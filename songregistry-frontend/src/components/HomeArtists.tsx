import axios from "axios";
import { useState, useEffect } from "react";
import { Card, CardHeader, CardTitle } from "@/components/ui/card.tsx";

interface Artist {
  id: number;
  artistName: string;
}

function HomeArtists() {
  const [artists, setArtists] = useState<Artist[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await axios.get("http://localhost:8080/artists");
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
    <div className="text-left p-6">
      <p className="text-4xl">Artists</p>
      <div>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className="flex mt-5">
            {artists.map((artist) => (
              <Card className="mr-8">
                <CardHeader>
                  <img
                    className="w-40"
                    src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
                  />
                  <CardTitle>{artist.artistName}</CardTitle>
                </CardHeader>
              </Card>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default HomeArtists;