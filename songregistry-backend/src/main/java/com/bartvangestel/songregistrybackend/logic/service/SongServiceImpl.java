package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.service.interfaces.ISongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements ISongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getSongsByArtistName(String name) {
        return songRepository.findBySongArtists_Artist_ArtistNameContainingIgnoreCase(name);
    }
    @Override
    public List<Song> getSongsBySongTitle(String title) {
        return songRepository.findBySongNameContainingIgnoreCase(title);
    }
}
