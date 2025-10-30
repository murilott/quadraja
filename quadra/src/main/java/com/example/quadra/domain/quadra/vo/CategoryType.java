package com.example.quadra.domain.quadra.vo;

import lombok.Getter;

@Getter
public enum CategoryType {
    FUTEBOL(1),
    VOLEI(2),
    TENIS(3);

    private final int level;

    CategoryType(int level) {
        this.level = level;
    }

    public boolean covers(CategoryType other) {
        return this.level >= other.level;
    }
}
