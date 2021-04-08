package com.hcl.MusicMelody.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "album")
public class Album {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
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
    
//    @ManyToMany(cascade = CascadeType.MERGE)
//	@JoinTable(name = "song_album", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "album_id"))
//	private Set<Song> songs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

//	public Set<Song> getSongs() {
//		return songs;
//	}
//
//	public void setSongs(Set<Song> songs) {
//		this.songs = songs;
//	}
    
    
}
