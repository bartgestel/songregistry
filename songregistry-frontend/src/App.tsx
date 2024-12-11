import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import ArtistPage from "./pages/ArtistPage";
import SongPage from "./pages/SongPage";
import AlbumPage from "./pages/AlbumPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path={"/artist/:artistId"} element={<ArtistPage />} />
        <Route path={"/song/:songId"} element={<SongPage />} />
        <Route path={"/album/:albumId"} element={<AlbumPage />} />
      </Routes>
    </Router>
  );
}

export default App;
