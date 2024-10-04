import { useNavigate } from "react-router-dom";
import BackArrow from "@/assets/icons/icon_back-arrow.svg";

export default function BackHeader() {
  const navigate = useNavigate();

  return (
    <>
      <div className="flex w-full h-[60px] justify-center items-center fixed top-0 z-10">
        <div className="flex w-11/12 justify-start items-center gap-2">
          <div>
            <img src={BackArrow} alt="back" />
          </div>
          <div onClick={() => navigate(-1)}>뒤로가기</div>
        </div>
      </div>
    </>
  );
}
