import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import AddArtist from "./pages/AddArtist.tsx";
import AddAlbum from "./pages/AddAlbum.tsx";
import AddSong from "./pages/AddSong.tsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/artist" element={<AddArtist />} />
        <Route path="/album" element={<AddAlbum />} />
        <Route path="/song" element={<AddSong />} />
      </Routes>
    </Router>
  );
}

export default App;
