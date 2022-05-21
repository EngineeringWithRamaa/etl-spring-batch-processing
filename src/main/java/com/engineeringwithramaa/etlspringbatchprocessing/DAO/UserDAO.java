package com.engineeringwithramaa.etlspringbatchprocessing.DAO;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
}
