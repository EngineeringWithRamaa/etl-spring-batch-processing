package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.LibraryRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class LibraryRecordProcessor implements ItemProcessor<LibraryRecord, LibraryRecord> {

    @Override
    public LibraryRecord process(LibraryRecord libraryRecord) throws Exception {
        return libraryRecord;
    }
}
