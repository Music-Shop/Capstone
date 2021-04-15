package com.hcl.MusicMelody.models;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
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
    private Long duration;

    @Column(name = "cost", columnDefinition ="Decimal(10,2) default '0.00'" ,precision = 10, scale = 2)
    private Double cost;
    
	@OneToMany(mappedBy = "artist")
    private Set<Song> songs = new HashSet<>();
    
}
