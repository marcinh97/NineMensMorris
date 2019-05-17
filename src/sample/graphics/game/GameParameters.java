package sample.graphics.game;

public class GameParameters {

    private AlgorithmType algorithmType;
    private HeuristicsType heuristicsType;
    private GameType gameType;

    public GameParameters(AlgorithmType algorithmType, HeuristicsType heuristicsType, GameType gameType) {
        this.algorithmType = algorithmType;
        this.heuristicsType = heuristicsType;
        this.gameType = gameType;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public HeuristicsType getHeuristicsType() {
        return heuristicsType;
    }

    public void setHeuristicsType(HeuristicsType heuristicsType) {
        this.heuristicsType = heuristicsType;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public enum AlgorithmType{
        ALGORITHM_ONE, ALGORITHM_TWO
    }

    public enum HeuristicsType{
        H1, H2
    }

    public enum GameType{
        PLAYER_VS_AI, AI_VS_AI
    }

    @Override
    public String toString() {
        return "GameParameters{" +
                "algorithmType=" + algorithmType +
                ", heuristicsType=" + heuristicsType +
                ", gameType=" + gameType +
                '}';
    }
}
