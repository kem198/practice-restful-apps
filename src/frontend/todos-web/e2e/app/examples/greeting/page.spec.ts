import { expect, test } from "@playwright/test";

test.describe("Greeting ページのテスト", () => {
  test.describe("初期表示のテスト", () => {
    test("Greeting ページが表示されること", async ({ page }) => {
      // Arrange
      await page.goto("/examples");

      // Capture screenshot before action
      await page.screenshot({
        path: "screenshots/examples/greeting/initial-test/display-greeting-before.png",
        fullPage: true,
      });

      // Act
      await page.getByRole("button", { name: "/greeting" }).click();

      // Assert
      await expect(
        page.getByRole("heading", { level: 1, name: "API Greeting Demo" }),
      ).toBeVisible();

      // Capture screenshot after action
      await page.screenshot({
        path: "screenshots/examples/greeting/initial-test/display-greeting-after.png",
        fullPage: true,
      });
    });

    test("文字列「Hello, World!」が表示されること", async ({ page }) => {
      // Arrange
      await page.goto("/examples");

      // Capture screenshot before action
      await page.screenshot({
        path: "screenshots/examples/greeting/initial-test/display-hello-world-before.png",
        fullPage: true,
      });

      // Act
      await page.getByRole("button", { name: "/greeting" }).click();

      // Assert
      await expect(page.getByText("Hello, World!").first()).toBeVisible();

      // Capture screenshot after action
      await page.screenshot({
        path: "screenshots/examples/greeting/initial-test/display-hello-world-after.png",
        fullPage: true,
      });
    });

    test("文字列「Hello, KeM198!」が表示されること", async ({ page }) => {
      // Act
      await page.goto("/examples/greeting");

      // Assert
      await expect(page.getByText("Hello, KeM198!").first()).toBeVisible();
    });
  });
});
