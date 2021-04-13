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
import lombok.Data;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "song")
@Data
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    @Length(min = 3, message = "Title should have at least 3 characters")
    private String title;

    @Column(name = "duration")
    private String duration;

    @Column(name = "cost", columnDefinition ="Decimal(10,2) default '0.00'" ,precision = 10, scale = 2)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;   
 
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

    

    
}
