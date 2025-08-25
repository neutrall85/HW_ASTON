package ru.aston.homework.modul3;

public class WriteStrategy {
    @Override
    public void invoke(Scanner scanner) {
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();
        System.out.print("Введите текст для записи: ");
        String text = scanner.nextLine();

        try {
            FileWriter writer = new FileWriter(filePath, text);
            writer.process();
        } catch (MyFileException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
