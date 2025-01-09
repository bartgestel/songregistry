import { useRef } from "react";
import axios from "axios";
import * as React from "react";

interface Artist {
  artistName: string;
}

function AddArtist() {
  const artistNameRef = useRef<HTMLInputElement>(null);

  async function handleAddArtist() {
    const artistName = artistNameRef.current?.value;
    if (!artistName) {
      const error = document.getElementById("error");
      if (error) {
        error.innerText = "Please fill out the artist name";
      }
      return;
    }
    const artist: Artist = {
      artistName: artistName || "",
    };
    await axios.post("http://localhost:8080/artists", artist);
  }

  return (
    <div className="flex flex-col items-center">
      <h1 className="text-2xl font-bold mb-4">Add Artist</h1>
      <div className="flex flex-col items-center mb-1">
        <label>Artist Name:</label>
        <input
          type="text"
          name="artistName"
          className="border-2 p-1"
          placeholder="artist name here"
          ref={artistNameRef}
        />
        <p className="text-red-600" id="error"></p>
      </div>
      <button
        onClick={() => handleAddArtist()}
        className="bg-black text-white p-2 ml-2 rounded-md"
      >
        Add Artist
      </button>
    </div>
  );
}

export default AddArtist;
