import React from "react";
import axios from "axios";
import "./componentcss/SearchBarCss.css";

interface searchResult {
    type: string;
    id: number;
    name: string
}

function SearchBar() {
    const[searchResult, setSearchResult] = React.useState<searchResult[]>([]);
    const [query, setQuery] = React.useState<string>("");

    const handleSearch = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const query = event.target.value;
        setQuery(query);

        if(query.length === 0) {
            setSearchResult([]);
            return;
        }

        try{
            const response = await axios.get(`http://localhost:8080/search/${query}`);
            const data = response.data;

            setSearchResult(data);
        }catch{
            console.log("Error");
        }
    }

    return (
        <div>
            <input className="searchbar" value={query} onInput={handleSearch} placeholder="Search"/>
            <div className={`searchresult ${searchResult.length > 0 ? 'visible' : ''}`}>
                {searchResult.map((result) => (
                    <div className="searchitem" key={result.name}>
                        <p>{result.name}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default SearchBar;