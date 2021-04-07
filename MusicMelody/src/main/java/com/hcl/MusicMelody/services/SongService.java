package com.hcl.MusicMelody.services;

import java.util.List;
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

    /**
     * If song does not already exist, then it is added to the database; 
     * else the song is updated with the following fields 
     * @param song - Song
     */
    public void addUpdateSong(Song song) {
        songRepo.save(song);
    }

    /**
     * Collects all songs that are in the database
     * @return List<Song>
     */
    public List<Song> GetAllSongs() {
        return songRepo.findAll();
    }

    public String convertDuration(int secs) {
        int mins = secs/60;
        int seconds = secs % 60;
        if(seconds < 10) {
            return String.valueOf(mins) + ":" + String.valueOf("0" + seconds);
        }
        return String.valueOf(mins) + ":" + String.valueOf(seconds);
    }
}
