package com.kata.minesweeper;

class Game {
    private GameState state;

    public void start() {
        state = GameState.IN_PROGRESS;
    }

    public GameState getState() {
        return state;
    }
}
