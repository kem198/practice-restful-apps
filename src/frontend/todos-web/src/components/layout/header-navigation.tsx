"use client";

import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
} from "@/components/ui/navigation-menu";
import Link from "next/link";

export function HeaderNavigation() {
  return (
    <NavigationMenu>
      <NavigationMenuList>
        <NavigationMenuItem>
          <NavigationMenuLink
            asChild
            className="hover:bg-white/10 hover:text-white focus:bg-white/10 focus:text-white"
          >
            {/* リンクをクリック後、フォーカスが当たったままになるため明示的に外す */}
            <Link href="/" onClick={(e) => e.currentTarget.blur()}>
              {"KeM's Todos"}
            </Link>
          </NavigationMenuLink>
        </NavigationMenuItem>
      </NavigationMenuList>
    </NavigationMenu>
  );
}
