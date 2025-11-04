import { Hello } from "@/components/example/hello/hello";
import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";

describe("Hello", () => {
  it("should display 'Hello, World!' in h1 element", () => {
    render(<Hello />);

    const heading = screen.getByRole("heading", { level: 1 });
    expect(heading).toHaveTextContent("Hello, World!");
  });
});
