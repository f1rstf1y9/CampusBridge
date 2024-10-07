import { useRef } from "react";
import Cropper, { ReactCropperElement } from "react-cropper";
import { Button } from "@radix-ui/themes";
import "cropperjs/dist/cropper.css";

import RotateCCW from "@/assets/icons/icon_rotate-ccw.svg";
import RotateCW from "@/assets/icons/icon_rotate-cw.svg";

export default function CameraPage() {
  const cropperRef = useRef<ReactCropperElement>(null);
  // const onCrop = () => {
  //   const cropper = cropperRef.current?.cropper;
  // };
  return (
    <>
      <Cropper
        src="https://raw.githubusercontent.com/roadmanfong/react-cropper/master/example/img/child.jpg"
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
  );
}
