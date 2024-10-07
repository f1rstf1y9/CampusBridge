import BackHeader from "@/components/BackHeader";
import Footer from "@/components/Footer";

export default function ProfilePage() {
  return (
    <>
      <BackHeader />
      <div className="mx-12 pt-[100px] flex flex-col gap-10">
        <div className="flex gap-8">
          <div>닉네임</div>
          <div>nickname</div>
        </div>
        <hr />
        <ul className="flex flex-col gap-8">
          <li>로그아웃</li>
          <li>히스토리 초기화</li>
          <li>회원탈퇴</li>
        </ul>
        <hr />
        <div>서비스 이용약관</div>
      </div>
      <Footer />
    </>
  );
}
