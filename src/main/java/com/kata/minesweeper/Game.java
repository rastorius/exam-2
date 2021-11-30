package com.kata.minesweeper;

import java.util.List;

class Game {
    private final String eol = System.lineSeparator();
    private final String sandbox = "[Sandbox 3x3]";
    private GameState state;

    {
    }

    Game() {
    }

    Game(List<Integer> bombPositions) {
    }

    public void step(int position) {
        System.out.println("+-+-+-+" + eol
                + "| | | |" + eol
                + "+-+-+-+" + eol
                + "| | | |" + eol
                + "+-+-+-+" + eol
                + "| | |X|" + eol
                + "+-+-+-+" + eol);
    }

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
