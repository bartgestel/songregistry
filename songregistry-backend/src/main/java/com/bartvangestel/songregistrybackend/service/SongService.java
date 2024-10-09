package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongsByArtistName(String name) {
        return songRepository.findBySongArtists_Artist_ArtistNameContainingIgnoreCase(name);
    }

    public List<Song> getSongsBySongTitle(String title) {
        return songRepository.findBySongNameContainingIgnoreCase(title);
    }
}
