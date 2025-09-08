package ru.aston.homework.modul5.proxy;

import java.math.BigDecimal;

public abstract class BaseBankAccountProxy implements BankAccount {
    protected RealBankAccount realBankAccount;

    protected BaseBankAccountProxy() {
        realBankAccount = new RealBankAccount();
    }

    @Override
    public final void deposit(BigDecimal amount) {
        checkAccess();
        realBankAccount.deposit(amount);
    }

    @Override
    public final void withdraw(BigDecimal amount) {
        checkAccess();
        realBankAccount.withdraw(amount);
    }

    @Override
    public final BigDecimal getBalance() {
        checkAccess();
        return realBankAccount.getBalance();
    }

    protected abstract void checkAccess();
}
