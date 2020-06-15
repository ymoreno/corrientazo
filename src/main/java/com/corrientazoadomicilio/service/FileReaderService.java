package com.corrientazoadomicilio.service;

import java.io.IOException;
import java.util.List;

public interface FileReaderService {
    List<List<String>> getDroneMovements() throws IOException;
}
