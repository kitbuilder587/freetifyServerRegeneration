package com.kitsoft.freetifyServer.scrapers;

import com.kitsoft.freetifyServer.components.StorageComponent;
import com.kitsoft.freetifyServer.components.StorageComponentImplementation;
import com.kitsoft.freetifyServer.entities.Composition;
import com.kitsoft.freetifyServer.services.CompositionService;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sound.sampled.*;
import java.io.*;

//@Component
public class ClassicalBethovenScraper {

    @Autowired
    CompositionService compositionService;

    @Autowired
    StorageComponent storageComponent;

    @PostConstruct
    public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        File torrentFolder = new File("beth/");
        Composition[] compositions = new Composition[100];
        File bethTorrent = new File("beth.torrent");
        BufferedInputStream bethTorrentIS = new BufferedInputStream(new FileInputStream(bethTorrent));
        Long torrentStorageId = storageComponent.save(bethTorrentIS.readAllBytes());
        for(int i=0;i<compositions.length;i++) {
            compositions[i] = new Composition();
            compositions[i].setTorrentStorageId(torrentStorageId);
        }
        for( File f : torrentFolder.listFiles()){
            if(f.getName().equals("folder.jpg")){
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));
                Long storageId = storageComponent.save(is.readAllBytes());
                for(Composition c : compositions)
                    c.setPictureStorageId(storageId);
            }else{
                String indxString= f.getName().split("-")[0].replaceAll(" ","");
                int compostionIndx = Integer.parseInt(indxString) - 1;
                String comp = f.getName().split("-")[1].replaceAll(".mp3", "");
                String author = comp.split("_")[0];
                String name = comp.substring(author.length() + 1);
                compositions[compostionIndx].setName(name);
                compositions[compostionIndx].setAuthorName(author);
                compositions[compostionIndx].setTorrentFileId((long)compostionIndx  + 1);
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));
                Mp3File mp3File = new Mp3File(f);
                compositions[compostionIndx].setDuration(mp3File.getLengthInSeconds());
            }
        }

        for(Composition c : compositions){
            compositionService.addComposition(c);
        }

    }
}
