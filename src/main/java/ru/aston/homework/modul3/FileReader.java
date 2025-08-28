package ru.aston.homework.modul3;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.logging.Logger;

class FileReader implements FileProcessor {
    final Logger logger = Logger.getLogger(getClass().getName());
    private String filePath;

    FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void process() throws MyFileException {
        try {
            String content = Files.readString(Paths.get(filePath));
            logger.info("Содержимое файла:");
            logger.info(content);
        } catch (IOException e) {
            throw new MyFileException("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
