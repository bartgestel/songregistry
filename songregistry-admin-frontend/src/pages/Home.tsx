import { Button } from "@/components/ui/button";
import * as React from "react";

function Home() {
  return (
    <div className="flex flex-col items-center">
      <h1 className="text-2xl font-bold mb-4">Song Registry Admin Panel</h1>
      <a href="/artist" className="mb-1">
        <Button className="w-52">Add Artist</Button>
      </a>
      <a href="/album" className="mb-1">
        <Button className="w-52">Add Album</Button>
      </a>
      <a href="/song" className="mb-1">
        <Button className="w-52">Add Song</Button>
      </a>
    </div>
  );
}

export default Home;
