package ru.aston.homework.modul5.proxy;

import java.math.BigDecimal;

public interface BankAccount {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    BigDecimal getBalance();
}
