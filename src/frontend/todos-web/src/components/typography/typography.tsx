import React from "react";

type TypographyH1Props = {
  children: React.ReactNode;
};

export function TypographyH1({ children }: TypographyH1Props) {
  return (
    <h1 className="scroll-m-20 text-center text-4xl font-extrabold tracking-tight text-balance">
      {children}
    </h1>
  );
}

type TypographyMutedProps = {
  children: React.ReactNode;
};

export function TypographyMuted({ children }: TypographyMutedProps) {
  return <p className="text-muted-foreground text-sm">{children}</p>;
}
