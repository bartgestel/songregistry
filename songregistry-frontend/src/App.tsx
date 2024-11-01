import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import ArtistPage from "./pages/ArtistPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path={"/artist/:artistId"} element={<ArtistPage />} />
      </Routes>
    </Router>
  );
}

export default App;
