"use client";

import { GreetingCard } from "@/features/greeting/components/greeting-card";
import type { GreetingResult } from "@/features/greeting/types/greeting";
import { SerializedResponse } from "@/types/common/response";
import { useEffect, useState } from "react";

export type GreetingCardConfig = {
  key: string;
  title: string;
  description: string;
  name?: string;
};

type GreetingCardState = GreetingResult;

type GreetingClientProps = {
  cards: GreetingCardConfig[];
};

export function GreetingClient({ cards }: GreetingClientProps) {
  const [cardStates, setCardStates] = useState<
    Record<string, GreetingCardState>
  >(() => createInitialStates(cards));

  useEffect(() => {
    let isMounted = true;

    setCardStates(createInitialStates(cards));

    const fetchCards = async () => {
      for (const card of cards) {
        const result = await fetchGreetingState(card.name);
        if (!isMounted) {
          return;
        }

        setCardStates((previous) => ({
          ...previous,
          [card.key]: result,
        }));
      }
    };

    void fetchCards();

    return () => {
      isMounted = false;
    };
  }, [cards]);

  return (
    <div className="flex flex-col gap-8">
      {cards.map((card) => {
        const state = cardStates[card.key] ?? createDefaultState();
        return (
          <GreetingCard
            key={card.key}
            title={card.title}
            description={card.description}
            greeting={state.greeting}
            status={state.status}
            responseData={state.responseData}
            errorMessage={state.errorMessage}
          />
        );
      })}
    </div>
  );
}

const createInitialStates = (cards: GreetingCardConfig[]) => {
  const map: Record<string, GreetingCardState> = {};
  for (const card of cards) {
    map[card.key] = createDefaultState();
  }
  return map;
};

const createDefaultState = (): GreetingCardState => ({
  status: "pending",
  greeting: "",
  responseData: undefined,
  errorMessage: undefined,
});

const fetchGreetingState = async (
  name?: string,
): Promise<GreetingCardState> => {
  const searchParams = new URLSearchParams();
  if (name) {
    searchParams.append("name", name);
  }

  const querySuffix =
    searchParams.size > 0 ? `?${searchParams.toString()}` : "";
  const url = `/examples/greeting/api/${querySuffix}`;

  try {
    const res = await fetch(url, { cache: "no-store" });
    const body = (await res.json()) as GreetingCardState;

    if (!res.ok) {
      return {
        status: "rejected",
        greeting: "",
        responseData: body.responseData,
        errorMessage:
          body.errorMessage ??
          `Failed to fetch greeting. Status: ${res.status}`,
      };
    }

    return body;
  } catch (error) {
    const message =
      error instanceof Error ? error.message : "Unknown greeting error.";

    return {
      status: "rejected",
      greeting: "",
      errorMessage: message,
      responseData: createErrorResponseData({
        status: 0,
        statusText: "Fetch Error",
        url,
        body: message,
      }),
    };
  }
};

const createErrorResponseData = ({
  status,
  statusText,
  url,
  body,
}: {
  status: number;
  statusText: string;
  url: string;
  body: string;
}): SerializedResponse => ({
  headers: {},
  ok: false,
  redirected: false,
  status,
  statusText,
  type: "error",
  url,
  body,
  bodyUsed: false,
});
