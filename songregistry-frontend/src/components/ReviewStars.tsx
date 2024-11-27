import ReactStars from "react-rating-stars-component";
import { Rating } from "react-simple-star-rating";

interface ReviewStarsProps {
  rating: number;
}
// https://mui.com/material-ui/react-rating/ hiermee maken komende keer
function ReviewStars({ rating }: ReviewStarsProps) {
  return (
    <Rating
      initialValue={rating}
      readonly={true}
      size={50}
      transition
      allowFraction
    />
  );
}

export default ReviewStars;
