import { JsonDisplay } from "@/components/display/json-display";
import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography";
import { Separator } from "@/components/ui/separator";
import { TodosClient } from "@/features/todos/components/todos-client";
import { getTodos } from "@/features/todos/utils/actions";

export default async function TodosPage() {
  const [todos, info] = await getTodos();

  return (
    <AppPage className="mx-auto max-w-3xl">
      <TypographyH1>Todos</TypographyH1>
      <TodosClient initialTodos={todos} />

      <Separator />

      <JsonDisplay data={info} />
    </AppPage>
  );
}
