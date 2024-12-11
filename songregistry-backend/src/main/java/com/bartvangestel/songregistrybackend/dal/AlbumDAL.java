package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.model.AlbumArtist;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import com.bartvangestel.songregistrybackend.dal.repository.AlbumArtistRepository;
import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.dal.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumDAL implements IAlbumDAL {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumArtistRepository albumArtistRepository;

    public AlbumDAL(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository, AlbumArtistRepository albumArtistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumArtistRepository = albumArtistRepository;
    }

    public List<AlbumDTO> getAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtistName(String artistName) {
        List<Album> albums = albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(artistName);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByAlbumTitle(String albumTitle) {
        List<Album> albums = albumRepository.findByAlbumNameContainingIgnoreCase(albumTitle);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsForHome() {
        List<Album> albums = albumRepository.findTop6ByOrderByIdAsc();
        return albums.stream().map(this::convertToDTOWithArtist).collect(java.util.stream.Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsForArtist(int artistId) {
        List<Album> albums = albumRepository.findByAlbumArtists_Artist_Id(artistId);
        return albums.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public AlbumDTO getAlbumById(int id) {
        Album album = albumRepository.findById(id);
        return convertToDTOWithArtistsAndSongs(album);
    }

    public void addAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setAlbumName(albumDTO.getAlbumName());
        albumRepository.save(album);
        for(ArtistDTO artistDTO : albumDTO.getAlbumArtists()) {
            Artist artist = new Artist();
            AlbumArtist albumArtist = new AlbumArtist();
            artist.setArtistName(artistDTO.getArtistName());
            artist.setId(artistDTO.getId());
            albumArtist.setAlbum(album);
            albumArtist.setArtist(artist);
            albumArtistRepository.save(albumArtist);
        }
    }

    public AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        return albumDTO;
    }

    public AlbumDTO convertToDTOWithArtist(Album album) {
        List<Artist> artists = artistRepository.findByAlbumArtists_Album_Id(album.getId());
        List<ArtistDTO> artistDTOS = new ArrayList<>();
        for(Artist artist : artists) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(artist.getId());
            artistDTO.setArtistName(artist.getArtistName());
            artistDTOS.add(artistDTO);
        }
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        albumDTO.setAlbumArtists(artistDTOS);
        return albumDTO;
    }

    public AlbumDTO convertToDTOWithArtistsAndSongs(Album album) {
        List<Artist> artists = artistRepository.findByAlbumArtists_Album_Id(album.getId());
        List<Song> songs = songRepository.findByAlbum_Id(album.getId());
        List<ArtistDTO> artistDTOS = new ArrayList<>();
        List<SongDTO> songDTOS = new ArrayList<>();
        for(Artist artist : artists) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(artist.getId());
            artistDTO.setArtistName(artist.getArtistName());
            artistDTOS.add(artistDTO);
        }
        for(Song song : songs) {
            SongDTO songDTO = new SongDTO();
            songDTO.setId(song.getId());
            songDTO.setTitle(song.getTitle());
            songDTOS.add(songDTO);
        }
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        albumDTO.setAlbumArtists(artistDTOS);
        albumDTO.setAlbumSongs(songDTOS);
        return albumDTO;
    }
}
