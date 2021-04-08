package com.hcl.MusicMelody.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

@Entity
@Table(name = "artist")
public class Artist {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
	
	@Column(name = "artist_fname")
    // @NotEmpty(message = "Duration cannot be 0, empty, or null")
    private String fname;
	
	@Column(name = "artist_lname")
    // @NotEmpty(message = "Duration cannot be 0, empty, or null")
    private String lname;
	
	@Nullable
	@Column(name = "start_date")
    // @NotEmpty(message = "Duration cannot be 0, empty, or null")
    private String startDate;

	@OneToMany(mappedBy = "artist")
    private Set<Song> songs = new HashSet<>();

    
	public Long getArtistId() {
		return artistId;
	}


	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}


	public Artist getArtist() {
		return artist;
	}


	public void setArtist(Artist artist) {
		this.artist = artist;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public Set<Song> getSongs() {
		return songs;
	}


	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	
	
}
