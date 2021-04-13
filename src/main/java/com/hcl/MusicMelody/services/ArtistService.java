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

    public List<Artist> getAllArtists() {
        return artistRepo.findAll();
    }

    public Artist getArtistByFName(String fname) {
        Optional<Artist> artist = artistRepo.findByFname(fname);
        return artist.orElse(null);
    }
    
    public Artist getArtistById(Integer artistId) {
    	Optional<Artist> artist = artistRepo.findById(artistId);
        return artist.orElse(null);
    }

    public Artist addArtist(Artist artist) { 
        return artistRepo.save(artist);
    }
}
