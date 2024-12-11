import React from "react";
import axios from "axios";
import "./componentcss/SearchBarCss.css";
import { useNavigate } from "react-router-dom";

interface searchResult {
  type: string;
  id: number;
  name: string;
}

function SearchBar() {
  const [searchResult, setSearchResult] = React.useState<searchResult[]>([]);
  const [query, setQuery] = React.useState<string>("");
  const navigate = useNavigate();

  const handleSearch = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const query = event.target.value;
    setQuery(query);

    if (query.length === 0) {
      setSearchResult([]);
      return;
    }

    try {
      const response = await axios.get(`http://localhost:8080/search/${query}`);
      const data = response.data;

      setSearchResult(data);
    } catch {
      console.log("Error");
    }
  };

  const handleSearchResultClick = (id: number, type: string) => {
    if (type === "artist") {
      navigate(`/artist/${id}`);
    } else if (type === "album") {
      navigate(`/album/${id}`);
    } else if (type === "song") {
      navigate(`/song/${id}`);
    }
  };

  return (
    <div id="searchBar">
      <input
        className="searchbar"
        id="searchBarInput"
        value={query}
        onInput={handleSearch}
        placeholder="Search"
      />
      <div
        className={`searchresult ${searchResult.length > 0 ? "visible" : ""}`}
        id="searchResults"
      >
        {searchResult.map((result) => (
          <div
            key={result.name}
            onClick={() => handleSearchResultClick(result.id, result.type)}
            className="searchResult"
          >
            <p>{result.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default SearchBar;
