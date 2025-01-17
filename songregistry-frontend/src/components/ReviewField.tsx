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
    const token = localStorage.getItem("token");
    console.log(token);
    if (reviewTextRef.current?.value === "") {
      const error = document.getElementById("error");
      error!.textContent = "Review cannot be empty";
      return;
    }
    if (ratingValue === 0) {
      const error = document.getElementById("error");
      error!.textContent = "Rating cannot be empty";
      return;
    }
    if (token === null) {
      const error = document.getElementById("error");
      error!.textContent = "You must be logged in to post a review";
      return;
    }
    const review: Review = {
      review: reviewTextRef.current?.value || "",
      rating: ratingValue,
      songId: songId,
    };
    // Post review to backend
    await axios.post("http://localhost:8080/reviews", review, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
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
      <p id="error" className="text-red-600"></p>
      <Button className=" hover:bg-slate-500" onClick={postReview}>
        Post review
      </Button>
    </div>
  );
}

export default ReviewField;
