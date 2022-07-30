package com.kitsoft.freetifyServer.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "compositionCreator")
    private Set<Composition> createdCompositions;

    @OneToMany(mappedBy = "playlistCreator")
    private Set<Playlist> createdPlaylists;
}
