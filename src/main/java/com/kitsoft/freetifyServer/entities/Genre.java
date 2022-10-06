package com.kitsoft.freetifyServer.entities;

public enum Genre {
    CLASSICAL ("Classical"),
    POP ("Pop"),
    ROCK ("Rock"),
    HIPHOP ("Hip-Hop");

    private String title;

    Genre(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
