package com.mipt.lukapavlov.multithreading;

public class Bank {

    public boolean sendToAccountBadLock(BankAccount from, BankAccount to, int amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Счета не могут быть null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна не может быть положительной");
        }

        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " заблокировал счет " + from.getId());

            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " заблокировал счет " + to.getId());

                if (from.getBalance() >= amount) {
                    from.setBalance(from.getBalance() - amount);
                    to.setBalance(to.getBalance() + amount);
                    System.out.println(Thread.currentThread().getName() + " перевел " + amount);
                    return true;
                } else {
                    System.out.println("Недостаточно средств на счете " + from.getId());
                    return true;
                }
            }
        }
    }

    public boolean sendToAccount(BankAccount from, BankAccount to, int amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Счета не могут быть null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (from == to) {
            throw new IllegalArgumentException("Нельзя переводить самому себе");
        }

        BankAccount first = from.getId() < to.getId() ? from : to;
        BankAccount second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() >= amount) {
                    from.setBalance(from.getBalance() - amount);
                    to.setBalance(to.getBalance() + amount);
                    System.out.println(Thread.currentThread().getName() + " успешно перевел " + amount);
                    return true;
                } else {
                    System.out.println("Недостаточно средств на счете " + from.getId());
                    return false;
                }
            }
        }
    }
}
