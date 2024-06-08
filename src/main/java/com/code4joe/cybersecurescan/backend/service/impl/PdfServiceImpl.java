package com.code4joe.cybersecurescan.backend.service.impl;

import com.code4joe.cybersecurescan.backend.service.PdfService;
import com.code4joe.cybersecurescan.shared.constant.PdfReportConstants;
import com.code4joe.cybersecurescan.web.model.ScannedFile;
import com.google.common.io.ByteSource;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
    Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);

    private final MinioClient minioClient;
    private final ResourceLoader resourceLoader;

    @Value("${jasper.compiled.file}")
    private String  compiledReportFile;

    @Override
    public void generatePdf(ScannedFile fileToBeScanned) {

        List<ScannedFile> scannedFileList = new ArrayList<>();

        scannedFileList.add(fileToBeScanned);

        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(scannedFileList);

        try {
            Resource resource = resourceLoader.getResource("classpath:FirstJasper.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource.getFile().getPath(), null, beanColDataSource);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            try {
                InputStream targetStream = ByteSource.wrap(pdfBytes).openStream();
                if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(PdfReportConstants.BUCKET_NAME).build())) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(PdfReportConstants.BUCKET_NAME).build());
                }
                minioClient.putObject(PutObjectArgs.builder().bucket(PdfReportConstants.BUCKET_NAME)
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
