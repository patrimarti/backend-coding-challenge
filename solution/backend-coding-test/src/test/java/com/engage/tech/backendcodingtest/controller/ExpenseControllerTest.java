package com.engage.tech.backendcodingtest.controller;

import com.engage.tech.backendcodingtest.domain.Expense;
import com.engage.tech.backendcodingtest.repo.ExpenseRepository;
import com.engage.tech.backendcodingtest.service.ExpenseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Mock
    private ExpenseService expenseServiceMock;

    @MockBean
    private ExpenseRepository expenseRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void readExpenses() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("24/01/2018");
        Date d1 = sdf.parse("23/01/2018");
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense(d, 300.3786, 50.06, "Laptop"));
        expenseList.add(new Expense(d1, 45, 7.5, "Chair"));
        when(expenseRepository.findAll()).thenReturn(expenseList);
        mockMvc.perform(get("/expenses").content("caca"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is((int)(expenseList.get(0).getId()))))
                .andExpect(jsonPath("$[0].date", is(expenseList.get(0).getDate().getTime())))
                .andExpect(jsonPath("$[0].amount", is(300.3786)))
                .andExpect(jsonPath("$[0].vat", is(50.06)))
                .andExpect(jsonPath("$[0].reason", is("Laptop")))
                .andExpect(jsonPath("$[1].id", is((int)(expenseList.get(1).getId()))))
                .andExpect(jsonPath("$[1].date", is(expenseList.get(1).getDate().getTime())))
                .andExpect(jsonPath("$[1].amount", is(45.0)))
                .andExpect(jsonPath("$[1].vat", is(7.5)))
                .andExpect(jsonPath("$[1].reason", is("Chair")));
    }

    @Test
    public void createExpense() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("11/11/2017");
        Expense testExpense = new Expense(d, 77, 33, "Test");
        String expenseJson = json(testExpense);
        this.mockMvc.perform(post("/expenses")
                .contentType(contentType)
                .content(expenseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("date", is(d.getTime())))
                .andExpect(jsonPath("amount", is(77.0)))
                .andExpect(jsonPath("vat", is(33.0)))
                .andExpect(jsonPath("reason", is("Test")));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
