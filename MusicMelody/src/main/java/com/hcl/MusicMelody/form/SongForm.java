package com.hcl.MusicMelody.form;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.web.multipart.MultipartFile;

import com.hcl.MusicMelody.models.Song;

public class SongForm {
    private String code;
    private String name;
    private BigDecimal price;
 
    private boolean newSong= false;
 
    // Upload file.
    private MultipartFile fileData;
 
    public SongForm() {
        this.newSong= true;
    }
 
    public SongForm(Song song) {
        this.code = song.getCode();
        this.name = song.getTitle();
        this.price = song.getCost();
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public BigDecimal getPrice() {
        return price;
    }
 
    public void setPrice(BigDecimal song) {
        this.price = song;
    }
 
    public MultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewSong() {
        return newSong;
    }
 
    public void setNewProduct(boolean newSong) {
        this.newSong = newSong;
    }
}
