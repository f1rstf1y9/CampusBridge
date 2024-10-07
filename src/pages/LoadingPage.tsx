import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import ErrorModal from "@/components/ErrorModal";

export default function LoadingPage() {
  const location = useLocation();
  const navigate = useNavigate();
  const croppedImage = location.state?.croppedImage;

  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    if (!croppedImage) {
      setShowModal(true);
      setTimeout(() => {
        navigate("/");
      }, 3000);
    }
  }, [croppedImage, navigate]);

  return (
    <>
      {showModal && <ErrorModal />}
      {croppedImage && (
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
      )}
    </>
  );
}
