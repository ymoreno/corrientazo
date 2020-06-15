package com.corrientazoadomicilio.service.impl;

import com.corrientazoadomicilio.service.FileReaderService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger logger = Logger.getLogger(FileReaderServiceImpl.class);
    private static final String IN_RUTAS_FOLDER = ".\\src\\main\\resources\\rutas";

    @Override
    public List<List<String>> getDroneMovements() throws IOException {
        return Files.list(Paths.get(IN_RUTAS_FOLDER))
                .map(this::getLines)
                .collect(Collectors.toList());
    }

    private List<String> getLines(Path path) {
        try {
            return Files.lines(path)
            .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Ha ocurrido un error al intentar leer el los archivos de entrada: ", e);
            return new ArrayList<>();
        }
    }

}
