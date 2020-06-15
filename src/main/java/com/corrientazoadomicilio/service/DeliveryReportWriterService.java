package com.corrientazoadomicilio.service;

import java.io.IOException;
import java.util.List;

public interface DeliveryReportWriterService {

    void writeReport(List<String> data, int reportNumber) throws IOException;

}
