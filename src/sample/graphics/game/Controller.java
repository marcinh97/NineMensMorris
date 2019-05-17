package sample.graphics.game;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Map<String, Color> mapCirclesClickedWithColor;
    private MainGame currentGame;
    private Circle currentlyClickedCircle;

    private static final String ERROR_MESSAGE = "Przed rozpoczęciem gry należy wybrać rodzaj algorytmu, " +
            "typ gry oraz heurystykę!";

    @FXML
    private Button newGameButton;

    @FXML
    private ComboBox<String> algorithmTypeCombo;

    @FXML
    private ComboBox<String> gameTypeCombo;

    @FXML
    private ComboBox<String> heuristicsChoiceCombo;

    public Controller() {
        this.mapCirclesClickedWithColor = new HashMap<>();
    }

    public void clicked(MouseEvent mouseEvent) {
        System.out.println("Clicked: " + mouseEvent.getSource());
    }

    public void newGameClicked(MouseEvent mouseEvent) {
        System.out.println("Clicked new game");
    }

    public void clickedCircle(MouseEvent mouseEvent) {
        if (currentGame == null || !currentGame.isHumanPlayersTurn()) return;
        var clickedBoardPlace = getBoardPlaceBasedOnClickedCircle((Circle)mouseEvent.getSource());
        if (!currentGame.tryPlacingGamePiece(clickedBoardPlace)) return;
        if (currentGame.getCurrentStage().equals(MainGame.GameStage.FIRST_STAGE)){
            performGameActionsForFirstStage(mouseEvent);
        }
        System.out.println("Clicked: " + ((Circle)mouseEvent.getSource()).getId());
    }

    private BoardPlace getBoardPlaceBasedOnClickedCircle(Circle circle){
        String clickedCircleId = circle.getId();
        char columnOfClickedCircle = clickedCircleId.charAt(clickedCircleId.length()-2);
        char rowOfClickedCircle = clickedCircleId.charAt(clickedCircleId.length()-1);
        return new BoardPlace(columnOfClickedCircle, rowOfClickedCircle);
    }

    private void performGameActionsForFirstStage(MouseEvent mouseEvent){
        Circle circle = (Circle)(mouseEvent.getSource());
        String id = circle.getId();
        Color chosenColor = Color.WHITE;
        performClickBasedOnPreviouslyClickedCircles(circle, id, chosenColor);

    }

    private void performClickBasedOnPreviouslyClickedCircles(Circle circle, String id, Color chosenColor) {
        if (currentlyClickedCircle == null){
            changeCircleAfterClick(chosenColor, circle, 2);
            mapCirclesClickedWithColor.put(id, chosenColor);
            currentlyClickedCircle = circle;
        }
        else{
            if (currentlyClickedCircle.getId().equals(id)){
                changeCircleAfterClick(Color.DODGERBLUE, circle, 1);
                mapCirclesClickedWithColor.remove(id);
                currentlyClickedCircle = null;
            }
            else{
                changeCircleAfterClick(chosenColor, circle, 2);
                changeCircleAfterClick(Color.DODGERBLUE, currentlyClickedCircle, 1);
                currentlyClickedCircle = circle;
            }
        }
    }

    private void changeCircleAfterClick(Color chosenColor, Circle circle, int scale){
        circle.setFill(chosenColor);
        circle.setScaleX(scale);
        circle.setScaleY(scale);
    }


    public void startGame(MouseEvent mouseEvent) {
        String algorithmType = algorithmTypeCombo.getValue();
        String gameType = gameTypeCombo.getValue();
        String heuristicsChosen = heuristicsChoiceCombo.getValue();

        boolean isAnyFieldEmpty = algorithmType == null || gameType == null || heuristicsChosen == null;
        if (isAnyFieldEmpty){
            Alert alert = new Alert(Alert.AlertType.ERROR, ERROR_MESSAGE, ButtonType.OK);
            alert.showAndWait();
            return;
        }
        GameParameters gameParameters = translateStringInputToGameParameters(algorithmType, gameType, heuristicsChosen);
        currentGame = new MainGame(gameParameters);
    }

    private boolean areAllComboBoxesSet(){
        return algorithmTypeCombo.getValue() != null && gameTypeCombo.getValue() != null
                && heuristicsChoiceCombo.getValue() != null;
    }

    private GameParameters translateStringInputToGameParameters(String algorithmType, String gameType,
                                                                String heuristicsChosen){
        GameParameters.AlgorithmType algorithm;
        GameParameters.HeuristicsType heuristics;
        GameParameters.GameType game;

        if (algorithmType.equalsIgnoreCase("A1")){
            algorithm = GameParameters.AlgorithmType.ALGORITHM_ONE;
        }
        else{
            algorithm = GameParameters.AlgorithmType.ALGORITHM_TWO;
        }

        if (heuristicsChosen.equalsIgnoreCase("H1")){
            heuristics = GameParameters.HeuristicsType.H1;
        }
        else{
            heuristics = GameParameters.HeuristicsType.H2;
        }

        if (gameType.equalsIgnoreCase("G1")){
            game = GameParameters.GameType.PLAYER_VS_AI;
        }
        else{
            game = GameParameters.GameType.AI_VS_AI;
        }

        return new GameParameters(algorithm, heuristics, game);

    }
}
