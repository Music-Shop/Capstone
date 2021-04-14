package com.hcl.musicmelody.controllers;

import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.repositories.ArtistRepository;
import com.hcl.MusicMelody.repositories.SongRepository;
import com.hcl.MusicMelody.services.ArtistService;
import com.hcl.MusicMelody.services.SongService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(classes = {TextContext.class, WebAppContext.class})
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Mock
    private ArtistRepository artistRepo;

    @Mock
    private SongRepository songRepo;

    @InjectMocks
    private ArtistService artistService;

    @InjectMocks
    private SongService songService;

    private List<Song> expectedSongs;
    private List<Artist> expectedArtists;

    @BeforeAll
    public void init() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext().build())

        Artist artist1 = new Artist("Clearwater Creedence Revival", "");
        artist1.setArtistId(1L);
        Artist artist2 = new Artist("Michael", "Jackson");
        artist1.setArtistId(2L);

        this.expectedArtists = Arrays.asList(artist1, artist2);

        Song song1 = new Song("Bad Moon Rising", "3:00", BigDecimal.valueOf(0.99),
                artist1);
        Song song2 = new Song("Billie Jean", "3:00", BigDecimal.valueOf(0.99), artist2);
        Song song3 = new Song("My Way", "3:00", BigDecimal.valueOf(0.99), artist2);

        this.expectedSongs = Arrays.asList(song1, song2, song3);

    }

    @Test
    public void showSongInvTest() {

        Mockito.when(songRepo.findAll()).thenReturn(expectedSongs);

        mockMvc.perform(get("/admin/home/song-inventory"))
            .andExpect(status().isOk())
            .andExpect(view().name("admin/song-inventory"))
                .andExpect(forwardedUrl("WEB-INF/jsp/"))
                .andExpect(model().attribute("listSongs", hasSize(2)))
                .andExpect(model().attribute("listSongs"), hasItem(
                        allOf(
                            hasProperty("title", "Bad Moon Rising"),
                            hasProperty("duration", "3:00")),
                            hasProperty("cost", BigDecimal.valueOf(0.99)),
                            hasProperty("artist", artist1))

                )

    }

    private Object hasSize(int i) {
    }

}
