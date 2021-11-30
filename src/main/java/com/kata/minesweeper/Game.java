package com.kata.minesweeper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Game {
    public static final String SEPARATOR = "+-+-+-+";
    private static final String SANDBOX = "[Sandbox 3x3]";
    private final String eol = System.lineSeparator();
    private final SquareState[] visibleBoard = new SquareState[9];
    private final SquareValue[] actualBoard = new SquareValue[9];
    private final Map<Integer, List<Integer>> neighbourPositions =
            Map.of(
                    0, List.of(1, 3, 4),
                    1, List.of(0, 2, 3, 4, 5),
                    2, List.of(1, 4, 5),
                    3, List.of(0, 1, 4, 6, 7),
                    4, List.of(0, 1, 2, 3, 5, 6, 7, 8),
                    5, List.of(1, 2, 4, 7, 8),
                    6, List.of(3, 4, 7),
                    7, List.of(3, 4, 5, 6, 8),
                    8, List.of(4, 5, 7)
            );
    private final int numberOfBombs;
    private GameState state;

    Game() {
        numberOfBombs = 0;
    }

    Game(List<Integer> bombPositions) {
        numberOfBombs = bombPositions.size();

        for (var i = 0; i < 9; ++i) {
            if (bombPositions.contains(i)) {
                actualBoard[i] = SquareValue.MINE;
            } else {
                actualBoard[i] = SquareValue.EMPTY;
            }
        }

        updateSquaresWithBombsAround();
    }

    private void updateSquaresWithBombsAround() {
        for (var i = 0; i < 9; ++i) {
            if (actualBoard[i] != SquareValue.MINE) {
                int numberOfBombsAround = getNumberOfBombsAround(i);
                setSquareWithBombAroundValue(i, numberOfBombsAround);
            }
        }
    }

    private void setSquareWithBombAroundValue(int i, int numberOfBombsAround) {
        if (numberOfBombsAround == 1) {
            actualBoard[i] = SquareValue.ONE;
        }
        if (numberOfBombsAround == 2) {
            actualBoard[i] = SquareValue.TWO;
        }
        if (numberOfBombsAround == 3) {
            actualBoard[i] = SquareValue.THREE;
        }
    }

    private int getNumberOfBombsAround(int position) {
        return (int) neighbourPositions.get(position)
                .stream()
                .filter(i -> actualBoard[i] == SquareValue.MINE)
                .count();
    }

    private String getSquarePrintValue(int position) {
        if (visibleBoard[position] == SquareState.STEPPED) {
            return actualBoard[position].getValue();
        } else {
            return visibleBoard[position].getState();
        }
    }

    void printBoard() {
        System.out.println(SEPARATOR + eol
                + "|" + getSquarePrintValue(6) + "|" + getSquarePrintValue(7) + "|" + getSquarePrintValue(8) + "|" + eol
                + SEPARATOR + eol
                + "|" + getSquarePrintValue(3) + "|" + getSquarePrintValue(4) + "|" + getSquarePrintValue(5) + "|" + eol
                + SEPARATOR + eol
                + "|" + getSquarePrintValue(0) + "|" + getSquarePrintValue(1) + "|" + getSquarePrintValue(2) + "|" + eol
                + SEPARATOR + eol);
    }

    public void mark(int position) {
        visibleBoard[position] = SquareState.MARKED;
        printBoard();
        System.out.println(SANDBOX + " Square flagged as bomb.");
    }

    public void step(int position) {
        visibleBoard[position] = SquareState.STEPPED;

        if (actualBoard[position] == SquareValue.MINE) {
            printBoard();
            handleGameOver();
        } else if (actualBoard[position] != SquareValue.EMPTY) {
            printBoard();
            checkAndHandleVictory(position);
        } else {
            massiveClear(position);
            printBoard();
            if (isVictory()) {
                handleVictory();
            }
        }
    }

    private boolean isVictory() {
        return (9 - numberOfBombs) == getNumberOfCleanedSquares();
    }

    private void massiveClear(int position) {
        visibleBoard[position] = SquareState.STEPPED;
        for (int i : neighbourPositions.get(position)) {
            if (actualBoard[i] == SquareValue.EMPTY && visibleBoard[i] != SquareState.STEPPED) {
                massiveClear(i);
            } else {
                visibleBoard[i] = SquareState.STEPPED;
            }
        }
    }

    private void handleGameOver() {
        System.out.println(SANDBOX + " BOOM! - Game Over");
        state = GameState.GAME_OVER;
    }

    private void checkAndHandleVictory(int position) {
        if (isVictory()) {
            handleVictory();
        } else {
            System.out.println(SANDBOX + " " + actualBoard[position].getValue() + " bombs around your square");
        }

    }

    private void handleVictory() {
        state = GameState.VICTORY;
        System.out.println(SANDBOX + " the land is cleared! GOOD JOB!");
    }

    private int getNumberOfCleanedSquares() {
        int counter = 0;
        for (int i = 0; i < 9; ++i) {
            if (visibleBoard[i] == SquareState.STEPPED) {
                ++counter;
            }
        }
        return counter;
    }

    public void start() {
        Arrays.fill(visibleBoard, SquareState.HIDDEN);
        state = GameState.IN_PROGRESS;

        printBoard();
        System.out.println(SANDBOX + " Game created");
    }

    public GameState getState() {
        return state;
    }
}
