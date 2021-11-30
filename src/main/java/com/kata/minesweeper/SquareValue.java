package com.kata.minesweeper;

enum SquareValue {
    EMPTY("_"), MINE("X"), ONE("1"), TWO("2"), THREE("3");
    private final String value;

    SquareValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
