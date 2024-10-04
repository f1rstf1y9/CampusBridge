import BackHeader from "@/components/BackHeader";
import DocumentItem from "@/components/DocumentItem";
import Footer from "@/components/Footer";

export default function HistoryPage() {
  return (
    <>
      <BackHeader />
      <div className="bg-slate-100 overflow-auto">
        <div className="pt-[70px] pb-[60px] w-10/12 h-[calc(100%-60px)] mx-auto flex flex-col gap-6">
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
          <DocumentItem />
        </div>
      </div>
      <Footer />
    </>
  );
}
