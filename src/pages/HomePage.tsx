import { useNavigate } from "react-router-dom";

export default function HomePage() {
  const navigate = useNavigate();

  return (
    <>
      <div className="text-center w-8/12 h-full flex flex-col justify-center mx-auto">
        <h1 className="text-4xl font-bold mb-24 text-sky-500">Campus Bridge</h1>
        <div className="flex flex-col gap-4 mb-12">
          <button
            className="w-full h-24 bg-sky-300 rounded-lg"
            onClick={() => navigate("/camera")}
          >
            카메라
          </button>
          <button
            onClick={() => navigate("/gallery")}
            className="w-full h-24 bg-sky-400 rounded-lg"
          >
            앨범
          </button>
        </div>
      </div>
      <div className="flex w-full h-[50px] justify-center items-center fixed bottom-0 z-10">
        <div className="flex w-4/5 justify-between">
          <div onClick={() => navigate("/")}>홈</div>
          <div onClick={() => navigate("/")}>히스토리</div>
          <div onClick={() => navigate("/")}>프로필</div>
        </div>
      </div>
    </>
  );
}
