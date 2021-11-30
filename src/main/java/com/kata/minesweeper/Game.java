package com.kata.minesweeper;

class Game {
    private final String eol = System.lineSeparator();
    private final String sandbox = "[Sandbox 3x3]";
    private GameState state;

    public void start() {
        state = GameState.IN_PROGRESS;

        System.out.println("+-+-+-+" + eol
                + "| | | |" + eol
                + "+-+-+-+" + eol
                + "| | | |" + eol
                + "+-+-+-+" + eol
                + "| | | |" + eol
                + "+-+-+-+" + eol
                + sandbox + " Game created");
    }

    public GameState getState() {
        return state;
    }
}
