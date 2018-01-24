package com.engage.tech.backendcodingtest.service;

import com.engage.tech.backendcodingtest.domain.Expense;
import com.engage.tech.backendcodingtest.repo.ExpenseRepository;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ExpenseServiceTest {

    @TestConfiguration
    static class ExpenseServiceImplTestContextConfiguration {

        @Bean
        public ExpenseService expenseService() {
            return new ExpenseServiceImpl();
        }
    }

    @Autowired
    private ExpenseService expenseService;

    @MockBean
    private ExpenseRepository expenseRepository;

    @Mock
    private Expense expense;

    @Test
    public void findAllExpensesSuccess() {
        List<Expense> expenseList = new ArrayList<>();
        Date d = new Date();
        expenseList.add(new Expense(d, 300.3786, 50.06, "Laptop"));
        expenseList.add(new Expense(d, 45, 7.5, "Chair"));

        Mockito.when(expenseRepository.findAll())
                .thenReturn(expenseList);

        List<Expense> expenseListRepository = expenseRepository.findAll();
        List<Expense> expenseListService = expenseService.findAll();
        assertArrayEquals(Arrays.array(expenseListRepository), Arrays.array(expenseListService));
    }

    @Test
    public void findAllExpensesEmptyList() {
        List<Expense> expenseListService = expenseService.findAll();
        assertEquals(true, expenseListService.isEmpty());
    }

    @Test
    public void saveSuccess() {
        Mockito.when(expenseRepository.save(expense)).thenReturn(expense);
        Expense savedExpense = expenseService.save(expense);
        assertEquals(savedExpense, expense);
    }

}
