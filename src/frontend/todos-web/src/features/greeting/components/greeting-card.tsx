import { JsonDisplay } from "@/components/display/json-display";
import { GreetingStatusBadge } from "@/features/greeting/components/greeting-status-badge";
import { GreetingStatus } from "@/features/greeting/types/greeting";
import { SerializedResponse } from "@/types/common/response";

type GreetingCardProps = {
  title: string;
  description: string;
  greeting: string;
  status: GreetingStatus;
  responseData?: SerializedResponse;
  errorMessage?: string;
};

export function GreetingCard({
  title,
  description,
  greeting,
  status,
  responseData,
  errorMessage,
}: GreetingCardProps) {
  let messageContent = greeting;
  switch (status) {
    case "pending":
      messageContent = "Pending...";
      break;
    case "rejected":
      messageContent = errorMessage ?? "Rejected while fetching greeting.";
      break;
    default:
      messageContent = greeting;
      break;
  }

  return (
    <section className="flex flex-col gap-4 rounded border border-gray-200 p-4">
      <header className="flex flex-col gap-2">
        <h2 className="text-lg font-semibold text-slate-800">{title}</h2>
        <p className="text-sm text-gray-600">{description}</p>
        <GreetingStatusBadge status={status} />
      </header>

      <div className="flex flex-col gap-4 rounded bg-gray-100 p-4">
        <div className="flex w-full flex-col gap-1">
          <h3 className="text-sm font-semibold">Message:</h3>
          <p className="text-lg">{messageContent}</p>
        </div>
        <div className="flex w-full flex-col gap-1">
          <h3 className="text-sm font-semibold">Full Response:</h3>
          <JsonDisplay data={responseData} />
        </div>
      </div>
    </section>
  );
}
