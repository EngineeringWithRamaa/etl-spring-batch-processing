package com.engineeringwithramaa.etlspringbatchprocessing.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class ReadListener<T> implements ItemReadListener {

    @Override
    public void beforeRead() {
        //System.out.println("Read Listener :: beforeRead");
    }

    @Override
    public void afterRead(Object o) {
        //System.out.println("Read Listener :: afterRead " + o.toString());
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("Read Listener :: onReadError " + e.getMessage());
    }
}

