package com.kitsoft.freetifyServer.components;

import java.io.File;
import java.io.IOException;

public interface StorageComponent {
    public Long save(byte[] data) throws IOException;
    public byte[] load(Long storageId) throws IOException;
    public File getFile(Long fileId);
}
