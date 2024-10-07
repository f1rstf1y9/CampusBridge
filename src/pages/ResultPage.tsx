import { SegmentedControl, Button } from "@radix-ui/themes";
import BackHeader from "@/components/BackHeader";
import Footer from "@/components/Footer";

export default function ResultPage() {
  return (
    <>
      <BackHeader />
      <div className="pt-[70px] text-center text-xl font-bold">문서 제목</div>
      <div className="w-full flex justify-center pt-4">
        <SegmentedControl.Root
          defaultValue="inbox"
          radius="large"
          size="3"
          className="h-[50px]"
        >
          <SegmentedControl.Item value="text_original">
            인식된 텍스트
          </SegmentedControl.Item>
          <SegmentedControl.Item value="text_translated">
            번역 결과
          </SegmentedControl.Item>
        </SegmentedControl.Root>
      </div>
      <div className="overflow-auto mt-8 pb-[60px] w-10/12 h-[calc(100vh-400px)] mx-auto flex flex-col gap-6">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget
        nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit.
        Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed
        adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et.
        Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit.
        Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh
        praesent. In hac habitasse platea dictumst quisque sagittis purus.
        Pulvinar elementum integer enim neque volutpat ac. Lorem ipsum dolor sit
        amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
        labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis
        hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio
        facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam
        donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci
        ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui
        vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac
        habitasse platea dictumst quisque sagittis purus. Pulvinar elementum
        integer enim neque volutpat ac. Lorem ipsum dolor sit amet, consectetur
        adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
        magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna
        eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet
        massa. Commodo odio aenean sed adipiscing diam donec adipiscing
        tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue.
        Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis.
        Egestas integer eget aliquet nibh praesent. In hac habitasse platea
        dictumst quisque sagittis purus. Pulvinar elementum integer enim neque
        volutpat ac. Lorem ipsum dolor sit amet, consectetur adipiscing elit,
        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl
        tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem
        ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo
        odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget
        mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet
        dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer
        eget aliquet nibh praesent. In hac habitasse platea dictumst quisque
        sagittis purus. Pulvinar elementum integer enim neque volutpat ac.
      </div>
      <div className="flex justify-center items-center h-32">
        <Button size="3" variant="soft" className="w-48">
          저장
        </Button>
      </div>

      <Footer />
    </>
  );
}
