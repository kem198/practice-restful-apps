import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography";
import {
  GreetingCardConfig,
  GreetingClient,
} from "@/features/greeting/components/greeting-client";

export default function GreetingPage() {
  const cards: GreetingCardConfig[] = [
    {
      key: "default",
      title: "Default Greeting",
      description: "API: GET /v1/greeting/hello",
    },
    {
      key: "personalized",
      title: "Personalized Greeting",
      description: "API: GET /v1/greeting/hello?name=KeM198",
      name: "KeM198",
    },
  ];

  return (
    <AppPage>
      <TypographyH1>API Greeting Demo</TypographyH1>
      <GreetingClient cards={cards} />
    </AppPage>
  );
}
