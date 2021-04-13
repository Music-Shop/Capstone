package com.hcl.MusicMelody.models;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    
    private int orderNum;

    private UserCred user;

    private List<Song> cart = new ArrayList<Song>();

    public Cart() {
    }

    public int getOrderNum() {
        return orderNum;
    }


    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }


    public UserCred getUser() {
        return user;
    }


    public void setUser(UserCred user) {
        this.user = user;
    }


    public List<Song> getCart() {
        return cart;
    }


    public void setCart(List<Song> cart) {
        this.cart = cart;
    }



    public void addSong(Song song, int quantity) {
        
    }
}
