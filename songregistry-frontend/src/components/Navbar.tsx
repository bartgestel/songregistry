import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command';
import React from 'react';
import axios from 'axios';

interface Result {
    id: number;
    artist_name: string;
}

function Navbar() {
    const [results, setResults] = React.useState<Result[]>([]);

    const handleSearch = (e: React.FormEvent<HTMLInputElement>) => {
        const searchValue = e.currentTarget.value;
        console.log(`Search value: ${searchValue}`);
        if (searchValue !== '') {
            axios.get(`http://localhost:8080/artists/name/${searchValue}`)
                .then(response => {
                    console.log('Full API response:', response);
                    console.log('Response data:', response.data);
                    if (response.data && Array.isArray(response.data)) {
                        setResults(response.data);
                    } else {
                        setResults([]);
                    }
                    console.log('Items:', response.data);
                })
                .catch(error => {
                    console.error('API error:', error);
                    setResults([]);
                });
        }

    }

    return (
        <div>
            <Command>
                <CommandInput id="searchBar" placeholder="Search..." onInput={handleSearch} />
                <CommandList>
                    <CommandGroup>
                        {results.length > 0 ? (
                            results.map((result: Result) => (
                                <CommandItem key={result.id}>
                                    {result.artist_name}
                                </CommandItem>
                            ))
                        ) : (
                            <CommandEmpty>No results found</CommandEmpty>
                        )}
                    </CommandGroup>
                </CommandList>
            </Command>
        </div>
    );
}

export default Navbar;