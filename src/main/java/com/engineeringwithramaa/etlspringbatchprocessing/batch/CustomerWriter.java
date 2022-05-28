package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.DAO.CustomerDAO;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter implements ItemWriter<Customer> {
    @Autowired
    private CustomerDAO customerDAO;
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        customerDAO.saveAll(list);
    }
}
