import React from "react";
import axios from "axios";
import "./componentcss/SearchBarCss.css";

interface Artist {
    id: number;
    artistName: string;
}

interface Album {
    id: number;
    albumName: string;
}

interface Song {
    id: number;
    songName: string;
}

function SearchBar() {
    const [artists, setArtist] = React.useState<Artist[]>([]);
    const [albums, setAlbum] = React.useState<Album[]>([]);
    const [songs, setSong] = React.useState<Song[]>([]);

    const handleSearch = async (event: React.FormEvent<HTMLInputElement>) => {
        const query = (event.target as HTMLInputElement).value;
        if (query.length === 0) {
            const searchresult = document.querySelector(".searchresult");
            if (searchresult) {
                searchresult.innerHTML = "";
            }
            return;
        }

        const response = await axios.get(`http://localhost:8080/search/${query}`);
        const data = response.data;

        setArtist(data.artists);
        setAlbum(data.albums);
        setSong(data.songs)
    }

    return (
        <div>
            <input className="searchbar" onInput={handleSearch} placeholder="Search"/>
            <div className="searchresult">
                {artists.map((artist) => (
                    <div key={artist.id}>
                        <p>{artist.artistName}</p>
                    </div>
                ))}
                {albums.map((album) => (
                    <div key={album.id}>
                        <p>{album.albumName}</p>
                    </div>
                ))}
                {songs.map((song) => (
                    <div key={song.id}>
                        <p>{song.songName}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default SearchBar;