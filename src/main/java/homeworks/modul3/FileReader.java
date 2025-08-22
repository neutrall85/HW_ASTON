package homeworks.modul3;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

class FileReader implements FileProcessor {
    private Path filePath;

    FileReader(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void process() throws MyFileException {
        try {
            String content = Files.readString(filePath);
            System.out.println("Содержимое файла:");
            System.out.println(content);
        } catch (IOException e) {
            throw new MyFileException("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
