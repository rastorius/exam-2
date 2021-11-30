package com.kata.minesweeper;

enum SquareState {
    HIDDEN(" "), MARKED("+"), STEPPED("");
    private final String state;

    SquareState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
