import Navbar from "../components/Navbar";
import HomeArtists from "../components/HomeArtists";
import HomeAlbums from "../components/HomeAlbums";

function Home() {
  return (
    <div className="w-full">
      <Navbar />
      <HomeArtists />
      <HomeAlbums />
    </div>
  );
}

export default Home;
