package com.learningcode.mongo.mongodbspring.service;

import com.learningcode.mongo.mongodbspring.model.Expense;
import com.learningcode.mongo.mongodbspring.repositroy.ExpenseRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        (String.format("Cannot Find Expense by ID %s", expense.getId())))
                        );
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);
    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow(
                ()-> new RuntimeException(String.format("Yep... Cannot find by %s", name))
        );
    }
    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
