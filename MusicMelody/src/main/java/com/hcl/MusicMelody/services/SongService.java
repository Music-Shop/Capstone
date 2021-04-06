package com.hcl.MusicMelody.services;

import java.util.Optional;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.repositories.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SongService {
    
    @Autowired
    private SongRepository songRepo;

    /**
     * Finds a song by the title
     * @param title - String 
     * @return Song
     */
    public Song GetByTitle(String title) throws RuntimeException{
        Optional<Song> holder = songRepo.findByTitle(title);
        if (!holder.isPresent()) throw new RuntimeException();
        else return holder.get();
    }

    public void addUpdateSong(Song song) {
        songRepo.save(song);
    }

    public Iterable<Song> GetAllSongs() {
        return songRepo.findAll();
    }
}
