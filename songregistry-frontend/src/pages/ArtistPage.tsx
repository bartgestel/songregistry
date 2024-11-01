import Navbar from "@/components/Navbar.tsx";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

interface Artist {
  id: number;
  artistName: string;
  artistAlbums: Album[];
  artistSongs: SongArtist[];
}

interface Album {
  id: number;
  albumName: string;
}

interface SongArtist {
  id: number;
  songName: string;
}

function ArtistPage() {
  const params = useParams();
  const artistId = params.artistId;

  const [artist, setArtist] = useState<Artist>();
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchArtist = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/artists/${artistId}`,
        );
        setArtist(response.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };
    fetchArtist();
  }, [artistId]);

  return (
    <div className="w-full">
      <Navbar />
      {loading ? <p>Loading...</p> : <p>{artist?.artistName}</p>}
    </div>
  );
}

export default ArtistPage;
