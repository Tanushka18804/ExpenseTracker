package com.expensetracker.service;

import com.expensetracker.model.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private final String URL = "jdbc:mysql://localhost:3306/expense_db";
    private final String USER = "root";
    private final String PASS = "your_password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void insert(Expense e) {
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO expenses(title, category, amount, date) VALUES(?,?,?,?)");
            ps.setString(1, e.getTitle());
            ps.setString(2, e.getCategory());
            ps.setDouble(3, e.getAmount());
            ps.setString(4, e.getDate());
            ps.executeUpdate();
            con.close();
        } catch (Exception ex) {}
    }

    public List<Expense> getAll() {
        List<Expense> list = new ArrayList<>();
        try {
            Connection con = connect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM expenses");
            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")));
            }
            con.close();
        } catch (Exception ex) {}
        return list;
    }

    public List<Expense> getByCategory(String c) {
        List<Expense> list = new ArrayList<>();
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM expenses WHERE category=?");
            ps.setString(1, c);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")));
            }
            con.close();
        } catch (Exception ex) {}
        return list;
    }

    public double getMonthlyTotal(String m) {
        double total = 0;
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT SUM(amount) FROM expenses WHERE date LIKE ?");
            ps.setString(1, m + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble(1);
            con.close();
        } catch (Exception ex) {}
        return total;
    }
}
