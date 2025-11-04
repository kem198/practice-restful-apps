export type SerializedResponse = {
  headers: Record<string, string>;
  ok: boolean;
  redirected: boolean;
  status: number;
  statusText: string;
  type: string;
  url: string;
  body: string;
  bodyUsed: boolean;
};
