package ru.aston.homework.modul5.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        BankAccount account = new SecureBankAccountProxy("secret");

        try {
            account.deposit(1000);
            account.withdraw(500);
            double balance = account.getBalance();

            LOGGER.info("Текущий баланс: {}", balance);
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
