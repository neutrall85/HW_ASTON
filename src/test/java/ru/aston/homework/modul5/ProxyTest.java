package ru.aston.homework.modul5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.aston.homework.modul5.proxy.BankAccount;
import ru.aston.homework.modul5.proxy.SecureBankAccountProxy;
import java.math.BigDecimal;

class ProxyTest {
    private BankAccount bankAccount;
    private static final BigDecimal INITIAL_BALANCE = BigDecimal.ZERO;
    private static final BigDecimal DEPOSIT_AMOUNT = new BigDecimal("1000");
    private static final BigDecimal WITHDRAW_AMOUNT = new BigDecimal("500");
    private static final String VALID_PASSWORD = "secret";
    private static final String INVALID_PASSWORD = "wrong";

    @BeforeEach
    void setUp() {
        bankAccount = new SecureBankAccountProxy(VALID_PASSWORD);
        bankAccount.deposit(INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        Assertions.assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(DEPOSIT_AMOUNT);
        Assertions.assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(DEPOSIT_AMOUNT);
        bankAccount.withdraw(WITHDRAW_AMOUNT);
        Assertions.assertEquals(DEPOSIT_AMOUNT.subtract(WITHDRAW_AMOUNT), bankAccount.getBalance());
    }

    @Test
    void testInsufficientFunds() {
        bankAccount.deposit(DEPOSIT_AMOUNT);
        bankAccount.withdraw(DEPOSIT_AMOUNT.add(BigDecimal.ONE));
        Assertions.assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testInvalidPassword() {
        BankAccount invalidBankAccount = new SecureBankAccountProxy(INVALID_PASSWORD);
        Assertions.assertThrows(SecurityException.class, invalidBankAccount::getBalance);
    }

    @Test
    void testMultipleTransactions() {
        bankAccount.deposit(DEPOSIT_AMOUNT);
        bankAccount.withdraw(WITHDRAW_AMOUNT);
        bankAccount.deposit(WITHDRAW_AMOUNT);
        Assertions.assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
