package com.corrientazoadomicilio.service.impl;

import com.corrientazoadomicilio.service.DeliveryReportWriterService;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class DeliveryReportWriterServiceImpl implements DeliveryReportWriterService {

    private static final Logger logger = Logger.getLogger(DeliveryReportWriterServiceImpl.class);
    private static final String REPORTS_OUT_FOLDER = ".\\src\\main\\resources\\reportes\\out";

    @Override
    public void writeReport(List<String> data, int reportNumber) throws IOException {

        File out = new File(REPORTS_OUT_FOLDER +String.format("%02d", reportNumber)+".txt");
        FileOutputStream fos = new FileOutputStream(out);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
        data.forEach(line-> {
            try {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            } catch (IOException e) {
                logger.error("Ha ocurrido un error al intentar escribir el reporte de entrega: ", e);
            }
        });
        bufferedWriter.close();
    }
}
