package com.hcl.musicmelody.service;

import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.repositories.ArtistRepository;
import com.hcl.MusicMelody.services.ArtistService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepo;

    @InjectMocks
    private ArtistService artistService;

    private List<Artist> expectedArtists;

    @BeforeAll
    public void init() {

        MockitoAnnotations.openMocks(this);

        Artist artist1 = new Artist("Celine", "Dion");
        artist1.setArtistId(1L);
        Artist artist2 = new Artist("Prince", null);
        artist2.setArtistId(2L);
        Artist artist3 = new Artist("The Artist Formerly Known As Prince", null);
        artist3.setArtistId(3L);
        Artist artist4 = new Artist("The", "Artist");
        artist4.setArtistId(4L);

        this.expectedArtists = new ArrayList<>(Arrays.asList(
                artist1, artist2, artist3, artist4));
    }

    @Test
    public void getAllArtistsTest() {
        when(artistRepo.findAll()).thenReturn(expectedArtists);

        List<Artist> allArtists = artistService.getAllArtists();

        Assertions.assertEquals(4, allArtists.size());
    }


}
