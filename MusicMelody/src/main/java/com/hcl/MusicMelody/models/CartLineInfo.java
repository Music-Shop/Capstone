package com.hcl.MusicMelody.models;

import java.math.BigDecimal;

public class CartLineInfo {
 
   private SongInfo songInfo;
   private int quantity;
 
   public CartLineInfo() {
       this.quantity = 0;
   }
 
   public SongInfo getSongInfo() {
       return songInfo;
   }
 
   public void setSongInfo(SongInfo songInfo) {
       this.songInfo = songInfo;
   }
 
   public int getQuantity() {
       return quantity;
   }
 
   public void setQuantity(int quantity) {
       this.quantity = quantity;
   }
 
   public BigDecimal getAmount() {
       return this.songInfo.getPrice().multiply(BigDecimal.valueOf(this.quantity));
   }
}
