package com.hcl.MusicMelody.repositories;

import java.util.Optional;

import com.hcl.MusicMelody.models.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer>{
    
    public Optional<Artist> findByFname(String fname);
}
