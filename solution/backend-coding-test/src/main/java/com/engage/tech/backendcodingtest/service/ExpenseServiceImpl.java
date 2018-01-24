package com.engage.tech.backendcodingtest.service;

import com.engage.tech.backendcodingtest.domain.Expense;
import com.engage.tech.backendcodingtest.repo.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense save(Expense expense) {
        LOGGER.debug("Save expense {}", expense);
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        LOGGER.debug("Retrieve all the expenses");
        return expenseRepository.findAll();
    }
}
