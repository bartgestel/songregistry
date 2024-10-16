// src/main/java/com/bartvangestel/songregistrybackend/service/SearchService.java
package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.model.Song;
import com.bartvangestel.songregistrybackend.service.interfaces.ISearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService {
    private final ArtistServiceImpl artistServiceImpl;
    private final AlbumServiceImpl albumServiceImpl;
    private final SongServiceImpl songServiceImpl;

    public SearchServiceImpl(ArtistServiceImpl artistServiceImpl, AlbumServiceImpl albumServiceImpl, SongServiceImpl songServiceImpl) {
        this.artistServiceImpl = artistServiceImpl;
        this.albumServiceImpl = albumServiceImpl;
        this.songServiceImpl = songServiceImpl;
    }

    @Override
    public List<SearchResult> search(String search) {
        List<SearchResult> searchResults = new ArrayList<>();

        List<Artist> artists = artistServiceImpl.getArtistByName(search);
        for (Artist artist : artists) {
            searchResults.add(new SearchResult("artist", artist.getId(), artist.getArtistName()));
        }

        List<Album> albums = albumServiceImpl.getAlbumsByAlbumTitle(search);
        for (Album album : albums) {
            searchResults.add(new SearchResult("album", album.getId(), album.getAlbumName()));
        }

        List<Song> songs = songServiceImpl.getSongsBySongTitle(search);
        for (Song song : songs) {
            searchResults.add(new SearchResult("song", song.getId(), song.getSongName()));
        }

        return searchResults;
    }

}