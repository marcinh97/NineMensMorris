package sample.graphics.game;


import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainGame {

    public static final int NUMBER_OF_GAME_PIECES = 9;
    private GameParameters gameParameters;
    private GameStage currentStage;
    private Player currentPlayer;
    private Player[] players;
    private BoardPlace[] boardPlaces;
    private List<BoardPlace> currentlyPlacedGamePieces;

    public MainGame(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        currentStage = GameStage.FIRST_STAGE;
        currentlyPlacedGamePieces = new ArrayList<>();
        initializePlayers();
        initializeBoardPlaces();

        System.out.println(gameParameters + "\n" + currentStage);
        System.out.println("Nowa gra:");
        System.out.println("Current player: " + currentPlayer);
        System.out.println("All players: " + players[0] + ", " + players[1]);
    }

    private void initializeBoardPlaces() {
        boardPlaces = new BoardPlace[]{
                new BoardPlace('a', '7')
                ,new BoardPlace('a', '4')
                ,new BoardPlace('a', '1')
                ,new BoardPlace('d', '7')
                ,new BoardPlace('g', '7')
                ,new BoardPlace('g', '4')
                ,new BoardPlace('g', '1')
                ,new BoardPlace('d', '1')
                ,new BoardPlace('d', '6')
                ,new BoardPlace('b', '6')
                ,new BoardPlace('b', '4')
                ,new BoardPlace('b', '2')
                ,new BoardPlace('d', '2')
                ,new BoardPlace('f', '2')
                ,new BoardPlace('f', '4')
                ,new BoardPlace('f', '6')
                ,new BoardPlace('c', '5')
                ,new BoardPlace('d', '5')
                ,new BoardPlace('e', '5')
                ,new BoardPlace('c', '4')
                ,new BoardPlace('c', '3')
                ,new BoardPlace('d', '3')
                ,new BoardPlace('e', '3')
                ,new BoardPlace('e', '4')
        };
    }

    public boolean isBoardPlaceAvailable(char column, char row){
        var foundOptional =  Stream.of(boardPlaces)
                .filter(place -> place.getColumn()==column && place.getRow()==row)
                .findFirst();
        return foundOptional.isPresent() && !foundOptional.get().isOccupied();
    }

    public boolean tryPlacingGamePiece(BoardPlace clickedBoardPlace){
        boolean isPlayerAllowedToPlaceTheirGamePiece =
                isBoardPlaceAvailable(clickedBoardPlace.getColumn(), clickedBoardPlace.getRow());
        if (isPlayerAllowedToPlaceTheirGamePiece){
            currentlyPlacedGamePieces.add(clickedBoardPlace);
            return true;
        }
        return false;
    }

    public boolean isHumanPlayersTurn() {
        return currentPlayer.getPlayerType().equals(Player.PlayerType.HUMAN_PLAYER);
    }

    private void initializePlayers(){
        if (gameParameters.getGameType().equals(GameParameters.GameType.PLAYER_VS_AI)){
            players = new Player[]{new Player(Player.PlayerType.HUMAN_PLAYER), new Player(Player.PlayerType.AI)};
        }
        else{
            players = new Player[]{new Player(Player.PlayerType.AI), new Player(Player.PlayerType.AI)};
        }
        currentPlayer = players[0];
    }

    public GameStage getCurrentStage() {
        return currentStage;
    }

    public enum GameStage{
        FIRST_STAGE, SECOND_STAGE, THIRD_STAGE
    }

}
