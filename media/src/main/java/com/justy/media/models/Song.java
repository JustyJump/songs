package com.justy.media.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, singer, full_song;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {

        this.singer = singer;
    }

    public String getFull_song() {

        return full_song;
    }

    public void setFull_song(String full_song) {
        this.full_song = full_song;
    }

    public Song() {
    }

    public Song(String name, String singer, String full_song) {
        this.name = name;
        this.singer = singer;
        this.full_song = full_song;
    }

}
