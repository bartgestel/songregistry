package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.model.SearchResult;

import java.util.List;

public interface ISearchService {
    List<SearchResult> search(String search);
}
