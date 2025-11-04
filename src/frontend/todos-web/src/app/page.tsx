import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function Home() {
  return (
    <AppPage>
      <TypographyH1>{"KeM's Todos"}</TypographyH1>
      <ul>
        <li>
          <Link href="/todos">
            <Button variant="link">/todos</Button>
          </Link>
        </li>
        <li>
          <Link href="/examples">
            <Button variant="link">/examples</Button>
          </Link>
        </li>
      </ul>
    </AppPage>
  );
}
