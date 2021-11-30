package com.kata.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game class related tests")
class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String eol = System.lineSeparator();
    private final String sandbox = "[Sandbox 3x3]";

    @Test
    @DisplayName("GIVEN new game WHEN starting game THEN should print new board and starting message")
    void gameBoardCreation_acceptanceTest() {
        // given
        Game game = new Game();

        // when
        System.setOut(new PrintStream(outContent));
        game.start();

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " Game created");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN new game WHEN starting game THEN game state should be IN_PROGRESS")
    void whenStartGame_thenStateShouldBeInProgress() {
        // given
        Game game = new Game();

        // when
        game.start();

        // then
        assertThat(game.getState()).isEqualTo(GameState.IN_PROGRESS);
    }

    @Test
    @DisplayName("GIVEN new game WHEN starting game THEN should print new board and starting message")
    void whenStartGame_thenShouldPrintBoardAndMessage() {
        // given
        Game game = new Game();

        // when
        System.setOut(new PrintStream(outContent));
        game.start();

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " Game created");
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("GIVEN board with bomb WHEN stepping on bomb THEN should print updated board and game over message")
    void gameOver_acceptanceTest() {
        // given
        Game game = new Game(List.of(4));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.step(4);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| |X| |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " BOOM! - Game Over");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN started game WHEN stepping THEN should print updated board")
    void whenStep_thenShouldPrintUpdatedBoard() {
        // given
        Game game = new Game(List.of(2));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.step(2);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | |X|" + eol
                        + "+-+-+-+");
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("GIVEN board with 1 bomb WHEN stepping on the bomb THEN should print game over message and game state should be GAME_OVER")
    void whenStepOnBomb_thenShouldPrintMessageAndUpdateState() {
        // given
        Game game = new Game(List.of(3));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.step(3);

        // then
        assertThat(outContent.toString())
                .contains(sandbox + " BOOM! - Game Over");
        System.setOut(originalOut);
        assertThat(game.getState()).isEqualTo(GameState.GAME_OVER);
    }
}
