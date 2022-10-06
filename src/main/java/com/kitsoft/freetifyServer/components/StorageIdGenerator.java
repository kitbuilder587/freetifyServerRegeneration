package com.kitsoft.freetifyServer.components;

import org.springframework.stereotype.Component;

@Component
public class StorageIdGenerator {
    private Long id = 0L;

    public Long getId(){
        id++;
        return id;
    }
}
