import React, { useRef, useState } from "react";
import axios from "axios";

interface Album {
  albumName: string;
  albumArtists: Artist[];
}

interface Artist {
  id: number;
  artistName: string;
}

function AddAlbum() {
  const albumNameRef = useRef<HTMLInputElement>(null);
  const [albumArtists, setAlbumArtists] = useState<Artist[]>([]);
  const [searchResult, setSearchResult] = useState<Artist[]>([]);
  const [query, setQuery] = useState<string>("");

  const handleSearch = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const query = event.target.value;
    setQuery(query);

    if (query.length === 0) {
      setSearchResult([]);
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/search/${query}/artist`,
      );
      const data = response.data;

      setSearchResult(data);
    } catch {
      console.log("Error");
    }
  };

  const handleAddArtist = (artist: Artist) => {
    setAlbumArtists([...albumArtists, artist]);
    setSearchResult([]);
    setQuery("");
  };

  const handleRemoveArtist = (artist: Artist) => {
    const newArtists = albumArtists.filter((a) => a.id !== artist.id);
    setAlbumArtists(newArtists);
  };

  const handleAddAlbum = async () => {
    const albumName = albumNameRef.current?.value;
    const album: Album = {
      albumName: albumName || "",
      albumArtists: albumArtists,
    };
    await axios.post("http://localhost:8080/albums", album);
  };

  return (
    <div>
      <h1>Add Album</h1>
      <input
        type="text"
        placeholder="Album Name"
        className="border-2 p-1"
        ref={albumNameRef}
      />
      <div className="flex">
        <div>
          <h2>Add artist to album</h2>
          <input
            type="text"
            placeholder="Search for artist"
            className="border-2 p-1"
            onInput={handleSearch}
            value={query}
          />
          <div
            className={`searchresult ${searchResult.length > 0 ? "visible" : ""}`}
          >
            {searchResult.map((result) => (
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
          <h2>Selected artists</h2>
          {albumArtists.map((artist) => (
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
      </div>
      <div>
        <button
          onClick={() => handleAddAlbum()}
          className="bg-black text-white p-2 ml-2 rounded-md"
        >
          Add Album
        </button>
      </div>
    </div>
  );
}

export default AddAlbum;
