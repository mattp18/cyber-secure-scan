package com.code4joe.cybersecurescan.backend.service.impl;

import com.code4joe.cybersecurescan.backend.service.PdfService;
import com.code4joe.cybersecurescan.web.model.ScannedFile;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PdfServiceImpl implements PdfService {
    Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);

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
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportFileName);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportFileName);
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
