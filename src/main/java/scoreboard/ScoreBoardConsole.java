package scoreboard;

import java.util.List;
import java.util.Scanner;

public class ScoreBoardConsole {
    private final ScoreBoard scoreBoard;
    private final Scanner scanner = new Scanner(System.in);

    public ScoreBoardConsole(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void run() {
        while (true) {
            System.out.println("\nChoose your action:");
            System.out.println("1 - Start a new match");
            System.out.println("2 - Update a score");
            System.out.println("3 - End a match");
            System.out.println("4 - Show summary");
            System.out.println("5 - Exit");

            switch (scanner.nextLine()) {
                case "1" -> startMatch();
                case "2" -> updateScore();
                case "3" -> endMatch();
                case "4" -> showSummary();
                case "5" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void startMatch() {
        System.out.print("Home team: ");
        String home = scanner.nextLine();
        System.out.print("Away team: ");
        String away = scanner.nextLine();
        try {
            scoreBoard.startGame(home, away);
            System.out.println("Match started.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateScore() {
        System.out.print("Home team: ");
        String home = scanner.nextLine();
        System.out.print("Away team: ");
        String away = scanner.nextLine();

        int homeScore = promptScore("home");
        int awayScore = promptScore("away");

        try {
            scoreBoard.updateScore(home, away, homeScore, awayScore);
            System.out.println("Score updated.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void endMatch() {
        System.out.print("Home team: ");
        String home = scanner.nextLine();
        System.out.print("Away team: ");
        String away = scanner.nextLine();
        boolean ended = scoreBoard.finishGame(home, away);
        System.out.println(ended ? "Match ended." : "Match not found.");
    }

    private void showSummary() {
        List<String> summary = scoreBoard.getSummary();
        System.out.println("\nLive Summary:");
        summary.forEach(System.out::println);
    }

    private int promptScore(String teamLabel) {
        while (true) {
            System.out.printf("Enter %s team's score: ", teamLabel);
            try {
                int score = Integer.parseInt(scanner.nextLine());
                if (score < 0) throw new IllegalArgumentException();
                return score;
            } catch (Exception e) {
                System.out.println("Invalid number. Please enter a non-negative integer.");
            }
        }
    }
}
