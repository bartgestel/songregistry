package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {
    private final ISongDAL songDAL;

    public SongService(ISongDAL songDAL) {
        this.songDAL = songDAL;
    }

    public List<SongDTO> getSongs() {
        List<Song> songs = songDAL.getSongs();
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SongDTO> getSongsByArtistName(String name) {
        List<Song> songs = songDAL.getSongsByArtistName(name);
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public List<SongDTO> getSongsBySongTitle(String title) {
        List<Song> songs = songDAL.getSongsBySongTitle(title);
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SongDTO convertToDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        return songDTO;
    }
}
