import { Hello } from "@/components/example/hello/hello";
import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography";

export default function HelloPage() {
  return (
    <AppPage>
      <TypographyH1>Hello, World!</TypographyH1>
      <div className="flex flex-col gap-2">
        <Hello />
        <Hello name="KeM198" />
      </div>
    </AppPage>
  );
}
