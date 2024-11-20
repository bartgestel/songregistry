import {
  Sheet,
  SheetTrigger,
  SheetContent,
  SheetTitle,
  SheetHeader,
} from "@/components/ui/sheet.tsx";
import ReviewStars from "@/components/ReviewStars";
import ReviewField from "@/components/ReviewField";

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
      <SheetTrigger className="text-blue-500 underline ml-2">
        {reviewCount}
      </SheetTrigger>
      <SheetContent className="bg-slate-300 border-0">
        <SheetHeader>
          <SheetTitle>Reviews</SheetTitle>
        </SheetHeader>
        <ReviewField />
        <div className="mt-4">
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
