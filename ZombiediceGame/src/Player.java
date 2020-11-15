
public class Player {
    int playerNumber;
    String playerName;
    int playerScore;   
    
    public Player(int playerNumber, String playerName, int playerScore) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.playerScore = playerScore;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getPlayerScore() {
        return playerScore;
    }
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

}
