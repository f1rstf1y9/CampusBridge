import { useTranslation } from "react-i18next";

export default function ErrorModal() {
  const { t } = useTranslation("page");

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-xl text-center shadow-clay-white-sm">
        <p className="text-lg font-semibold">{t("ImgUploadErrMsg")}</p>
        <p>{t("BackToMainMsg")}</p>
      </div>
    </div>
  );
}
