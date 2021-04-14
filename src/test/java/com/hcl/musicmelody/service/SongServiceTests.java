package com.hcl.musicmelody.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.hcl.MusicMelody.services.SongService;

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
		Artist artist3 = new Artist("Fleet", "Maccc3");
		artist1.setArtistId(3L);
		Song song1 = new Song("Angel", "2:45", new BigDecimal("45.9"), artist1);
		song1.setId(1);
		Song song2 = new Song("All the Things of you are", "3:45", new BigDecimal("21.9"), artist2);
		song2.setId(2);
		Song song3 = new Song("Angel3", "2:89", new BigDecimal("67.9"), artist3);
		song1.setId(3);
		this.expectedSongs = new ArrayList<>(Arrays.asList(song1, song2, song3));

	}

	@Test
	public void getAllSongsTest() {
		when(songRepository.findAll()).thenReturn(expectedSongs);
		List<Song> allSongs = songService.getAllSongs();
		Assertions.assertEquals(3, allSongs.size());
	}



	@Test
	public void getSongByIdTest() {
		int id = 2;
		when(songRepository.findById(id)).thenReturn(Optional.ofNullable(expectedSongs.get(1)));
		Optional<Song> idSong = songService.getSongById(id);
		Assertions.assertEquals("All the Things of you are", idSong.get().getTitle());

	}

	@Test
	public void saveSongTest() {
		Artist artist1 = new Artist("Fleetwood", "Mac");
		Song song1 = new Song("Angel", "2:45", new BigDecimal("45.9"), artist1);
		when(songRepository.save(song1)).thenReturn(song1);
		Assertions.assertEquals(song1, songService.saveSong(song1));
	}

	@Test
	public void addUpdateSongTest() {
		Artist artist1 = new Artist("Fleetwood", "Mac");
		Song song1 = new Song("Angel", "2:45", new BigDecimal("45.9"), artist1);
		when(songRepository.save(song1)).thenReturn(song1);

		songService.addUpdateSong(song1);
		List<Song> allSongs = songService.getAllSongs();
		Assertions.assertEquals(3, allSongs.size());

	}

	@Test
	public void getSongByTitleNotNullTest() {

		String title = "Angel";

		when(songRepository.findByTitle(title)).thenReturn(Optional.ofNullable(expectedSongs.get(0)));

		Song titleSong = songService.GetByTitle(title);

		Assertions.assertEquals("Angel", titleSong.getTitle());

	}

	@Test
	public void SearchSongTest() {
		String keyword = "Angel";
		when(songRepository.findAll(keyword)).thenReturn(expectedSongs);
		List<Song> allSongs = songService.listAll("Angel");
		Assertions.assertEquals("Angel", allSongs.get(0).getTitle());
	}

	@Test
	public void convertDurationTest() {
		int mins1 = 3;
		int secs1 = 9;
		int seconds1 = secs1 % 60;
		int mins2 = 5;
		int secs2 = 16;
		int seconds2 = secs2 % 60;
		Assertions.assertEquals("3:09", String.valueOf(mins1) + ":" + String.valueOf("0" + seconds1));
		Assertions.assertEquals("5:16", String.valueOf(mins2) + ":" + String.valueOf(seconds2));
	}

}
