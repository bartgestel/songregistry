import Navbar from "@/components/Navbar.tsx";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ReviewStars from "@/components/ReviewStars.tsx";
import ReviewSheet from "@/components/ReviewSheet.tsx";
import axios from "axios";

interface Song {
  id: number;
  title: string;
  artists: Artist[];
  reviews: Review[];
}

interface Artist {
  id: number;
  artistName: string;
}

interface Review {
  id: number;
  review: string;
  rating: number;
}

function SongPage() {
  const params = useParams();
  const songId = params.songId;

  const [song, setSong] = useState<Song>();
  const [loading, setLoading] = useState<boolean>(true);
  const reviews = song?.reviews;

  const roundToHalf = (num: number) => {
    return Math.round(num * 2) / 2;
  };

  const averageRating =
    song?.reviews && song.reviews.length > 0
      ? roundToHalf(
          song.reviews.reduce((acc, review) => acc + review.rating, 0) /
            song.reviews.length,
        )
      : 0;

  useEffect(() => {
    const fetchSong = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/songs/${songId}`,
        );
        setSong(response.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };
    fetchSong();
  }, [songId]);
  return (
    <div className="w-full justify-center">
      <Navbar />
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="w-full flex flex-col justify-center items-center mt-8">
          <div className="w-4/5 flex">
            <img
              alt={song?.title}
              className="w-60 h-60"
              src="https://media.istockphoto.com/id/1147544807/vector/thumbnail-image-vector-graphic.jpg?s=612x612&w=0&k=20&c=rnCKVbdxqkjlcs3xH87-9gocETqpspHFXu5dIGB4wuM="
            />
            <div className="block text-left ml-5">
              <p className="text-4xl ">{song?.title}</p>
              <p className="">
                {song?.artists.map((artist) => artist.artistName).join(", ")}
              </p>
              <div className="flex items-center">
                <ReviewStars rating={averageRating} />
                <ReviewSheet reviews={reviews} />
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default SongPage;
