package com.hcl.MusicMelody.repositories;

import java.util.List;
import java.util.Optional;

import com.hcl.MusicMelody.models.Song;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>{
    

    public List<Song> findAll();
    public Optional<Song> findByTitle(String name);

    @Query("SELECT s FROM Song s WHERE s.title LIKE %?1% or s.artist.fname like %?1% or s.artist.lname like %?1%")
    public List<Song> songSearch (String keyword);
}
