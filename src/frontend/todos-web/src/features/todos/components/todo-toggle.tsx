"use client";

import { Toggle } from "@/components/ui/toggle";
import { cn } from "@/lib/utils";
import { Todo } from "@/types/todo/todo";
import { CheckIcon } from "lucide-react";
import { useState, type ComponentProps } from "react";

type ToggleProps = ComponentProps<typeof Toggle>;

type TodoToggleProps = {
  todo: Todo;
} & ToggleProps;

export function TodoToggle({ todo, className, ...rest }: TodoToggleProps) {
  const [checked, setChecked] = useState(!!todo.finished);

  return (
    <Toggle
      pressed={checked}
      onPressedChange={setChecked}
      variant="outline"
      className={cn(
        `data-[state=on]:bg-primary cursor-pointer rounded-full`,
        className,
      )}
      {...rest}
    >
      {checked && <CheckIcon className="text-primary-foreground" />}
    </Toggle>
  );
}
