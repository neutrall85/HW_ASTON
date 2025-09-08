package ru.aston.homework.modul5.proxy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            BankAccount account = new SecureBankAccountProxy("secret");

            account.deposit(new BigDecimal(1000));
            account.withdraw(new BigDecimal(500));
            BigDecimal balance = account.getBalance();

            log.info("Текущий баланс: {}", balance);
        } catch (SecurityException e) {
            log.error("Ошибка безопасности: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Произошла непредвиденная ошибка", e);
        }
    }
}
