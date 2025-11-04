import { Badge } from "@/components/ui/badge";
import {
  Item,
  ItemActions,
  ItemContent,
  ItemDescription,
  ItemFooter,
  ItemMedia,
  ItemTitle,
} from "@/components/ui/item";
import { TodoToggle } from "@/features/todos/components/todo-toggle";
import { Todo } from "@/types/todo/todo";
import { format } from "date-fns";
import { ja } from "date-fns/locale";
import {
  BellIcon,
  CalendarClockIcon,
  Equal,
  LinkIcon,
  MapPinIcon,
} from "lucide-react";

type TodosItemProps = {
  todo: Todo;
};

export function TodoItem({ todo }: TodosItemProps) {
  return (
    <Item variant="outline">
      <ItemMedia className="flex items-center">
        <TodoToggle aria-label="TodoToggle" todo={todo} />
      </ItemMedia>

      <ItemContent>
        <ItemTitle>{todo.todoTitle}</ItemTitle>
        {todo.todoDescription && (
          <ItemDescription>{todo.todoDescription}</ItemDescription>
        )}
      </ItemContent>

      <ItemActions className="self-start">
        <Equal className="size-4 cursor-grab text-gray-400" />
      </ItemActions>

      <ItemFooter className="pl-12">
        <div className="flex gap-2">
          <Badge variant="outline">
            <CalendarClockIcon />
            {format(todo.createdAt, "M月d日 (E)", { locale: ja })}
          </Badge>
          <Badge variant="outline">
            <BellIcon />3
          </Badge>
          <Badge variant="outline">
            <MapPinIcon />
            Fukuoka, Japan
          </Badge>
          <Badge variant="outline">
            <LinkIcon />
          </Badge>
        </div>
      </ItemFooter>
    </Item>
  );
}
