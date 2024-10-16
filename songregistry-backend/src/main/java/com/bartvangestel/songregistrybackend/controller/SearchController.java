package com.bartvangestel.songregistrybackend.controller;

import com.bartvangestel.songregistrybackend.model.SearchResult;
import com.bartvangestel.songregistrybackend.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchServiceImpl searchServiceImpl;

    @GetMapping("/{search}")
    public List<SearchResult> search(@PathVariable String search) {
        return searchServiceImpl.search(search);
    }
}
