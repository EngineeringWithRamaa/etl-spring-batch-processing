package com.engineeringwithramaa.etlspringbatchprocessing.DAO;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ECTDAO extends JpaRepository<ECT, String> {
}
