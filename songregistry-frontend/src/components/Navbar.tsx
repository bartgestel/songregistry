import SearchBar from "@/components/SearchBar.tsx";
import logo from "../assets/logo-no-background.png";
import { useEffect, useState } from "react";

function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    window.location.href = "/";
  };

  return (
    <div className="bg-slate-300 flex m-0 p-3 justify-between" id="navbar">
      <div className="flex">
        <a className="left-0 mr-5">
          <img src={logo} alt="logo" className="w-20"></img>
        </a>
        <div className="flex items-center mr-5">
          <a
            href="/"
            className="p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
          >
            Home
          </a>
          <a
            href="/"
            className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
          >
            Artists
          </a>
          <a
            href="/"
            className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
          >
            Albums
          </a>
          <a
            href="/"
            className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
          >
            Genres
          </a>
        </div>
        <SearchBar />
      </div>
      <div className="flex items-center mr-5 float-right">
        {isLoggedIn ? (
          <a
            onClick={handleLogout}
            className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
          >
            Logout
          </a>
        ) : (
          <div>
            <a
              href="/login"
              className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
            >
              Login
            </a>
            <a
              href="/register"
              className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded"
            >
              Signup
            </a>
          </div>
        )}
      </div>
    </div>
  );
}

export default Navbar;
