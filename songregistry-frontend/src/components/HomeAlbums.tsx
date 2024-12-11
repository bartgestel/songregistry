import axios from "axios";
import { useState, useEffect } from "react";
import {
  Card,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card.tsx";
import { useNavigate } from "react-router-dom";

interface Artist {
  id: number;
  artistName: string;
}

interface Album {
  id: number;
  albumName: string;
  albumArtists: Artist[];
}

function HomeArtists() {
  const [albums, setAlbums] = useState<Album[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  const handleClick = (id: number) => {
    navigate(`/album/${id}`);
  };

  useEffect(() => {
    const fetchAlbums = async () => {
      try {
        const response = await axios.get("http://localhost:8080/albums/home");
        setAlbums(response.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };
    fetchAlbums();
  }, []);
  return (
    <div className="text-left p-6" id="homeAlbums">
      <p className="text-4xl">Albums</p>
      <div>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className="flex mt-5 justify-between">
            {albums.map((album) => (
              <div>
                <Card
                  onClick={() => handleClick(album.id)}
                  className="cursor-pointer albumCard"
                >
                  <CardHeader>
                    <img
                      alt={album.albumName}
                      className="w-40"
                      src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
                    />
                    <CardTitle>{album.albumName}</CardTitle>
                    <CardDescription>
                      {album.albumArtists.map((artist, index) => (
                        <span key={artist.id}>
                          {artist.artistName}
                          {index < album.albumArtists.length - 1 && ", "}
                        </span>
                      ))}
                    </CardDescription>
                  </CardHeader>
                </Card>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default HomeArtists;
