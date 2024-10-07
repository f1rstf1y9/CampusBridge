import { useNavigate } from "react-router-dom";

export default function Footer() {
  const navigate = useNavigate();

  return (
    <>
      <div className="flex w-full h-[50px] justify-center items-center fixed bottom-0 z-10">
        <div className="flex w-4/5 justify-between">
          <div onClick={() => navigate("/")}>홈</div>
          <div onClick={() => navigate("/history")}>히스토리</div>
          <div onClick={() => navigate("/profile")}>프로필</div>
        </div>
      </div>
    </>
  );
}
