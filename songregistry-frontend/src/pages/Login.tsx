import Navbar from "@/components/Navbar";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

function Login() {
  return (
    <div className="w-full">
      <Navbar />
      <div className="flex flex-col content-center mt-3">
        <h1 className="text-4xl text-center">Login</h1>
        <div className="flex flex-col items-center mt-2">
          <form>
            <label>Email:</label>
            <Input type="email" placeholder="Email" />
            <p id="emailError" className="text-red-600"></p>
            <label>Password:</label>
            <Input type="password" placeholder="Password" />
            <p id="passwordError" className="text-red-600"></p>
            <Button className="bg-black text-white p-2 mt-2 rounded-md">
              Login
            </Button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
