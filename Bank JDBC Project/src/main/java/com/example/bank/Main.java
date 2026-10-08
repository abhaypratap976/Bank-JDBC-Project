package com.example.bank;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountDAO service = new AccountDAO();

        System.out.println("--- 1. Creating Accounts ---");
        Account acc1 = new Account("SB1001", "Abhay Pratap", 50000.0);
        Account acc2 = new Account("SB1002", "Rohan Sharma", 15000.0);
        service.createAccount(acc1);
        service.createAccount(acc2);

        System.out.println("\n--- 2. Fetch Single Account ---");
        Account fetched = service.getAccountByNumber("SB1001");
        System.out.println("Found: " + fetched);

        System.out.println("\n--- 3. Updating Balance ---");
        service.updateBalance("SB1001", 75000.0);

        System.out.println("\n--- 4. List All Accounts ---");
        List<Account> allAccounts = service.getAllAccounts();
        for (Account a : allAccounts) {
            System.out.println(a);
        }

        System.out.println("\n--- 5. Delete Account ---");
        service.deleteAccount("SB1002");

        System.out.println("\n--- Final Accounts List ---");
        service.getAllAccounts().forEach(System.out::println);
    }
}