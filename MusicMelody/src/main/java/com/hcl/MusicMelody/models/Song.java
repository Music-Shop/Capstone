package com.hcl.MusicMelody.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "song")
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    @Length(min = 3, message = "Title should have at least 3 characters")
    private String title;

    @Column(name = "duration")
    // @NotEmpty(message = "Duration cannot be 0, empty, or null")
    private String duration;

    @Column(name = "cost", columnDefinition ="Decimal(10,2) default '0.00'" ,precision = 10, scale = 2)
    // @NotEmpty(message = "Please enter a price for the song. Cannot be null or empty")
    private BigDecimal cost;

    /**
     * Not added content. Cannot use currently
     */
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;   

    // @Column(name = "band")
    // @OneToMany
    // private Band band;
    
 
    @ManyToOne 
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    
    
    public Song() {
    }

    public Song(@Length(min = 3, message = "Title should have at least 3 characters") String title, String duration,
            BigDecimal cost, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.cost = cost;
        this.artist = artist;
    }

    public Song(Integer id, @Length(min = 3, message = "Title should have at least 3 characters") String title,
            String duration, BigDecimal cost, Album album, Artist artist, Genre genre) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.cost = cost;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
    } 
   

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
     
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    @Override
    public String toString() {
        return "Song [cost=" + cost + ", duration=" + duration + ", title=" + title + "]";
    }

    

    

    

    
}
