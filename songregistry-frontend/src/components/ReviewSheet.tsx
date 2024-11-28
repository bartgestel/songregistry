import {
  Sheet,
  SheetTrigger,
  SheetContent,
  SheetTitle,
  SheetHeader,
} from "@/components/ui/sheet.tsx";
import { ScrollArea } from "@/components/ui/scroll-area";
import ReviewStars from "@/components/ReviewStars";
import ReviewField from "@/components/ReviewField";
import { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

interface Review {
  id: number;
  review: string;
  rating: number;
}

interface ReviewSheetProps {
  reviews: Review[];
}

function ReviewSheet({ reviews }: ReviewSheetProps) {
  const params = useParams();
  const songId = params.songId;

  const [reviewList, setReviews] = useState<Review[]>([]);
  const [reviewCount, setReviewCount] = useState<number>(0);

  useEffect(() => {
    setReviews(reviews);
    setReviewCount(reviews.length);
  }, [reviews]);

  function reloadReviews() {
    // Reload reviews from backend
    axios
      .get(`http://localhost:8080/reviews/song/${songId}`)
      .then((response) => {
        setReviews(response.data);
        setReviewCount(response.data.length);
      });
  }

  return (
    <Sheet>
      <SheetTrigger className="text-blue-500 underline ml-2">
        {reviewCount}
      </SheetTrigger>
      <SheetContent className="bg-slate-200 border-0">
        <SheetHeader>
          <SheetTitle>Reviews</SheetTitle>
        </SheetHeader>
        <ReviewField reloadReviews={reloadReviews} />
        <ScrollArea className="h-[400px] w-[340px] rounded-md border-2 mt-4">
          <div className="mt-4">
            {reviewList.map((review) => (
              <div key={review.id} className="p-2 mb-6 bg-white">
                <p>{review.review}</p>
                <ReviewStars rating={review.rating} />
              </div>
            ))}
          </div>
        </ScrollArea>
      </SheetContent>
    </Sheet>
  );
}

export default ReviewSheet;
