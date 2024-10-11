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
    const [query, setQuery] = React.useState<string>("");

    const handleSearch = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const query = event.target.value;
        setQuery(query);

        if(query.length === 0) {
            setArtist([]);
            setAlbum([]);
            setSong([]);
            return;
        }

        try{
            const response = await axios.get(`http://localhost:8080/search/${query}`);
            const data = response.data;

            setArtist(data.artists);
            setAlbum(data.albums);
            setSong(data.songs);
        }catch{
            console.log("Error");
        }
    }

    return (
        <div>
            <input className="searchbar" value={query} onInput={handleSearch} placeholder="Search"/>
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