package com.hcl.MusicMelody.services;

import java.util.List;
import java.util.Optional;

import com.hcl.MusicMelody.models.Artist;
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

    public Artist GetArtistByFName(String fname) {
        Optional<Artist> artist = artistRepo.findByFname(fname);
        if(!artist.isPresent()) {
            return null;
        }
        return artist.get();
    }

    public void addArtist(Artist artist) { 
        artistRepo.save(artist);
    }
}
