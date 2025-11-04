import { GreetingResult } from "@/features/greeting/types/greeting";
import { SerializedResponse } from "@/types/common/response";

type CreateResponseDataArgs = {
  res: Response;
  body: string;
};

type CreateErrorResponseDataArgs = {
  status: number;
  statusText: string;
  url: string;
  body: string;
};

const createResponseData = ({ res, body }: CreateResponseDataArgs) =>
  ({
    headers: Object.fromEntries(res.headers.entries()),
    ok: res.ok,
    redirected: res.redirected,
    status: res.status,
    statusText: res.statusText,
    type: res.type,
    url: res.url,
    body,
    bodyUsed: res.bodyUsed,
  }) satisfies SerializedResponse;

const createErrorResponseData = ({
  status,
  statusText,
  url,
  body,
}: CreateErrorResponseDataArgs) =>
  ({
    headers: {},
    ok: false,
    redirected: false,
    status,
    statusText,
    type: "error",
    url,
    body,
    bodyUsed: false,
  }) satisfies SerializedResponse;

const createRejectedResult = (
  errorMessage: string,
  responseData: SerializedResponse,
): GreetingResult => ({
  status: "rejected",
  greeting: "",
  responseData,
  errorMessage,
});

export async function getGreeting(name?: string): Promise<GreetingResult> {
  const apiBaseUrl = process.env.API_BASE_URL;
  if (!apiBaseUrl) {
    const message = "API_BASE_URL environment variable is not set.";
    return createRejectedResult(
      message,
      createErrorResponseData({
        status: 0,
        statusText: "Configuration Error",
        url: "",
        body: message,
      }),
    );
  }

  const searchParams = new URLSearchParams();
  if (name) {
    searchParams.append("name", name);
  }

  const querySuffix =
    searchParams.size > 0 ? `?${searchParams.toString()}` : "";
  const url = `${apiBaseUrl}/v1/greeting/hello${querySuffix}`;

  try {
    const res = await fetch(url, { cache: "no-store" });
    const bodyText = await res.text();
    const responseData = createResponseData({ res, body: bodyText });

    if (!res.ok) {
      return createRejectedResult(
        `Failed to fetch greeting. Status: ${res.status}`,
        responseData,
      );
    }

    return {
      status: "fulfilled",
      greeting: bodyText,
      responseData,
    } satisfies GreetingResult;
  } catch (error) {
    const errorMessage =
      error instanceof Error ? error.message : "Unknown greeting error.";

    return createRejectedResult(
      errorMessage,
      createErrorResponseData({
        status: 0,
        statusText: "Fetch Error",
        url,
        body: errorMessage,
      }),
    );
  }
}
