package com.engage.tech.backendcodingtest.repo;

import com.engage.tech.backendcodingtest.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
