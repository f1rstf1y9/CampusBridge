export default function ErrorModal() {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-xl text-center shadow-clay-white-sm">
        <p className="text-lg font-semibold">
          사진이 정상적으로 업로드되지 않았습니다.
        </p>
        <p>잠시 후 메인화면으로 돌아갑니다.</p>
      </div>
    </div>
  );
}
