package com.hcl.SpringSecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import com.hcl.MusicMelody.models.Album;
import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.models.ConfirmedPurchase;
import com.hcl.MusicMelody.models.Genre;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.repositories.ConfirmedPurchaseRepository;
import com.hcl.MusicMelody.services.ConfirmedPurchaseService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfirmedPurchaseTesting {
    
    @Mock
    private ConfirmedPurchaseRepository purchaseRepo;

    @InjectMocks
    private ConfirmedPurchaseService purchaseService;

    private ConfirmedPurchase purchase;
    private ArrayList<ConfirmedPurchase> purchases = new ArrayList<>();
    

    @BeforeAll
    public void setup() {
        Artist artist = new Artist("John", "Legend");
        UserCred user = new UserCred();
        Song song = new Song("Hello", "2:30", new BigDecimal("7.99"), artist);
        ConfirmedPurchase testPurchase = new ConfirmedPurchase(1, song, user);
        purchases.add(testPurchase);
    }

    @Test
    public void shouldGetSongById() {
        // found case
        when(purchaseRepo.findById(1)).thenReturn(Optional.ofNullable(purchases.get(0)));
        purchase = purchaseService.getSongById(1);
        assertEquals(purchases.get(0), purchase);

        // not found case
        when(purchaseRepo.findById(2)).thenReturn(Optional.ofNullable(null));
        assertNull(purchaseService.getSongById(2));
    }

}