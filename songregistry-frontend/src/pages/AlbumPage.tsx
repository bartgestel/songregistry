import Navbar from "@/components/Navbar.tsx";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { Card, CardHeader, CardTitle } from "@/components/ui/card";
import { useNavigate } from "react-router-dom";
import ReviewStars from "@/components/ReviewStars.tsx";

interface Album {
  id: number;
  albumName: string;
  averageRating: number;
  albumSongs: Song[];
  albumArtists: Artist[];
}

interface Song {
  id: number;
  title: string;
}

interface Artist {
  id: number;
  artistName: string;
}

function AlbumPage() {
  const params = useParams();
  const id = params.albumId;
  const navigate = useNavigate();

  const [album, setAlbum] = useState<Album>();
  const [loading, setLoading] = useState<boolean>(true);

  const handleSongClick = (id: number) => {
    navigate(`/song/${id}`);
  };

  useEffect(() => {
    const fetchAlbum = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/albums/${id}`);
        setAlbum(response.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };
    fetchAlbum();
  }, [id]);

  return (
    <div>
      <div className="w-full justify-center">
        <Navbar />
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className="w-full flex flex-col justify-center items-center mt-8">
            <div className="w-4/5 flex" id="albumInfo">
              <img
                alt={album?.albumName}
                className="w-60 h-60"
                id="albumImage"
                src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
              />
              <div className="flex flex-col">
                <div className="block text-left ml-5">
                  <p className="text-4xl " id="albumName">
                    {album?.albumName}
                  </p>
                </div>
                <div className="block text-left ml-5">
                  <p id="albumArtists">
                    {album?.albumArtists
                      .map((artist) => artist.artistName)
                      .join(", ")}
                  </p>
                </div>
                <div className="block text-left ml-5" id="albumRating">
                  <ReviewStars rating={album?.averageRating ?? 0} />
                </div>
              </div>
            </div>
            <div className="w-4/5 flex flex-col mt-8" id="songs">
              <p className="text-4xl text-left">Songs</p>
              <div className="flex">
                {loading ? (
                  <p>Loading...</p>
                ) : (
                  <div className="flex mt-5 justify-between" id="albumSongs">
                    {album?.albumSongs.map((song) => (
                      <div key={album.id}>
                        <Card
                          onClick={() => handleSongClick(song.id)}
                          className="cursor-pointer albumSongCard"
                        >
                          <CardHeader>
                            <img
                              alt={song.title}
                              className="w-40"
                              src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
                            />
                            <CardTitle>{song.title}</CardTitle>
                          </CardHeader>
                        </Card>
                      </div>
                    ))}
                  </div>
                )}
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default AlbumPage;
