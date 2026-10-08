package com.example.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, holder_name, balance) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getAccountNumber());
            ps.setString(2, account.getHolderName());
            ps.setDouble(3, account.getBalance());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account created successfully for: " + account.getHolderName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        Account account = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account(
                        rs.getInt("id"),
                        rs.getString("account_number"),
                        rs.getString("holder_name"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Account(
                        rs.getInt("id"),
                        rs.getString("account_number"),
                        rs.getString("holder_name"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setString(2, accountNumber);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Balance updated successfully for account: " + accountNumber);
            } else {
                System.out.println("Account not found: " + accountNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String accountNumber) {
        String sql = "DELETE FROM accounts WHERE account_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account deleted successfully: " + accountNumber);
            } else {
                System.out.println("Account does not exist: " + accountNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}