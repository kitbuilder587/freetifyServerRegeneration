package com.kitsoft.freetifyServer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
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

    private String username;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "listeners")
    @JsonIgnore
    private Set<Composition> listenedCompositions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
