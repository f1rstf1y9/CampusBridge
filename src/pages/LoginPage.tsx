import BackHeader from "@/components/BackHeader";
import { Flex, TextField, Button } from "@radix-ui/themes";
import { Link } from "react-router-dom";

export default function LoginPage() {
  return (
    <>
      <BackHeader />
      <div className="flex w-lvw h-lvh items-center">
        <form className="w-full" action="#">
          <Flex direction="column" gap="2" className="w-full items-center">
            <h1 className="font-bold text-2xl mb-4 text-sky-400">로그인</h1>
            <TextField.Root
              placeholder="ID"
              className="w-4/5 h-[50px] rounded-xl shadow-clay-white-sm"
              required
            />
            <TextField.Root
              placeholder="PASSWORD"
              type="password"
              className="w-4/5 h-[50px] rounded-xl shadow-clay-white-sm"
              required
            />
            <Button
              variant="solid"
              className="w-4/5 h-[50px] mt-4 bg-sky-400 rounded-xl shadow-clay-blue-sm"
            >
              LOGIN
            </Button>
            <Link to="/signup" className="underline mt-2 text-slate-400">
              Sign Up
            </Link>
          </Flex>
        </form>
      </div>
    </>
  );
}
