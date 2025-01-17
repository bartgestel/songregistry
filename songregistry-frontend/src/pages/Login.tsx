import Navbar from "@/components/Navbar";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import React from "react";
import axios from "axios";

function Login() {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [error, setError] = React.useState("");

  async function handleLogin(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/users/login", {
        email,
        password,
      });
      localStorage.setItem("token", response.data);
      window.location.href = "/";
    } catch (error) {
      setError(error);
    }
  }

  return (
    <div className="w-full">
      <Navbar />
      <div className="flex flex-col content-center mt-3">
        <h1 className="text-4xl text-center">Login</h1>
        <div className="flex flex-col items-center mt-2">
          <form onSubmit={handleLogin}>
            <label>Email:</label>
            <Input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <p id="emailError" className="text-red-600"></p>
            <label>Password:</label>
            <Input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
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
