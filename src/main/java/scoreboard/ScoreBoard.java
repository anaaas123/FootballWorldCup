package scoreboard;

import java.util.*;

public class ScoreBoard {
    private final LinkedHashMap<String, Match> matches = new LinkedHashMap<>();

    public void startGame(String homeTeam, String awayTeam) {
        String matchId = createMatchId(homeTeam, awayTeam);
        if (matches.containsKey(matchId)) {
            throw new IllegalArgumentException("Match already exists: " + matchId);
        }
        matches.put(matchId, new Match(homeTeam, awayTeam));
    }

    public boolean finishGame(String homeTeam, String awayTeam) {
        return matches.remove(createMatchId(homeTeam, awayTeam)) != null;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = matches.get(createMatchId(homeTeam, awayTeam));
        if (match == null) {
            throw new NoSuchElementException("Match not found");
        }
        match.updateScore(homeScore, awayScore);
    }

    private String createMatchId(String homeTeam, String awayTeam) {
        return homeTeam.toLowerCase() + " vs " + awayTeam.toLowerCase();
    }

    public List<String> getSummary() {
        List<Match> sortedMatches = matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing((m1, m2) -> {
                            List<Match> matchList = new ArrayList<>(matches.values());
                            return Integer.compare(matchList.indexOf(m2), matchList.indexOf(m1));
                        }))
                .toList();

        return sortedMatches.stream().map(Match::toString).toList();
    }
}