package com.code4joe.cybersecurescan.backend.service.impl;

import com.code4joe.cybersecurescan.backend.service.PdfService;
import com.code4joe.cybersecurescan.shared.constant.PdfReportConstants;
import com.code4joe.cybersecurescan.web.model.ScannedFile;
import com.google.common.io.ByteSource;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
    Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);

    private final MinioClient minioClient;

    @Value("${jasper.file}")
    private String jrxmlFileName;

    @Value("${jasper.compiled.file}")
    private String  compiledReportFile;

    @Value("${jasper.output.file}")
    private String reportFileName;

    @Override
    public void generatePdf(ScannedFile fileToBeScanned, Map<String, Collection<String>> viruses) {

        compileReport(jrxmlFileName, compiledReportFile);

        List<ScannedFile> someList = new ArrayList<ScannedFile>();

        someList.add(fileToBeScanned);

        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(someList);

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReportFile, null, beanColDataSource);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            try {
                InputStream targetStream = ByteSource.wrap(pdfBytes).openStream();
                minioClient.putObject(PutObjectArgs.builder().bucket("upload-reports")
                        .object(fileToBeScanned.getFileName() + PdfReportConstants.REPORT_POSTFIX)
                        .stream(targetStream, PdfReportConstants.OBJECT_SIZE, PdfReportConstants.PART_SIZE).build());
            } catch (ServerException | InsufficientDataException | InternalException | ErrorResponseException |
                     IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException |
                     XmlParserException e) {
                throw new RuntimeException(e);
            }
            log.info("PDF created!");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private static void compileReport(String jrxmlFileName, String compiledReportFile) {
        try {
            JasperCompileManager.compileReportToFile(jrxmlFileName, compiledReportFile);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
