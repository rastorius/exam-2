package com.kata.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game class related tests")
class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String eol = System.lineSeparator();
    private final String sandbox = "[Sandbox 3x3]";

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

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
                        + "+-+-+-+");
        assertThat(outContent.toString())
                .contains(sandbox + " Game created");
        System.setOut(originalOut);
        
        System.out.println(outContent);
    }

}
