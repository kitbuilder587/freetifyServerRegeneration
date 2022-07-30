package com.kitsoft.freetifyServer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Compositions")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date releaseDate;
    private String authorName;
    private String album;
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Playlist> playlists = new HashSet<>();
    private Long duration;
    @Transient
    private byte[] data;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private User compositionCreator;


    private Long pictureStorageId = -1L;
    private Long soundStorageId = -1L;
}
