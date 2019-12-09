import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {
    private final String PLAYER1_NAME;
    private final String PLAYER2_NAME;
    private Map<String, Integer> playerScoreMap = new HashMap<>();
    private Map<Integer, String> scoreTermMap = new HashMap<Integer, String>() {{
        put(0, "Love");
        put(1, "Fifteen");
        put(2, "Thirty");
        put(3, "Forty");
    }};

    public TennisGame1(String player1Name, String player2Name) {
        PLAYER1_NAME = player1Name;
        PLAYER2_NAME = player2Name;
        playerScoreMap.put(PLAYER1_NAME, 0);
        playerScoreMap.put(PLAYER2_NAME, 0);
    }

    public void wonPoint(String playerName) {
        if (!playerScoreMap.containsKey(playerName)) throw new IllegalArgumentException("Invalid player name");
        playerScoreMap.put(playerName, playerScoreMap.get(playerName) + 1);
    }

    public String getScore() {
        int player1Score = playerScoreMap.get(PLAYER1_NAME);
        int player2Score = playerScoreMap.get(PLAYER2_NAME);

        if (player1Score == player2Score) {
            return handleEqualScore(player1Score);
        } else if (eitherBeyondFour(player1Score, player2Score)) {
            return handleBeyondFour(player1Score - player2Score);
        }
        return scoreTermMap.get(player1Score) + "-" + scoreTermMap.get(player2Score);
    }

    private String handleBeyondFour(int scoreDiff) {
        if (Math.abs(scoreDiff) >= 2) return handleWin(scoreDiff);
        return handleAdv(scoreDiff);
    }

    private String handleWin(int scoreDiff) {
        if (scoreDiff >= 2) return "Win for player1";
        return "Win for player2";
    }

    private String handleAdv(int scoreDiff) {
        if (scoreDiff == 1) return "Advantage player1";
        return "Advantage player2";
    }

    private String handleEqualScore(int score) {
        if (score >= 3) return "Deuce";
        return scoreTermMap.get(score) + "-All";
    }

    private boolean eitherBeyondFour(int player1Score, int player2Score) {
        return player1Score >= 4 || player2Score >= 4;
    }
}
