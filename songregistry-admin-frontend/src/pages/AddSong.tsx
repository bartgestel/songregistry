import React, { useRef, useState } from "react";
import axios from "axios";

interface Album {
  albumName: string;
  id: number;
}

interface Artist {
  id: number;
  artistName: string;
}

interface Song {
  title: string;
  albumId: number;
  genreId: number;
  artists: Artist[];
}

function AddSong() {
  const songNameRef = useRef<HTMLInputElement>(null);
  const [songArtists, setSongArtists] = useState<Artist[]>([]);
  const [album, setAlbum] = useState<Album>();
  const [searchResultArtist, setSearchResultArtist] = useState<Artist[]>([]);
  const [searchResultAlbum, setSearchResultAlbum] = useState<Album[]>([]);
  const [artistQuery, setArtistQuery] = useState<string>("");
  const [albumQuery, setAlbumQuery] = useState<string>("");

  const handleArtistSearch = async (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const query = event.target.value;
    setArtistQuery(query);

    if (query.length === 0) {
      setSearchResultArtist([]);
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/search/${query}/artist`,
      );
      const data = response.data;

      setSearchResultArtist(data);
    } catch {
      console.log("Error");
    }
  };

  const handleAlbumSearch = async (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const query = event.target.value;
    setAlbumQuery(query);

    if (query.length === 0) {
      setSearchResultAlbum([]);
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/search/${query}/album`,
      );
      const data = response.data;

      setSearchResultAlbum(data);
    } catch {
      console.log("Error");
    }
  };

  const handleAddArtist = (artist: Artist) => {
    setSongArtists([...songArtists, artist]);
    setSearchResultArtist([]);
    setArtistQuery("");
  };

  const handleRemoveArtist = (artist: Artist) => {
    const newArtists = songArtists.filter((a) => a.id !== artist.id);
    setSongArtists(newArtists);
  };

  const handleAddAlbum = (album: Album) => {
    setAlbum(album);
    setSearchResultAlbum([]);
    setAlbumQuery("");
  };

  const handleRemoveAlbum = () => {
    setAlbum(undefined);
  };

  const handleAddSong = async () => {
    const songName = songNameRef.current?.value;
    const albumId = album?.id || 0;
    console.log(albumId);
    const song: Song = {
      title: songName || "",
      albumId: albumId,
      genreId: 1,
      artists: songArtists,
    };
    await axios.post("http://localhost:8080/songs", song);
  };

  return (
    <div>
      <h1>Add Song</h1>
      <input
        type="text"
        placeholder="Song Name"
        className="border-2 p-1"
        ref={songNameRef}
      />
      <div className="flex">
        <div>
          <h2>Add artist to song</h2>
          <input
            type="text"
            placeholder="Search for artist"
            className="border-2 p-1"
            onInput={handleArtistSearch}
            value={artistQuery}
          />
          <div
            className={`searchresult ${searchResultArtist.length > 0 ? "visible" : ""}`}
          >
            {searchResultArtist.map((result) => (
              <div
                className="searchitem"
                key={result.id}
                onClick={() => handleAddArtist(result)}
              >
                <p>{result.artistName}</p>
              </div>
            ))}
          </div>
        </div>
        <div>
          <h2>Add song to album</h2>
          <input
            type="text"
            placeholder="Search for artist"
            className="border-2 p-1"
            onInput={handleAlbumSearch}
            value={albumQuery}
          />
          <div
            className={`searchresult ${searchResultAlbum.length > 0 ? "visible" : ""}`}
          >
            {searchResultAlbum.map((result) => (
              <div
                className="searchitem"
                key={result.albumId}
                onClick={() => handleAddAlbum(result)}
              >
                <p>{result.albumName}</p>
              </div>
            ))}
          </div>
        </div>
        <div>
          <h2>Selected artists</h2>
          {songArtists.map((artist) => (
            <div key={artist.id} className="flex">
              <p>{artist.artistName}</p>
              <button
                className="ml-2"
                onClick={() => handleRemoveArtist(artist)}
              >
                X
              </button>
            </div>
          ))}
        </div>
        <div>
          <h2>Selected album</h2>
          {album && (
            <div className="flex">
              <p>{album.albumName}</p>
              <button className="ml-2" onClick={handleRemoveAlbum}>
                X
              </button>
            </div>
          )}
        </div>
      </div>
      <div>
        <button
          onClick={() => handleAddSong()}
          className="bg-black text-white p-2 ml-2 rounded-md"
        >
          Add Song
        </button>
      </div>
    </div>
  );
}

export default AddSong;
