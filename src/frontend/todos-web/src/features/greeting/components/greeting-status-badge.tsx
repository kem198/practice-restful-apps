import { GreetingStatus } from "@/features/greeting/types/greeting";

export const GreetingStatusBadge = ({ status }: { status: GreetingStatus }) => {
  const statusStyles: Record<
    GreetingStatus,
    { className: string; text: string }
  > = {
    pending: {
      className: "text-blue-600",
      text: "Pending",
    },
    fulfilled: {
      className: "text-green-600",
      text: "Fulfilled",
    },
    rejected: {
      className: "text-red-600",
      text: "Rejected",
    },
  };

  const { className, text } = statusStyles[status];

  return (
    <div
      className={`w-fit rounded bg-gray-100 px-4 py-2 text-sm font-semibold ${className}`}
    >
      {text}
    </div>
  );
};
