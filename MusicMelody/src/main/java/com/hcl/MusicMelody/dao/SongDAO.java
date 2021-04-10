package com.hcl.MusicMelody.dao;

import java.io.IOException;
import java.util.Date;
 
import javax.persistence.NoResultException;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.MusicMelody.form.SongForm;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.models.SongInfo;
import com.hcl.MusicMelody.pagination.PaginationResult;
 
@Transactional
@Repository
public class SongDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Song findSong(String code) {
        try {
            String sql = "Select e from " + Song.class.getName() + " e Where e.code =:code ";
 
            Session session = this.sessionFactory.getCurrentSession();
            Query<Song> query = session.createQuery(sql, Song.class);
            query.setParameter("code", code);
            return (Song) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
    public SongInfo findSongInfo(String code) {
    	Song song = this.findSong(code);
        if (song == null) {
            return null;
        }
        return new SongInfo(song.getCode(), song.getTitle(), song.getCost());
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(SongForm songForm) {
 
        Session session = this.sessionFactory.getCurrentSession();
        String code = songForm.getCode();
 
        Song song = null;
 
        boolean isNew = false;
        if (code != null) {
        	song = this.findSong(code);
        }
        if (song == null) {
            isNew = true;
            song = new Song();
            song.setCreateDate(new Date());
        }
        song.setCode(code);
        song.setTitle(songForm.getName());
        song.setCost(songForm.getPrice());
 
        if (songForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = songForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
            	song.setImage(image);
            }
        }
        if (isNew) {
            session.persist(song);
        }
        // If error in DB, Exceptions will be thrown out immediately
        session.flush();
    }
 
    public PaginationResult<SongInfo> querySongs(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + SongInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + SongInfo.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        // 
        Session session = this.sessionFactory.getCurrentSession();
        Query<SongInfo> query = session.createQuery(sql, SongInfo.class);
 
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<SongInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<SongInfo> querySongs(int page, int maxResult, int maxNavigationPage) {
        return querySongs(page, maxResult, maxNavigationPage, null);
    }
}
