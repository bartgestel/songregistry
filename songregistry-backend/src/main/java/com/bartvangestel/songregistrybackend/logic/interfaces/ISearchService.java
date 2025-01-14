package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.logic.DTO.SearchResultDTO;

import java.util.List;

public interface ISearchService {
    List<SearchResultDTO> search(String search);
}
