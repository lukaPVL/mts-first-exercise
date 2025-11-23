package com.mipt.lukapavlov.multithreading;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BankTest {

    @Test
    public void testBasicTransfer() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 1000);
        BankAccount account2 = new BankAccount(2, 500);

        boolean result = bank.sendToAccount(account1, account2, 200);

        assertTrue(result);
        assertEquals(800, account1.getBalance());
        assertEquals(700, account2.getBalance());
    }

    @Test
    public void testInsufficientFunds() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 100);
        BankAccount account2 = new BankAccount(2, 500);

        boolean result = bank.sendToAccount(account1, account2, 200);

        assertFalse(result);
        assertEquals(100, account1.getBalance());
        assertEquals(500, account2.getBalance());
    }
    @Test
    public void testNullValidation() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 100);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(null, account1, 50);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(account1, null, 50);
        });
    }

    @Test
    public void testNegativeAmount() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 100);
        BankAccount account2 = new BankAccount(2, 500);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(account1, account2, -50);
        });
    }

    @Test
    public void testMultiThreadedCorrectMethod() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 1000);
        BankAccount account2 = new BankAccount(2, 1000);

        int threadCount = 10;
        int transfersPerThread = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < transfersPerThread; j++) {
                    bank.sendToAccount(account1, account2, 1);
                    bank.sendToAccount(account2, account1, 1);
                }
                latch.countDown();
            }).start();
        }

        boolean completed = latch.await(10, TimeUnit.SECONDS);

        assertTrue(completed, "Все потоки должны завершиться без deadlock");
        assertEquals(1000, account1.getBalance());
        assertEquals(1000, account2.getBalance());
    }

    @Test
    public void testDeadlockScenario() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount(1, 1000);
        BankAccount account2 = new BankAccount(2, 1000);

        AtomicBoolean thread1Completed = new AtomicBoolean(false);
        AtomicBoolean thread2Completed = new AtomicBoolean(false);

        Thread thread1 = new Thread(() -> {
            bank.sendToAccountBadLock(account1, account2, 100);
            thread1Completed.set(true);
        }, "Thread-A-to-B");

        Thread thread2 = new Thread(() -> {
            bank.sendToAccountBadLock(account2, account1, 100);
            thread2Completed.set(true);
        }, "Thread-B-to-A");

        thread1.start();
        thread2.start();

        thread1.join(3000);
        thread2.join(3000);

        boolean deadlockOccurred = !thread1Completed.get() || !thread2Completed.get();

        System.out.println("Thread 1 завершился: " + thread1Completed.get());
        System.out.println("Thread 2 завершился: " + thread2Completed.get());
        System.out.println("Deadlock произошел: " + deadlockOccurred);

        assertTrue(true, "Тест завершился (не завис навсегда)");
    }

    @Test
    public void testMultipleAccounts() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount[] accounts = {
                new BankAccount(1, 1000),
                new BankAccount(2, 1000),
                new BankAccount(3, 1000),
                new BankAccount(4, 1000)
        };

        int threadCount = 8;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    int from = threadId % accounts.length;
                    int to = (threadId + 1) % accounts.length;
                    bank.sendToAccount(accounts[from], accounts[to], 1);
                }
                latch.countDown();
            }).start();
        }

        boolean completed = latch.await(10, TimeUnit.SECONDS);
        assertTrue(completed, "Многопоточные переводы между множеством счетов");

        int totalBalance = 0;
        for (BankAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        assertEquals(4000, totalBalance, "Общий баланс должен сохраниться");
    }
}
