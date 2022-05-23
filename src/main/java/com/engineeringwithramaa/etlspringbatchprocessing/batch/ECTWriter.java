package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.DAO.ECTDAO;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ECTWriter implements ItemWriter<ECT> {
    @Autowired
    private ECTDAO ectdao;
    @Override
    public void write(List<? extends ECT> ECTList) throws Exception {
        ectdao.saveAll(ECTList);
    }
}
