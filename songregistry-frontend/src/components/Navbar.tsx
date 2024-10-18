import SearchBar from "@/components/SearchBar.tsx";
import logo from "../assets/logo-no-background.png";

function Navbar() {


    return (
        <div className="bg-slate-300 flex m-0 p-3">
            <a className="left-0 mr-5">
                <img src={logo} alt="logo" className="w-20"></img>
            </a>
            <div className="flex items-center mr-5">
                <a href="/" className="p-2 text-xl hover:bg-slate-500 transition duration-500 rounded">Home</a>
                <a href="/" className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded">Artists</a>
                <a href="/" className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded">Albums</a>
                <a href="/" className="ml-3 p-2 text-xl hover:bg-slate-500 transition duration-500 rounded">Genres</a>
            </div>
            <SearchBar/>
        </div>
    );
}

export default Navbar;