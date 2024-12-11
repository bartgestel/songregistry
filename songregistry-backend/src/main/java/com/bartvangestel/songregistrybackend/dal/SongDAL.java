package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.ReviewDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import com.bartvangestel.songregistrybackend.dal.model.Review;
import com.bartvangestel.songregistrybackend.dal.model.SongArtist;
import com.bartvangestel.songregistrybackend.dal.repository.*;
import com.bartvangestel.songregistrybackend.logic.interfaces.ISongDAL;
import com.bartvangestel.songregistrybackend.dal.model.Song;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SongDAL implements ISongDAL {
    public final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final ReviewRepository reviewRepository;
    private final SongArtistRepository songArtistRepository;
    private final AlbumRepository albumRepository;

    public SongDAL(SongRepository songRepository, ArtistDAL artistDAL, ArtistRepository artistRepository, ReviewRepository reviewRepository, SongArtistRepository songArtistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.reviewRepository = reviewRepository;
        this.songArtistRepository = songArtistRepository;
        this.albumRepository = albumRepository;
    }

    public List<SongDTO> getSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByArtistName(String name) {
        List<Song> songs = songRepository.findBySongArtists_Artist_ArtistNameContainingIgnoreCase(name);
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsBySongTitle(String title) {
        List<Song> songs = songRepository.findBySongNameContainingIgnoreCase(title);
        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public SongDTO getSongById(int id) {
        Song song = songRepository.findById(id).get(0);
        return convertToDTOWithArtistsAndReviews(song);
    }

    public void addSong(SongDTO songDTO) {
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setAlbum(albumRepository.findById(songDTO.getAlbumId()));
        songRepository.save(song);
        for(ArtistDTO artistDTO : songDTO.getArtists()) {
            Artist artist = new Artist();
            SongArtist songArtist = new SongArtist();
            artist.setArtistName(artistDTO.getArtistName());
            artist.setId(artistDTO.getId());
            songArtist.setArtist(artist);
            songArtist.setSong(song);
            songArtistRepository.save(songArtist);
        }
    }

    public SongDTO convertToDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        return songDTO;
    }

    public SongDTO convertToDTOWithArtistsAndReviews(Song song) {
        List<Artist> artists = artistRepository.findBySongArtists_Song_Id(song.getId());
        List<Review> reviews = reviewRepository.findReviewBySongId(song.getId(), Sort.by(Sort.Direction.DESC, "id"));

        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        if (reviews != null) {
            for (Review review : reviews) {
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setReview(review.getText());
                reviewDTO.setRating(review.getRating());
                reviewDTOS.add(reviewDTO);
            }
        }

        List<ArtistDTO> artistDTOs = new ArrayList<>();
        for (Artist artist : artists) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(artist.getId());
            artistDTO.setArtistName(artist.getArtistName());
            artistDTOs.add(artistDTO);
        }

        return new SongDTO(song.getId(), song.getTitle(), artistDTOs, reviewDTOS);
    }
}
