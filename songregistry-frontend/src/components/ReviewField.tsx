import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import ReactStars from "react-rating-stars-component";

const starConfig = {
  count: 5,
  value: 0,
  size: 36,
  isHalf: true,
  activeColor: "#ffd700",
  edit: true,
};

function ReviewField() {
  return (
    <div className="flex flex-col space-y-2">
      <label htmlFor="review" className="text-sm font-semibold">
        Write a review
      </label>
      <ReactStars {...starConfig} />
      <Textarea
        id="review"
        className="resize-none bg-white"
        placeholder="Write your review here"
      />
      <Button className="bg-white hover:bg-slate-500">Post review</Button>
    </div>
  );
}

export default ReviewField;
