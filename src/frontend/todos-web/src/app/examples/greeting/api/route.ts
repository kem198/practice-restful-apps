import { getGreeting } from "@/features/greeting/utils/actions";
import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest) {
  const { searchParams } = new URL(request.url);
  const name = searchParams.get("name") ?? undefined;

  const result = await getGreeting(name);

  return NextResponse.json(result);
}
