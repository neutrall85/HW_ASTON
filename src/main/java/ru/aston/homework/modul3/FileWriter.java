package ru.aston.homework.modul3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;


class FileWriter implements FileProcessor {
    final Logger logger = Logger.getLogger(getClass().getName());
    private final String filePath;
    private final String content;

    FileWriter(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
    }

    @Override
    public void process() throws MyFileException {
        try {
            if (Files.exists(Paths.get(filePath))) {
                if (Files.size(Paths.get(filePath)) > 0) {
                    Files.writeString(Paths.get(filePath), "\n" + content, StandardOpenOption.APPEND);
                } else {
                    Files.writeString(Paths.get(filePath), content, StandardOpenOption.WRITE);
                }
            } else {
                Files.writeString(Paths.get(filePath), content, StandardOpenOption.CREATE);
            }
            logger.info("Данные успешно записаны в файл");
        } catch (IOException e) {
            throw new MyFileException("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
