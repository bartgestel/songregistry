package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final SongService songService;

    public SearchService(ArtistService artistService, AlbumService albumService, SongService songService) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.songService = songService;
    }

    public SearchResult search(String search){
        List<Artist> artists = artistService.getArtistByName(search);
        List<Album> albums = albumService.getAlbumsByAlbumTitle(search);
        List<Song> songs = songService.getSongsBySongTitle(search);
        return new SearchResult(artists, albums, songs);
    }
}
