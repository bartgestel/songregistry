import ReactStars from "react-rating-stars-component";

function ReviewStars(rating) {
  const reviewRating = rating.rating;

  return (
    <ReactStars
      count={5}
      value={reviewRating}
      size={36}
      isHalf={true}
      activeColor="#ffd700"
      edit={false}
    />
  );
}

export default ReviewStars;
