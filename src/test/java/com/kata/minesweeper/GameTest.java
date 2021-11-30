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

    @Test
    @DisplayName("GIVEN bombs on (1,0), (0,1) and (1,1) WHEN stepping on (0,0) THEN should print updated board and mines around you message")
    void neighbourBombs_acceptanceTest() {
        // given
        Game game = new Game(List.of(1, 3, 4));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.step(0);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "|3| | |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " 3 bombs around your square");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN 1 bomb WHEN stepping next to bomb THEN should print mines around you message and game state should be IN_PROGRESS")
    void whenStepNextToBomb_thenShouldPrintMessageAndUpdateState() {
        // given
        Game game = new Game(List.of(3));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.step(0);

        // then
        assertThat(outContent.toString())
                .contains(sandbox + " 1 bombs around your square");
        System.setOut(originalOut);
        assertThat(game.getState()).isEqualTo(GameState.IN_PROGRESS);
    }

    @Test
    @DisplayName("GIVEN b(1,0), (0,1) and (1,1) are bombs, (0,0) is 3 "
            + "WHEN stepping on (0,1), (1,0), (0,2), (2,0) and (2,2) "
            + "THEN should print updated board and victory message")
    void victory_acceptanceTest() {
        // given
        Game game = new Game(List.of(1, 3, 4));
        game.start();
        game.step(0);

        // when
        game.step(2);
        game.step(5);
        game.step(6);
        game.step(7);
        System.setOut(new PrintStream(outContent));
        game.step(8);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "|2|2|1|" + eol
                        + "+-+-+-+" + eol
                        + "| | |2|" + eol
                        + "+-+-+-+" + eol
                        + "|3| |2|" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " the land is cleared! GOOD JOB!");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN a board with 1 cleanable square left WHEN stepping on the cleanable square "
            + "THEN should print victory message and game state should be VICTOR")
    void whenVictory_thenShouldPrintMessageAndUpdateState() {
        // given
        Game game = new Game(List.of(4));
        game.start();
        game.step(0);
        game.step(1);
        game.step(2);
        game.step(3);
        game.step(5);
        game.step(6);
        game.step(7);

        // when
        System.setOut(new PrintStream(outContent));
        game.step(8);

        // then
        assertThat(outContent.toString())
                .contains(sandbox + " the land is cleared! GOOD JOB!");
        System.setOut(originalOut);
        assertThat(game.getState()).isEqualTo(GameState.VICTORY);
    }

    @Test
    @DisplayName("GIVEN (2,2) is a bomb WHEN stepping on (0,0) THEN should print updated board and victory message")
    void massiveCleaningVictory_acceptanceTest() {
        // given
        Game game = new Game(List.of(8));
        game.start();


        // when
        System.setOut(new PrintStream(outContent));
        game.step(0);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "|_|1| |" + eol
                        + "+-+-+-+" + eol
                        + "|_|1|1|" + eol
                        + "+-+-+-+" + eol
                        + "|_|_|_|" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " the land is cleared! GOOD JOB!");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN a board with massive cleaning possible with victory WHEN stepping on one of winning squares "
            + "THEN should clear every square around it recursively and game state should be VICTORY")
    void whenMassiveCleaningVictory_thenShouldPrintMessageAndUpdateState() {
        // given
        Game game = new Game(List.of(8));
        game.start();


        // when
        System.setOut(new PrintStream(outContent));
        game.step(0);

        // then
        assertThat(outContent.toString())
                .contains(sandbox + " the land is cleared! GOOD JOB!");
        System.setOut(originalOut);
        assertThat(game.getState()).isEqualTo(GameState.VICTORY);
    }

    @Test
    @DisplayName("GIVEN (0,0) is revealed (3) and there are bombs on (1,0), (0,1) and (1,1)"
            + " WHEN marking (1,0), (0,1) and (1,1)"
            + " THEN should print updated board and square flagged as mine message")
    void mark_acceptanceTest() {
        // given
        Game game = new Game(List.of(1, 3, 4));
        game.start();
        game.step(0);
        game.mark(1);
        game.mark(3);

        // when
        System.setOut(new PrintStream(outContent));
        game.mark(4);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "|+|+| |" + eol
                        + "+-+-+-+" + eol
                        + "|3|+| |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " Square flagged as bomb.");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN a board no steps taken"
            + " WHEN marking a square"
            + " THEN should print board with the square revealed (+) and square flagged as mine message")
    void whenMarking_thenShouldUpdateBoardAndPrintMessage() {
        // given
        Game game = new Game(List.of(1));
        game.start();

        // when
        System.setOut(new PrintStream(outContent));
        game.mark(1);

        // then
        assertThat(outContent.toString())
                .contains("+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| | | |" + eol
                        + "+-+-+-+" + eol
                        + "| |+| |" + eol
                        + "+-+-+-+" + eol);
        assertThat(outContent.toString())
                .contains(sandbox + " Square flagged as bomb.");
        System.setOut(originalOut);

        System.out.println(outContent);
    }

    @Test
    @DisplayName("GIVEN new board"
            + " WHEN isPositionFree for any position"
            + " THEN should return true")
    void hiddenSquareShouldBeFree() {
        // given
        Game game = new Game(List.of(1));
        game.start();

        // when
        boolean result = game.isPositionFree(1);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("GIVEN stepped positon"
            + " WHEN isPositionFree for that position"
            + " THEN should return false")
    void steppedSquareShouldNotBeFree() {
        // given
        Game game = new Game(List.of(1));
        game.start();
        game.step(2);

        // when
        boolean result = game.isPositionFree(2);

        // then
        assertThat(result).isFalse();
    }
}
