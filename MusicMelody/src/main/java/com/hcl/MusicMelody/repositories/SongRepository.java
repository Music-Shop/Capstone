package com.hcl.MusicMelody.repositories;

import java.util.Optional;

import com.hcl.MusicMelody.models.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>{
    
    public Optional<Song> findByTitle(String name);
}
