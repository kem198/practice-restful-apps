"use server";

import { SerializedResponse } from "@/types/common/response";
import { Todo } from "@/types/todo/todo";
import { revalidatePath } from "next/cache";

export const getTodos = async (): Promise<[Todo[], SerializedResponse]> => {
  const apiBaseUrl = process.env.API_BASE_URL;
  if (!apiBaseUrl) {
    throw new Error("API_BASE_URL environment variable is not set.");
  }

  const res = await fetch(`${apiBaseUrl}/v1/todos`, {
    method: "GET",
    cache: "no-store",
  });
  if (!res.ok) {
    throw new Error(`Failed to fetch todos. Status: ${res.status}`);
  }

  const data = await res.json();

  const sortedData = data.toSorted((a: Todo, b: Todo) => {
    // Sort by finished (false first), then createdAt (desc)
    const finishedCompare = Number(a.finished) - Number(b.finished);
    if (finishedCompare !== 0) {
      return finishedCompare;
    }
    return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
  });

  const info: SerializedResponse = {
    headers: Object.fromEntries(res.headers.entries()),
    ok: res.ok,
    redirected: res.redirected,
    status: res.status,
    statusText: res.statusText,
    type: res.type,
    url: res.url,
    body: data,
    bodyUsed: res.bodyUsed,
  };

  return [sortedData, info];
};

export const createTodo = async (formData: FormData): Promise<void> => {
  const apiBaseUrl = process.env.API_BASE_URL;
  if (!apiBaseUrl) {
    throw new Error("API_BASE_URL environment variable is not set.");
  }

  const res = await fetch(`${apiBaseUrl}/v1/todos`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      todoTitle: formData.get("todoTitle"),
      todoDescription: formData.get("todoDescription"),
    }),
    cache: "no-store",
  });

  if (!res.ok) {
    throw new Error(`Failed to add todos. Status: ${res.status}`);
  }

  revalidatePath("/todos");
};
