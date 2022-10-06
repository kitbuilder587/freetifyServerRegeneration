package com.kitsoft.freetifyServer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<User> listeners;


    private Long pictureStorageId = -1L;
    private Long torrentStorageId = -1L;
    private Long torrentFileId = -1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composition that = (Composition) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
