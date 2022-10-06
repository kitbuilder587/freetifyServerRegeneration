package com.kitsoft.freetifyServer.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class StorageComponentImplementation implements  StorageComponent{

    @Autowired
    StorageIdGenerator storageIdGenerator;

    @Override
    public Long save(byte[] data) throws IOException {
        Long id = storageIdGenerator.getId();
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(getFile(id)));
        os.write(data);
        os.flush();
        os.close();
        return id;
    }

    @Override
    public byte[] load(Long storageId) throws IOException {
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(getFile(storageId)));
        byte[] file = is.readAllBytes();
        is.close();
        return file;
    }

    @Override
    public File getFile(Long fileId) {
        File f =new File("storage/" + fileId + ".file");
        return f;
    }
}
