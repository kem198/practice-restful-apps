import { Button } from "@/components/ui/button";
import { EllipsisVertical, PlusIcon, SquareMousePointer } from "lucide-react";

export function TodosNavigation({}) {
  return (
    <div className="flex gap-2">
      <Button>
        <PlusIcon />
        Add Todo
      </Button>
      <Button variant="outline">
        <SquareMousePointer />
        Edit
      </Button>
      <div className="ml-auto">
        <Button variant="ghost" size="icon-lg" className="rounded-full">
          <EllipsisVertical />
        </Button>
      </div>
    </div>
  );
}
