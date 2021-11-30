package com.kata.minesweeper;

import java.util.List;
import java.util.Random;

class MineSweeperApplication {
    public static void main(String[] args) {
        Game game = new Game(List.of(5, 6));
        game.start();
        while (game.getState() == GameState.IN_PROGRESS) {
            int pos = 1;
            while (!game.isPositionFree(pos)) {
                pos = new Random().nextInt(9);
            }
            game.step(pos);
        }
    }
}
