import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <div className="bg-slate-100 w-full h-full mx-auto">
        <Outlet />
      </div>
    </>
  );
}
