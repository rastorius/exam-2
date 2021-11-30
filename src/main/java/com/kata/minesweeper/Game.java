package com.kata.minesweeper;

import java.util.Arrays;
import java.util.List;

class Game {
    private final String eol = System.lineSeparator();
    private final String sandbox = "[Sandbox 3x3]";
    private final SquareState[] visibleBoard = new SquareState[9];
    private final SquareValue[] actualBoard = new SquareValue[9];
    private GameState state;

    {
    }

    Game() {
    }

    Game(List<Integer> bombPositions) {
        for (int i = 0; i < 9; ++i) {
            if (bombPositions.contains(i)) {
                actualBoard[i] = SquareValue.MINE;
            } else {
                actualBoard[i] = SquareValue.EMPTY;
            }
        }
    }

    private String getSquarePrintValue(int position) {
        if (visibleBoard[position] == SquareState.STEPPED) {
            return actualBoard[position].getValue();
        } else {
            return visibleBoard[position].getState();
        }
    }

    void printBoard() {
        System.out.println("+-+-+-+" + eol
                + "|" + getSquarePrintValue(6) + "|" + getSquarePrintValue(7) + "|" + getSquarePrintValue(8) + "|" + eol
                + "+-+-+-+" + eol
                + "|" + getSquarePrintValue(3) + "|" + getSquarePrintValue(4) + "|" + getSquarePrintValue(5) + "|" + eol
                + "+-+-+-+" + eol
                + "|" + getSquarePrintValue(0) + "|" + getSquarePrintValue(1) + "|" + getSquarePrintValue(2) + "|" + eol
                + "+-+-+-+" + eol);
    }

    public void step(int position) {
        visibleBoard[position] = SquareState.STEPPED;
        printBoard();
    }

    public void start() {
        Arrays.fill(visibleBoard, SquareState.HIDDEN);
        state = GameState.IN_PROGRESS;

        printBoard();
        System.out.println(sandbox + " Game created");
    }

    public GameState getState() {
        return state;
    }
}
