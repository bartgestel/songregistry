package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.SearchResultDTO;

import java.util.List;

public interface ISearchService {
    List<SearchResultDTO> search(String search);
}
