import Navbar from "@/components/Navbar.tsx";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { Card, CardHeader, CardTitle } from "@/components/ui/card.tsx";

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
  title: string;
}

function ArtistPage() {
  const params = useParams();
  const artistId = params.artistId;

  const [artist, setArtist] = useState<Artist>();
  const [loading, setLoading] = useState<boolean>(true);

  const [isExpanded, setIsExpanded] = useState<boolean>(false);
  const maxLength = 500;
  const bioText =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id nibh nulla. Maecenas interdum dignissim justo non pellentesque. Mauris tristique elit sit amet cursus tristique. Donec nunc sem, gravida sit amet lorem sit amet, blandit ornare risus. Donec quis vestibulum lorem. Mauris maximus arcu orci, vel maximus nulla bibendum vel. Phasellus a neque quam. Mauris iaculis nunc in tortor cursus, eget tincidunt neque hendrerit. Mauris tincidunt risus tristique lectus placerat vehicula. Nam maximus, urna in elementum pretium, augue orci placerat lorem, efficitur tempor elit eros id felis. Vivamus massa arcu, venenatis eu porta sit amet, commodo ut dui. Donec tortor augue, lacinia ac blandit vitae, tristique a massa. ";
  const toggleExpand = () => {
    setIsExpanded(!isExpanded);
  };

  const truncatedText =
    bioText.length > maxLength ? bioText.slice(0, maxLength) + "..." : bioText;

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
    <div className="w-full justify-center">
      <Navbar />
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="w-full flex flex-col justify-center items-center mt-8">
          <div className="w-4/5 flex">
            <img
              alt={artist?.artistName}
              className="w-60 h-60"
              src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
            />
            <div className="block text-left ml-5">
              <p className="text-4xl ">{artist?.artistName}</p>
              <p className="">
                {isExpanded ? bioText : truncatedText}
                {bioText.length > maxLength && (
                  <button
                    onClick={toggleExpand}
                    className="text-blue-500 ml-2 hover:underline"
                  >
                    {isExpanded ? "Less" : "More"}
                  </button>
                )}
              </p>
            </div>
          </div>
          <div className="w-4/5 flex flex-col mt-8">
            <p className="text-4xl text-left">Albums</p>
            <div className="flex">
              {loading ? (
                <p>Loading...</p>
              ) : (
                <div className="flex mt-5 justify-between">
                  {artist?.artistAlbums.map((album) => (
                    <div>
                      <Card>
                        <CardHeader>
                          <img
                            alt={album.albumName}
                            className="w-40"
                            src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
                          />
                          <CardTitle>{album.albumName}</CardTitle>
                        </CardHeader>
                      </Card>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
          <div className="w-4/5 flex flex-col mt-8">
            <p className="text-4xl text-left">Songs</p>
            <div className="flex">
              {loading ? (
                <p>Loading...</p>
              ) : (
                <div className="flex mt-5 justify-between">
                  {artist?.artistSongs.map((song) => (
                    <div>
                      <Card>
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
  );
}

export default ArtistPage;
