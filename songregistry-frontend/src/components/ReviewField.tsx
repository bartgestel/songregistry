import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import ReactStars from "react-rating-stars-component";
import { useRef, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

interface Review {
  review: string;
  rating: number;
  songId: number;
}

function ReviewField({ reloadReviews }: { reloadReviews: () => void }) {
  const params = useParams();
  const reviewTextRef = useRef<HTMLTextAreaElement>(null);

  const songId: number = parseInt(params.songId || "0");
  const [ratingValue, setRatingValue] = useState<number>(0);

  const ratingChanged = (newRating: number) => {
    setRatingValue(newRating);
    console.log(newRating);
  };

  const starConfig = {
    count: 5,
    value: ratingValue,
    size: 36,
    isHalf: true,
    activeColor: "#ffd700",
    edit: true,
    onChange: ratingChanged,
  };

  async function postReview() {
    const review: Review = {
      review: reviewTextRef.current?.value || "",
      rating: ratingValue,
      songId: songId,
    };
    console.log(review);
    // Post review to backend
    await axios.post("http://localhost:8080/reviews", review);
    reloadReviews();
  }
  return (
    <div className="flex flex-col space-y-2">
      <label htmlFor="review" className="text-sm font-semibold">
        Write a review
      </label>
      <ReactStars {...starConfig} />
      <Textarea
        ref={reviewTextRef}
        id="review"
        className="resize-none bg-white"
        placeholder="Write your review here"
      />
      <Button className="bg-white hover:bg-slate-500" onClick={postReview}>
        Post review
      </Button>
    </div>
  );
}

export default ReviewField;
