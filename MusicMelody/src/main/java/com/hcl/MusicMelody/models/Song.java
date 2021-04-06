package com.hcl.MusicMelody.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "song")
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @Length(min = 3, message = "Title should have at least 3 characters")
    private String title;

    @Column(name = "duration")
    // @NotEmpty(message = "Duration cannot be 0, empty, or null")
    private Long duration;

    @Column(name = "cost", columnDefinition ="Decimal(10,2) default '0.00'" ,precision = 10, scale = 2)
    // @NotEmpty(message = "Please enter a price for the song. Cannot be null or empty")
    private Double cost;

    /**
     * Not added content. Cannot use currently
     */
    // @Column(name = "album")
    // @NotEmpty(message = "Cannot be empty. Must be Single or part of a album")
    // @OneToMany
    // private Album album;

    // @Column(name = "artist")
    // @OneToMany
    // private Artist artist;

    // @Column(name = "band")
    // @OneToMany
    // private Band band;

    // @Column(name = "genre")
    // @NotEmpty(message = "Song must have a genre. Cannot be Empty or null")
    // @ManyToMany
    // private String genre;

    public Song() {
    }

    public Song(@Length(min = 3, message = "Title should have at least 3 characters") String title,
            @NotEmpty(message = "Duration cannot be 0, empty, or null") Long duration,
            @NotEmpty(message = "Please enter a price for the song. Cannot be null or empty") Double cost) {
        this.title = title;
        this.duration = duration;
        this.cost = cost;
        // this.album = album;
        // this.artist = artist;
        // this.band = band;
        // this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Song [cost=" + cost + ", duration=" + duration + ", title=" + title + "]";
    }

    
}
