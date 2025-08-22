package homeworks.modul3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class FileWriter implements FileProcessor {
    private String filePath;
    private String content;

    FileWriter(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
    }

    @Override
    public void process() throws MyFileException {
        try {
            Files.writeString(Paths.get(filePath), content);
            System.out.println("Данные успешно записаны в файл");
        } catch (IOException e) {
            throw new MyFileException("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}

