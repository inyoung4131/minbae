package com.minbae.store.comm;

import lombok.Getter;

public enum StoreCategory {
    CHICKEN("CHICKEN"),
    CHINESEFOOD("CHINESEFOOD"),
    DESSERT("DESSERT"),
    BUNSIK("BUNSIK"),
    PIZZA("PIZZA"),
    JAPANESEFOOD("JAPANESEFOOD");

    @Getter
    private String category;

    StoreCategory(String category) {
        this.category = category;
    }
}
