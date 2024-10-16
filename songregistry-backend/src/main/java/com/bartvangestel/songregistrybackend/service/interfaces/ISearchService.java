package com.bartvangestel.songregistrybackend.service.interfaces;

import com.bartvangestel.songregistrybackend.model.SearchResult;

import java.util.List;

public interface ISearchService {
    List<SearchResult> search(String search);
}
