import { Rating } from "@mui/material";

interface ReviewStarsProps {
  rating: number;
}

function ReviewStars({ rating }: ReviewStarsProps) {
  console.log(rating);
  return (
    <Rating value={rating} precision={0.5} readOnly={true} size={"large"} />
  );
}

export default ReviewStars;
