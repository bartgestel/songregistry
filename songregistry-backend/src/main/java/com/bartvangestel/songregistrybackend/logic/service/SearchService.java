// src/main/java/com/bartvangestel/songregistrybackend/service/SearchService.java
package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistService;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongService;
import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.model.Song;
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
    public List<SearchResult> search(String search) {
        List<SearchResult> searchResults = new ArrayList<>();

        List<Artist> artists = artistService.getArtistByName(search);
        for (Artist artist : artists) {
            searchResults.add(new SearchResult("artist", artist.getId(), artist.getArtistName()));
        }

        List<Album> albums = albumService.getAlbumsByAlbumTitle(search);
        for (Album album : albums) {
            searchResults.add(new SearchResult("album", album.getId(), album.getAlbumName()));
        }

        List<Song> songs = songService.getSongsBySongTitle(search);
        for (Song song : songs) {
            searchResults.add(new SearchResult("song", song.getId(), song.getSongName()));
        }

        return searchResults;
    }

}