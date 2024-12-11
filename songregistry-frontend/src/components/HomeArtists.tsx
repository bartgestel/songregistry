import axios from "axios";
import { useState, useEffect } from "react";
import { Card, CardHeader, CardTitle } from "@/components/ui/card.tsx";
import { useNavigate } from "react-router-dom";

interface Artist {
  id: number;
  artistName: string;
}

function HomeArtists() {
  const [artists, setArtists] = useState<Artist[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  const handleClick = (id: number) => {
    navigate(`/artist/${id}`);
  };

  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await axios.get("http://localhost:8080/artists/home");
        setArtists(response.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };
    fetchArtists();
  }, []);

  return (
    <div className="text-left p-6" id="homeArtists">
      <p className="text-4xl">Artists</p>
      <div>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className="flex mt-5 justify-between">
            {artists.map((artist) => (
              <div key={artist.id}>
                <Card
                  onClick={() => handleClick(artist.id)}
                  className="cursor-pointer artistCard"
                >
                  <CardHeader>
                    <img
                      alt={artist.artistName}
                      className="w-40"
                      src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
                    />
                    <CardTitle>{artist.artistName}</CardTitle>
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
