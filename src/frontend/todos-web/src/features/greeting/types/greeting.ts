import { SerializedResponse } from "@/types/common/response";

export type GreetingStatus = "pending" | "fulfilled" | "rejected";

export type GreetingResult = {
  status: GreetingStatus;
  greeting: string;
  responseData?: SerializedResponse;
  errorMessage?: string;
};
