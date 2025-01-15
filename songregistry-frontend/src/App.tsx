import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import ArtistPage from "./pages/ArtistPage";
import SongPage from "./pages/SongPage";
import AlbumPage from "./pages/AlbumPage";
import Login from "./pages/Login";
import Register from "./pages/Signup";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path={"/artist/:artistId"} element={<ArtistPage />} />
        <Route path={"/song/:songId"} element={<SongPage />} />
        <Route path={"/album/:albumId"} element={<AlbumPage />} />
        <Route path={"/login"} element={<Login />} />
        <Route path={"/register"} element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
