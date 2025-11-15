package com.expensetracker.service;

import com.expensetracker.model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private FileService fileService = new FileService();

    public void addExpense(Expense expense) {
        fileService.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return fileService.load();
    }

    public List<Expense> filterByCategory(String category) {
        List<Expense> all = fileService.load();
        List<Expense> filtered = new ArrayList<>();
        for (Expense e : all) if (e.getCategory().equalsIgnoreCase(category)) filtered.add(e);
        return filtered;
    }

    public double getMonthlyTotal(String month) {
        List<Expense> list = fileService.load();
        double total = 0;
        for (Expense e : list) if (e.getDate().startsWith(month)) total += e.getAmount();
        return total;
    }
}
