package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.DAO.LibraryRecordDAO;
import com.engineeringwithramaa.etlspringbatchprocessing.DAO.UserDAO;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.LibraryRecord;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryRecordWriter implements ItemWriter<LibraryRecord> {

    @Autowired
    private LibraryRecordDAO libraryRecordDAO;

    @Override
    public void write(List<? extends LibraryRecord> records) throws Exception {
        libraryRecordDAO.saveAll(records);
    }
}
