package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.dal.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.dal.repository.SongRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDAL implements IArtistDAL {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public ArtistDAL(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }


    public List<ArtistDTO> getArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public ArtistDTO getArtistById(int id) {
        Artist artist = artistRepository.findById(id);
        return convertToDTOWithConnections(artist);
    }

    @Override
    public List<ArtistDTO> getArtistsByName(String name) {
        List<Artist> artists = artistRepository.findByArtistNameContainingIgnoreCase(name);
        return artists.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<ArtistDTO> getArtistsForHome() {
        List<Artist> artists = artistRepository.findTop6ByOrderByIdAsc();
        return artists.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());

    }

    public List<ArtistDTO> getArtistsBySongId(Integer id) {
        List<Artist> artists = artistRepository.findBySongArtists_Song_Id(id);
        return artists.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<ArtistDTO> getArtistsByAlbumId(Integer id) {
        List<Artist> artists = artistRepository.findByAlbumArtists_Album_Id(id);
        return artists.stream().map(this::convertToDTO).collect(java.util.stream.Collectors.toList());
    }

    public ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setArtistName(artist.getArtistName());
        return artistDTO;
    }

    public ArtistDTO convertToDTOWithConnections(Artist artist) {
        List<AlbumDTO> albums = albumRepository.findByAlbumArtists_Artist_Id(artist.getId()).stream().map(album -> {
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setId(album.getId());
            albumDTO.setAlbumName(album.getAlbumName());
            return albumDTO;
        }).collect(java.util.stream.Collectors.toList());
        List<SongDTO> songs = songRepository.findBySongArtists_Artist_Id(artist.getId()).stream().map(song -> {
            SongDTO songDTO = new SongDTO();
            songDTO.setId(song.getId());
            songDTO.setTitle(song.getTitle());
            return songDTO;
        }).collect(java.util.stream.Collectors.toList());
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setArtistName(artist.getArtistName());
        artistDTO.setArtistAlbums(albums);
        artistDTO.setArtistSongs(songs);
        return artistDTO;
    }

}
