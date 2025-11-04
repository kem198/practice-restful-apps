import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { ItemGroup } from "@/components/ui/item";
import { TodoItem } from "@/features/todos/components/todo-item";
import { createTodo } from "@/features/todos/utils/actions";
import { Todo } from "@/types/todo/todo";
import { PlusIcon } from "lucide-react";

type TodosClientProps = {
  initialTodos: Todo[];
};

export function TodosClient({ initialTodos }: TodosClientProps) {
  return (
    <div className="flex w-full flex-col gap-4">
      <form action={createTodo} className="flex gap-2">
        <Input name="todoTitle" type="text" placeholder="Type todo title..." />
        <Button type="submit">
          <PlusIcon />
          Add
        </Button>
      </form>
      <ItemGroup className="gap-4">
        {initialTodos.map((todo) => (
          <TodoItem key={todo.todoId} todo={todo} />
        ))}
      </ItemGroup>
    </div>
  );
}
