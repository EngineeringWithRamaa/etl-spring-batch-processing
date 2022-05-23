package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ECTProcessor implements ItemProcessor<ECT, ECT> {

    private static final Map<String, String > TRANSACTION_STATUS = new HashMap<>();

    public ECTProcessor() {
        TRANSACTION_STATUS.put("C","TRANSACTION COMPLETED");
        TRANSACTION_STATUS.put("F","TRANSACTION FAILED");
        TRANSACTION_STATUS.put("R","REFUND INITIATED");
    }

    @Override
    public ECT process(ECT ect) throws Exception {
        String transactionStatus = TRANSACTION_STATUS.get(ect.getTransactionStatus());
        ect.setTransactionStatus(transactionStatus);
        System.out.println("status transformed " + ect.getTransactionStatus());
        return ect;
    }
}
