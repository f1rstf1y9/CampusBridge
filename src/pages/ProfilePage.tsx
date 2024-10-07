import { useTranslation } from "react-i18next";
import BackHeader from "@/components/BackHeader";
import Footer from "@/components/Footer";

export default function ProfilePage() {
  const { t } = useTranslation("page");
  return (
    <>
      <BackHeader />
      <div className="mx-12 pt-[100px] flex flex-col gap-10">
        <div className="flex gap-8">
          <div> {t("Nickname")}</div>
          <div>nickname</div>
        </div>
        <hr />
        <ul className="flex flex-col gap-8">
          <li> {t("LogOut")}</li>
          <li> {t("ResetHistory")}</li>
          <li> {t("DeleteAccount")}</li>
        </ul>
        <hr />
        <div> {t("TermsOfService")}</div>
      </div>
      <Footer />
    </>
  );
}
