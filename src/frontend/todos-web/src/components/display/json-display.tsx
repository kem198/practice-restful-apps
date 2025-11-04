import { Item, ItemContent } from "@/components/ui/item";

export const JsonDisplay = ({ data }: { data: unknown }) => {
  const jsonString = data ? JSON.stringify(data, undefined, 2) : " ";
  const lines = jsonString.split("\n");

  return (
    <Item variant="outline" className="bg-muted/50 w-full">
      <ItemContent>
        <pre className="cursor-text text-xs break-words whitespace-pre-wrap select-text">
          {lines.map((line, index) => (
            <span
              key={`${index}-${line}`}
              className="block hover:bg-gray-200/80"
            >
              {line}
            </span>
          ))}
        </pre>
      </ItemContent>
    </Item>
  );
};
