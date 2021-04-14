package com.hcl.musicmelody.service;


import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.hcl.MusicMelody.services.SongService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.repositories.SongRepository;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SongServiceTests {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    private List<Song> expectedSongs;

    @BeforeAll
    public void init() {

        MockitoAnnotations.openMocks(this);

        Artist artist1 = new Artist("Fleetwood", "Mac");
        artist1.setArtistId(1L);
        Artist artist2 = new Artist("Eric", "Carmen");
        artist2.setArtistId(2L);
        Song song1 = new Song("Angel", "2:45", new BigDecimal("45.9"),artist1);
        song1.setId(1);
        Song song2 = new Song("All the Things of you are", "3:45", new BigDecimal("21.9"),artist2);
        song2.setId(2);
        this.expectedSongs = new ArrayList<>(Arrays.asList(song1, song2));

    }

    @Test
    public void getAllSongsTest() {
        when(songRepository.findAll()).thenReturn(expectedSongs);
        List<Song> allSongs = songService.getAllSongs();
        Assertions.assertEquals(2, allSongs.size());
    }}
