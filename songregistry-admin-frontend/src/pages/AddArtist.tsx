import { useRef } from "react";
import axios from "axios";

interface Artist {
  artistName: string;
}

function AddArtist() {
  const artistNameRef = useRef<HTMLInputElement>(null);

  async function handleAddArtist() {
    const artistName = artistNameRef.current?.value;
    const artist: Artist = {
      artistName: artistName || "",
    };
    await axios.post("http://localhost:8080/artists", artist);
  }

  return (
    <div>
      <h1>Add Artist</h1>
      <label>
        Artist Name:
        <input
          type="text"
          name="artistName"
          className="border-2 p-1"
          placeholder="artist name here"
          ref={artistNameRef}
        />
      </label>
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
