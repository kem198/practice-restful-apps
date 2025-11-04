import { cn } from "@/lib/utils";
import React, { HTMLAttributes } from "react";

type PageProps = {
  children: React.ReactNode;
  className?: string;
} & HTMLAttributes<HTMLElement>;

export function AppPage({ children, className, ...props }: PageProps) {
  return (
    <main
      className={cn(
        "flex flex-col items-center gap-4 sm:items-start",
        className,
      )}
      {...props}
    >
      {children}
    </main>
  );
}
