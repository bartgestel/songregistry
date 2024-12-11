package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.logic.interfaces.IReviewDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class AlbumService implements IAlbumService {
    private final IAlbumDAL albumDAL;
    private final IReviewDAL reviewDAL;

    @Autowired
    public AlbumService(IAlbumDAL albumDAL, IReviewDAL reviewDAL) {
        this.albumDAL = albumDAL;
        this.reviewDAL = reviewDAL;
    }

    public List<AlbumDTO> getAlbums() {
        return albumDAL.getAlbums();
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistName(String name) {
        return albumDAL.getAlbumsByArtistName(name);
    }

    @Override
    public List<AlbumDTO> getAlbumsByAlbumTitle(String title) {
        return albumDAL.getAlbumsByAlbumTitle(title);
    }

    @Override
    public List<AlbumDTO> getAlbumsForHome() {
        return albumDAL.getAlbumsForHome();
    }

    public AlbumDTO getAlbumById(int id) {
        AlbumDTO album = albumDAL.getAlbumById(id);
        List<SongDTO> songs = album.getAlbumSongs();

        OptionalDouble averageRating = songs.stream()
                .mapToDouble(song -> {
                    List<ReviewDTO> reviews = reviewDAL.getReviewsForSong(song.getId());
                    return reviews != null && !reviews.isEmpty()
                            ? reviews.stream().mapToDouble(ReviewDTO::getRating).average().orElse(0.0)
                            : 0.0;
                })
                .average();

        album.setAverageRating(averageRating.orElse(0.0));
        return album;
    }

    public void addAlbum(AlbumDTO albumDTO) {
        albumDAL.addAlbum(albumDTO);
    }
}