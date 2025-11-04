import type { FC } from "react";

export type HelloProps = {
  name?: string;
  className?: string;
};

export const Hello: FC<HelloProps> = ({ name = "World", className }) => {
  return (
    <div className={className} data-testid="hello-component">
      <h1>Hello, {name}!</h1>
    </div>
  );
};
