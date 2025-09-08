package ru.aston.homework.modul5.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class RealBankAccount implements BankAccount {
    private BigDecimal balance = BigDecimal.ZERO;

    @Override
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        log.info("Пополнено: {}", amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            log.info("Снято: {}", amount);
        } else {
            log.warn("Попытка снятия средств. Недостаточно средств!");
        }
    }

    @Override
    public BigDecimal getBalance() {
        log.debug("Запрос баланса");
        return balance;
    }
}
