package com.engage.tech.backendcodingtest.service;

import com.engage.tech.backendcodingtest.domain.Expense;
import java.util.List;

public interface ExpenseService {

    Expense save(Expense expense);

    List<Expense> findAll();

}
