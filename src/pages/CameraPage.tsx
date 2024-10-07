import { useState, useEffect, useRef } from "react";
import Cropper, { ReactCropperElement } from "react-cropper";
import { Button } from "@radix-ui/themes";
import "cropperjs/dist/cropper.css";

import RotateCCW from "@/assets/icons/icon_rotate-ccw.svg";
import RotateCW from "@/assets/icons/icon_rotate-cw.svg";
import { useLocation, useNavigate } from "react-router-dom";

export default function CameraPage() {
  const location = useLocation();
  const navigate = useNavigate();
  const cropperRef = useRef<ReactCropperElement>(null);

  const [showModal, setShowModal] = useState(false);

  const selectedImage = location.state?.selectedImage;

  useEffect(() => {
    if (!selectedImage) {
      setShowModal(true);
      setTimeout(() => {
        navigate("/");
      }, 3000);
    }
  }, [selectedImage, navigate]);

  const onClick = () => {
    if (cropperRef.current) {
      const croppedCanvas = cropperRef.current.cropper.getCroppedCanvas();

      croppedCanvas.toBlob((blob) => {
        if (blob) {
          const file = new File([blob], "cropped-image.jpg", {
            type: "image/png",
          });

          navigate("/loading", {
            state: { croppedImage: file },
          });
        }
      }, "image/png");
    }
  };

  return (
    <>
      {showModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded-xl text-center shadow-clay-white-sm">
            <p className="text-lg font-semibold">
              사진이 정상적으로 업로드되지 않았습니다.
            </p>
            <p>잠시 후 메인화면으로 돌아갑니다.</p>
          </div>
        </div>
      )}

      {selectedImage && (
        <>
          <Cropper
            src={selectedImage ? URL.createObjectURL(selectedImage) : ""}
            style={{ height: "100vh", width: "100vw" }}
            initialAspectRatio={9 / 16}
            guides={true}
            minCropBoxHeight={100}
            minCropBoxWidth={100}
            background={false}
            responsive={true}
            rotatable={true}
            viewMode={1}
            // crop={onCrop}
            ref={cropperRef}
          />
          <div className="fixed z-1 top-4 right-4">
            <Button
              size="3"
              color="gray"
              variant="outline"
              className="w-[80px] bg-white"
              onClick={onClick}
            >
              확인
            </Button>
          </div>
          <div className="fixed z-1 h-24 bottom-0 w-lvw flex justify-center items-center gap-10">
            <button
              onClick={() => {
                if (cropperRef.current?.cropper) {
                  cropperRef.current?.cropper.rotate(-90);
                }
              }}
            >
              <img src={RotateCCW} alt="반시계 방향 90도 회전" width="30px" />
            </button>
            <button
              onClick={() => {
                if (cropperRef.current?.cropper) {
                  cropperRef.current?.cropper.rotate(90);
                }
              }}
            >
              <img src={RotateCW} alt="시계 방향 90도 회전" width="30px" />
            </button>
          </div>
        </>
      )}
    </>
  );
}
