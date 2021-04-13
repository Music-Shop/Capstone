package com.hcl.MusicMelody.services;

import java.util.List;
import java.util.Optional;

import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.repositories.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    
    @Autowired
    private ArtistRepository artistRepo;

    public List<Artist> GetAllArtists() {
        return artistRepo.findAll();
    }

    public Optional<Artist> GetArtistByFName(String fname) {
        Optional<Artist> artist = artistRepo.findByFname(fname);
        if(!artist.isPresent()) {
            return null;
        }
        return artist;
    }
    
    public Artist getArtistById(Integer songId) {
    	Optional<Artist> artist = artistRepo.findById(songId);
        if(!artist.isPresent()) {
            return null;
        }
        return artist.get();
    }

    public Artist addArtist(Artist artist) { 
        return artistRepo.save(artist);
    }
}
