import Footer from "@/components/Footer";

export default function HomePage() {
  return (
    <>
      <div className="text-center w-8/12 h-full flex flex-col justify-center mx-auto">
        <h1 className="text-4xl font-bold mb-24 text-sky-500">Campus Bridge</h1>
        <div className="flex flex-col gap-4 mb-12">
          <div className="w-full h-24 bg-sky-300 rounded-lg">
            <label
              htmlFor="camera"
              className="flex justify-center items-center w-full h-full"
            >
              카메라
            </label>
            <input
              id="camera"
              className="hidden"
              accept="image/*"
              type="file"
              capture="environment"
            />
          </div>

          <div className="w-full h-24 bg-sky-400 rounded-lg">
            <label
              htmlFor="gallery"
              className="flex justify-center items-center w-full h-full"
            >
              앨범
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
