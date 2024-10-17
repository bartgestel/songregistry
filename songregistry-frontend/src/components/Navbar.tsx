import SearchBar from "@/components/SearchBar.tsx";
import logo from "../assets/logo-no-background.png";

function Navbar() {


    return (
        <div className="flex m-0">
            <a className="left-0">
                <img src={logo} alt="logo" className="w-20"></img>
            </a>
            <SearchBar />
        </div>
    );
}

export default Navbar;