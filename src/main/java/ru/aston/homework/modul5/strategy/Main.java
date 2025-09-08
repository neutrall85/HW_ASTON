package ru.aston.homework.modul5.strategy;

public class Main {

    public static void main(String[] args) {

        Device laptop = new Device("Ноутбук");
        Device tablet = new Device("Планшет");
        Device smartphone = new Device("Смартфон");

        laptop.setConfigurationStrategy(new LaptopConfigurationStrategy());
        tablet.setConfigurationStrategy(new TabletConfigurationStrategy());
        smartphone.setConfigurationStrategy(new SmartphoneConfigurationStrategy());

        laptop.configure();
        tablet.configure();
        smartphone.configure();

        laptop.showComponents();
        tablet.showComponents();
        smartphone.showComponents();
    }
}
