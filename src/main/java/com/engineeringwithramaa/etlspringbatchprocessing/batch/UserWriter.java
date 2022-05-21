package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.DAO.UserDAO;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserWriter implements ItemWriter<User> {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void write(List<? extends User> users) throws Exception {
        userDAO.saveAll(users);
        //System.out.println("Writer Logging - User Data dumped into MySQL " + users);
    }
}
