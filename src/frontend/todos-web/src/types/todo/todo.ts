export type Todo = {
  todoId: string;
  todoTitle: string;
  todoDescription?: string;
  finished: boolean;
  createdAt: Date;
};
