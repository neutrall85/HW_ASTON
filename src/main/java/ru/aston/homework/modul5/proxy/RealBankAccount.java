package ru.aston.homework.modul5.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
@Setter
@NoArgsConstructor
public class RealBankAccount implements BankAccount {
    private double balance;
    private final Logger logger = LoggerFactory.getLogger(RealBankAccount.class);

    @Override
    public void deposit(double amount) {
        balance += amount;
        logger.info("Пополнено: {}", amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            logger.info("Снято: {}", amount);
        } else {
            logger.warn("Попытка снятия средств. Недостаточно средств!");
        }
    }

    @Override
    public double getBalance() {
        logger.debug("Запрос баланса");
        return balance;
    }
}
