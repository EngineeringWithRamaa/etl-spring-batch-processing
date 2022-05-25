package com.engineeringwithramaa.etlspringbatchprocessing.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriteListener<S> implements ItemWriteListener<S> {

    @Override
    public void beforeWrite(List<? extends S> items) {
      //  System.out.println("WriteListener::beforeWrite() -> item " + items );
    }

    @Override
    public void afterWrite(List<? extends S> items) {
      //  System.out.println("WriteListener::afterWrite() -> item " + items);
    }

    @Override
    public void onWriteError(Exception e, List<? extends S> items) {
        System.out.println("WriteListener::onWriteError() -> exception " + e + " , " + items );
    }
}
