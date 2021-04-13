package com.hcl.MusicMelody.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "confirmed_purchase")
@Data
public class ConfirmedPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_id")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserCred user;
	
}
