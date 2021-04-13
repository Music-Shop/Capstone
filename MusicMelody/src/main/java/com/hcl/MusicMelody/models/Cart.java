package com.hcl.MusicMelody.models;

import java.util.ArrayList;
import lombok.Data;
import java.util.List;

@Data
public class Cart {
    
    private int orderNum;

    private UserCred user;

    private List<Song> cart = new ArrayList<Song>();

    public Cart() {
    }

    public void addSong(Song song, int quantity) {
        
    }
}
