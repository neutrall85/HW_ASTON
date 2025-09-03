package ru.aston.homework.modul5.proxy;

abstract class BaseBankAccountProxy implements BankAccount {
    protected RealBankAccount realBankAccount;

    protected BaseBankAccountProxy() {
        realBankAccount = new RealBankAccount();
    }

    @Override
    public final void deposit(double amount) {
        checkAccess();
        realBankAccount.deposit(amount);
    }

    @Override
    public final void withdraw(double amount) {
        checkAccess();
        realBankAccount.withdraw(amount);
    }

    @Override
    public final double getBalance() {
        checkAccess();
        return realBankAccount.getBalance();
    }

    protected abstract void checkAccess();
}
