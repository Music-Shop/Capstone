package com.hcl.MusicMelody.services;

import java.util.List;
import java.util.Optional;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.repositories.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Song> getAllSongs() {
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
    
//    public List<Song> getPaging(Integer pageNo, Integer pageSize, String sortBy)
//    {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
// 
//        Page<Song> pagedResult = songRepo.findAll(paging);
//          
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//        	
//        	List<Song> song = new ArrayList<Song>();
//            return song;
//        }
//    }
    
    public Page<Song> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
    	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
    	
    	Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    	
    	return this.songRepo.findAll(pageable);
    };
    
    public Optional<Song> getSongById(Integer songId) {
    	return songRepo.findById(songId);
    }

    public List<Song> listAll(String keyword) {
        if (keyword != null) {
            return songRepo.findAll(keyword);
        }
        return songRepo.findAll();   
    }

    public void deleteSongById(Integer id) {
        songRepo.delete(songRepo.findById(id).get());
    }
    
    public Song saveSong(Song song) {
        return songRepo.save(song);
    }
}
