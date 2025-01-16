package com.bartvangestel.songregistrybackend.logic.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchResultDTO {
    private String type;
    private int id;
    private String name;

    public SearchResultDTO(String type, int id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

}
