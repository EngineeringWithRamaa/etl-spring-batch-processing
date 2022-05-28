package com.engineeringwithramaa.etlspringbatchprocessing.DAO;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
}
