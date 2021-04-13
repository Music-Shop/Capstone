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
import lombok.Data;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;

@Entity
@Table(name = "artist")
@Data
public class Artist {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
	
	@Column(name = "artist_fname")
    private String fname;
	
	@Column(name = "artist_lname")
    private String lname;
	
	@Nullable
	@Column(name = "start_date")
    private String startDate;

	@OneToMany(mappedBy = "artist")
    private Set<Song> songs = new HashSet<>();

    public Artist() {
	}

	public Artist(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
}
