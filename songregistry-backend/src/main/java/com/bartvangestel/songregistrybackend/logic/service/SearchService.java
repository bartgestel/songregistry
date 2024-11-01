// src/main/java/com/bartvangestel/songregistrybackend/service/SearchService.java
package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistService;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import com.bartvangestel.songregistrybackend.DTO.SearchResultDTO;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private final IArtistService artistService;

    @Autowired
    private final IAlbumService albumService;

    @Autowired
    private final ISongService songService;

    public SearchService(IArtistService artistService, IAlbumService albumService, ISongService songService) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.songService = songService;
    }

    @Override
    public List<SearchResultDTO> search(String search) {
        List<SearchResultDTO> searchResultDTOS = new ArrayList<>();

        List<ArtistDTO> artists = artistService.getArtistByName(search);
        for (ArtistDTO artist : artists) {
            searchResultDTOS.add(new SearchResultDTO("artist", artist.getId(), artist.getArtistName()));
        }

        List<AlbumDTO> albums = albumService.getAlbumsByAlbumTitle(search);
        for (AlbumDTO album : albums) {
            searchResultDTOS.add(new SearchResultDTO("album", album.getId(), album.getAlbumName()));
        }

        List<SongDTO> songs = songService.getSongsBySongTitle(search);
        for (SongDTO song : songs) {
            searchResultDTOS.add(new SearchResultDTO("song", song.getId(), song.getTitle()));
        }

        return searchResultDTOS;
    }

}