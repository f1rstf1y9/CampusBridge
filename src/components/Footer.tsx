import { useNavigate, useLocation } from "react-router-dom";

import IconHome from "@/assets/icons/icon_home.svg";
import IconFolder from "@/assets/icons/icon_folder.svg";
import IconUser from "@/assets/icons/icon_user.svg";

export default function Footer() {
  const navigate = useNavigate();
  const location = useLocation();

  const isActive = (path: string) => location.pathname === path;

  return (
    <>
      <div className="flex w-full h-[60px] justify-center items-center fixed bottom-0 z-10 bg-slate-50 rounded-t-xl shadow-[0_0_10px_rgba(0,0,0,0.1)]">
        <div className="flex w-4/5 justify-between">
          <div
            onClick={() => navigate("/")}
            className={`flex flex-col justify-center items-center w-[30px] ${
              isActive("/") ? "bg-radial-sky brightness-95" : ""
            }`}
          >
            <img src={IconHome} alt="home" />
            <span className="text-xs text-sky-400 mt-1 font-bold">홈</span>
          </div>
          <div
            onClick={() => navigate("/history")}
            className={`flex flex-col justify-center items-center ${
              isActive("/history") ? "bg-radial-sky brightness-95" : ""
            }`}
          >
            <img src={IconFolder} alt="history" />
            <span className="text-xs text-sky-400 mt-1 font-bold">
              히스토리
            </span>
          </div>
          <div
            onClick={() => navigate("/profile")}
            className={`flex flex-col justify-center items-center ${
              isActive("/profile") ? "bg-radial-sky brightness-95" : ""
            }`}
          >
            <img src={IconUser} alt="profile" />
            <span className="text-xs text-sky-400 mt-1 font-bold">프로필</span>
          </div>
        </div>
      </div>
    </>
  );
}
