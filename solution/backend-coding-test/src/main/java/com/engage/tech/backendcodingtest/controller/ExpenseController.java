package com.engage.tech.backendcodingtest.controller;

import com.engage.tech.backendcodingtest.domain.Expense;
import com.engage.tech.backendcodingtest.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Expense> listExpenses() {
        LOGGER.debug("Received request to list all the expenses");
        List<Expense> expenses = expenseService.findAll();
        return expenses;
    }

    @PostMapping
    public Expense saveExpense(@RequestBody Expense expense) {
        LOGGER.debug("Received post request to save an expense");
        expenseService.save(expense);
        return expense;
    }

}
