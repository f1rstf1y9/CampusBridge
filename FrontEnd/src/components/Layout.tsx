import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <div className="bg-slate-100 w-lvw h-lvh mx-auto">
        <Outlet />
      </div>
    </>
  );
}
