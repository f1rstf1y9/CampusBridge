import { useLocation } from "react-router-dom";

export default function LoadingPage() {
  const location = useLocation();
  const croppedImage = location.state?.croppedImage;

  return (
    <>
      <div className="wmx-auto flex justify-center items-center h-lvh">
        <div className="fixed z-10 text-center">
          <iframe src="https://lottie.host/embed/a807f7e6-9b39-48ef-9e50-183909786a6a/3dS6Zu16YD.json"></iframe>{" "}
          <span className="text-white font-bold drop-shadow">번역 중...</span>
        </div>
        <img
          src={croppedImage ? URL.createObjectURL(croppedImage) : ""}
          width="300px"
          className="rounded brightness-50 opacity-60"
        />
      </div>
    </>
  );
}
