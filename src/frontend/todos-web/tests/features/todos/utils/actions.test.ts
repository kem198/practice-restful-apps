import { getTodos } from "@/features/todos/utils/actions";
import { beforeEach, describe, expect, it, MockInstance, vi } from "vitest";

describe("getTodos() のテスト", () => {
  process.env.API_BASE_URL = "http://localhost:8080";
  let fetchMock: MockInstance;
  const expectedResponse = [
    {
      todoId: "1",
      todoTitle: "タスクのタイトルタスクのタイトル",
      todoDescription:
        "タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明",
      finished: false,
      // TODO: 型定義 + Date をテスト可能にする
      // createdAt: new Date("2025-10-10"),
    },
    {
      todoId: "2",
      todoTitle: "タスクのタイトルタスクのタイトル",
      todoDescription:
        "タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明",
      finished: false,
      // createdAt: new Date("2025-10-10"),
    },
  ];

  beforeEach(() => {
    fetchMock = vi.spyOn(globalThis, "fetch");
    fetchMock.mockImplementation(
      async () =>
        new Response(JSON.stringify(expectedResponse), { status: 200 }),
    );
  });

  it("Todo[] を返すこと", async () => {
    // Arrange

    // Act
    const [todos] = await getTodos();

    // Assert
    expect(todos).toEqual(expectedResponse);
  });

  it("Response を返すこと", async () => {
    // Arrange

    // Act
    const [todos, response] = await getTodos();

    // Assert
    // TODO: もっとまともにする？
    expect(response).toBeDefined();
  });

  it("レスポンスが OK でなかったら例外をスローすること", async () => {
    // Arrange
    fetchMock.mockImplementationOnce(
      async () =>
        new Response('{"error":"server"}', {
          status: 500,
          statusText: "Internal Server Error",
        }),
    );

    // Act & Assert
    await expect(getTodos()).rejects.toThrow();
  });
});
