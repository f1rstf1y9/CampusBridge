import Footer from "@/components/Footer";

import MainLogo from "@/assets/images/main-logo.png";
import IconCamera from "@/assets/icons/icon_camera.svg";
import IconAlbum from "@/assets/icons/icon_album.svg";

export default function HomePage() {
  return (
    <>
      <div className="text-center w-8/12 h-full flex flex-col justify-center mx-auto">
        <div className="text-4xl font-bold mb-16 text-sky-500">
          <img src={MainLogo} alt="Campus Bridge" />
          <span className="text-xl text-sky-400">캠퍼스 브릿지</span>
        </div>
        <div className="flex flex-col gap-4 mb-12">
          <div className="w-full h-24 bg-white rounded-xl shadow-clay-01">
            <label
              htmlFor="camera"
              className="flex justify-center items-center w-full h-full gap-4"
            >
              <img
                src={IconCamera}
                alt="camera"
                width="40px"
                className="ml-[-40px]"
              />
              <span className="text-sky-500 text-xl font-bold ml-[40px]">
                카메라
              </span>
            </label>
            <input
              id="camera"
              className="hidden"
              accept="image/*"
              type="file"
              capture="environment"
            />
          </div>

          <div className="w-full h-24 bg-sky-400 rounded-xl shadow-clay-02">
            <label
              htmlFor="gallery"
              className="flex justify-center items-center w-full h-full gap-4"
            >
              <img
                src={IconAlbum}
                alt="album"
                width="40px"
                className="ml-[-40px]"
              />
              <span className="text-white text-xl font-bold ml-[40px]">
                앨범
              </span>
            </label>
            <input
              id="gallery"
              className="hidden"
              accept="image/*"
              type="file"
            />
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
