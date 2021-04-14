package com.hcl.SpringSecurity;

import com.hcl.MusicMelody.models.ConfirmedPurchase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class ConfirmedPurchaseTesting {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConfirmedPurchase purchase;
    
    @BeforeAll
    public void setup() {

    }

    @Test
    public void shouldGetSongById() {

    }

    @Test
    public void shouldSaveOrUpdate() {
        
    }
}