import { Link } from "react-router-dom";

import DocuImg from "@/assets/images/docu_dummy.jpg";

export default function DocumentItem() {
  return (
    <>
      <Link to="/document/1">
        <div className="flex items-center gap-4">
          <img
            src={DocuImg}
            alt="문서 제목"
            className="w-[80px] h-[80px] object-cover rounded"
          />
          <div>
            <div className="font-bold mb-[10px]">문서 제목</div>
            <div className="text-slate-400">2024.10.04</div>
          </div>
        </div>
      </Link>
    </>
  );
}
