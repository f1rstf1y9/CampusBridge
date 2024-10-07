import { useNavigate } from "react-router-dom";
import BackArrow from "@/assets/icons/icon_back-arrow.svg";

export default function BackHeader() {
  const navigate = useNavigate();

  return (
    <>
      <div className="flex w-full h-[60px] justify-center items-center fixed top-0 z-10 bg-white">
        <div
          className="flex w-11/12 justify-start items-center gap-2"
          onClick={() => navigate(-1)}
        >
          <div>
            <img src={BackArrow} alt="back" />
          </div>
          <div>뒤로가기</div>
        </div>
      </div>
    </>
  );
}
