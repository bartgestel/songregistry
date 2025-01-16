import React, { useState, useEffect } from "react";
import Navbar from "@/components/Navbar";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import axios from "axios";

interface User {
  email: string;
  username: string;
  password: string;
}

function Signup() {
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordValid, setPasswordValid] = useState(false);
  const [error, setError] = React.useState("");
  const [bannedPasswords, setBannedPasswords] = useState<string[]>([]);

  useEffect(() => {
    fetch("/bannedPasswords.txt")
      .then((response) => response.text())
      .then((data) => {
        const passwordsArray = data
          .split("\n")
          .map((password) => password.trim());
        setBannedPasswords(passwordsArray);
      })
      .catch((error) =>
        console.error("Error fetching banned passwords:", error),
      );
  }, []);

  function onPasswordChange(e: React.ChangeEvent<HTMLInputElement>) {
    const newPassword = e.target.value;
    setPassword(newPassword);
    const passwordError = document.getElementById("passwordError");

    const hasSpecialCharOrNumber = /[0-9!@#$%^&*(),.?":{}|<>]/.test(
      newPassword,
    );
    if (
      newPassword.length >= 14 &&
      hasSpecialCharOrNumber &&
      !bannedPasswords.includes(newPassword)
    ) {
      setPasswordValid(true);
      if (passwordError) {
        passwordError.innerText = "";
      }
    } else {
      setPasswordValid(false);
      if (passwordError) {
        if (newPassword.length < 14) {
          passwordError.innerText = "Password must be at least 14 characters";
        } else if (!hasSpecialCharOrNumber) {
          passwordError.innerText =
            "Password must contain a special character or number";
        } else if (bannedPasswords.includes(newPassword)) {
          passwordError.innerText = "Password is banned";
        }
      }
    }
  }

  async function userRegistration(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/users/register",
        {
          username,
          email,
          password,
        },
      );
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
        <h1 className="text-4xl text-center">Sign Up</h1>
        <div className="flex flex-col items-center mt-2">
          <form onSubmit={userRegistration}>
            <label>Email:</label>
            <Input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <p id="emailError" className="text-red-600"></p>
            <label>Username:</label>
            <Input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <p id="usernameError" className="text-red-600"></p>
            <label>Password:</label>
            <Input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => onPasswordChange(e)}
            />
            <p id="passwordError" className="text-red-600"></p>
            <label>Confirm Password:</label>
            <Input
              type="password"
              placeholder="Confirm Password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
            <p id="confirmPasswordError" className="text-red-600"></p>
            <Button
              className="bg-black text-white p-2 mt-2 rounded-md"
              type="submit"
            >
              Sign Up
            </Button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Signup;
