import {
  Sheet,
  SheetTrigger,
  SheetContent,
  SheetTitle,
  SheetHeader,
} from "@/components/ui/sheet.tsx";
import ReviewStars from "@/components/ReviewStars";

interface Review {
  id: number;
  review: string;
  rating: number;
}

interface ReviewSheetProps {
  reviews: Review[];
}

function ReviewSheet({ reviews }: ReviewSheetProps) {
  const reviewCount = reviews.length;

  return (
    <Sheet>
      <SheetTrigger>{reviewCount}</SheetTrigger>
      <SheetContent className="bg-slate-300 border-0">
        <SheetHeader>
          <SheetTitle>Reviews</SheetTitle>
        </SheetHeader>
        <div className="p-4">
          {reviews.map((review) => (
            <div key={review.id} className="p-2 mb-6 bg-white">
              <p>{review.review}</p>
              <ReviewStars rating={review.rating} />
            </div>
          ))}
        </div>
      </SheetContent>
    </Sheet>
  );
}

export default ReviewSheet;
