package sample.graphics.game;

public class Player {
    private PlayerType playerType;

    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerType=" + playerType +
                '}';
    }

    public enum PlayerType{
        HUMAN_PLAYER, AI
    }
}
