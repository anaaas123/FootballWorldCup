import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoreboard.ScoreBoard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTests {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void shouldStartNewGameWithInitialScore() {
        scoreBoard.startGame("Germany", "France");
        List<String> summary = scoreBoard.getSummary();

        assertEquals(1, summary.size());
        assertTrue(summary.getFirst().contains("Germany 0 - France 0"));
    }

    @Test
    void shouldUpdateScoreOfAnExistingGame() {
        scoreBoard.startGame("Argentina", "Brazil");
        scoreBoard.updateScore("Argentina", "Brazil", 3, 2);

        List<String> summary = scoreBoard.getSummary();
        assertTrue(summary.getFirst().contains("Argentina 3 - Brazil 2"));
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentGame() {
        Exception exception = assertThrows(Exception.class, () ->
                scoreBoard.updateScore("Nowhere", "Narnia", 1, 1));

        assertEquals("Match not found", exception.getMessage());
    }

    @Test
    void shouldRemoveMatchOnFinishGame() {
        scoreBoard.startGame("Italy", "Portugal");
        boolean removed = scoreBoard.finishGame("Italy", "Portugal");

        assertTrue(removed);
        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void shouldReturnGamesSortedByScoreAndRecency() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);

        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);

        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);

        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);

        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<String> summary = scoreBoard.getSummary();

        assertEquals("Uruguay 6 - Italy 6", summary.get(0));
        assertEquals("Spain 10 - Brazil 2", summary.get(1));
        assertEquals("Mexico 0 - Canada 5", summary.get(2));
        assertEquals("Argentina 3 - Australia 1", summary.get(3));
        assertEquals("Germany 2 - France 2", summary.get(4));
    }

    @Test
    void shouldNotAllowDuplicateMatches() {
        scoreBoard.startGame("Poland", "Croatia");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.startGame("Poland", "Croatia"));

        assertEquals("Match already exists: poland vs croatia", exception.getMessage());
    }
}
