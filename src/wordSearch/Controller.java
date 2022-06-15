package wordSearch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;


public class Controller {
    /**Potential order:
     *IDEAS in rough order of difficulty
     *have a screen with a 15*15 grid of buttons
     * make words randomly appear at first in a random row or column
     * have the user click on words to make the word
     * have them click submit
     * have different difficulties
     * start getting diagonals to work
     * have a timer
     * leaderboard
     * classes: gameBoard, word
     * have hints
     * maybe powerups and a shop
     *
     * NOTES
     *
     */
    @FXML private ListView lstWords,lstWordsFound,lstLeaderboard;
    Button[][] btn = new Button[15][15];
    @FXML private AnchorPane aPane;
    @FXML private GridPane gPane;
    @FXML private Label lblMessages;
    private boolean started,developerMode=false;
    private long start,finish;
    private ArrayList<Long> leaderBoard=new ArrayList<Long>();
    GameBoard game=new GameBoard();
    
    /**
     * pre: user is a developer and wants to test quickly
     * post: extra letters not present
     */
    @FXML private void setDeveloperMode(){
        if (developerMode){
            developerMode=false;
            lblMessages.setText("Developer mode off(click new board to show changes)");

        }
        else {
            developerMode=true;
            lblMessages.setText("Developer mode on(click new board to show changes)");
        }
    }
    /**
     * pre:user has selected a word to check
     * post: user knows if they found the word
     */
    @FXML private void checkIfWordRight(){
        try {
            if (game.checkIfWordFound(lstWords.getSelectionModel().getSelectedIndex())) {
                lstWordsFound.getItems().add(game.returnWord(lstWords.getSelectionModel().getSelectedIndex()).getName());
                game.removeWords(lstWords.getSelectionModel().getSelectedIndex());
                lstWords.getItems().remove(lstWords.getSelectionModel().getSelectedIndex());
            }
        }
        catch (ArrayIndexOutOfBoundsException err){
            lblMessages.setText("Please select a word to check");
        }
        if (game.returnListOfWords().size()==0){
            timer();
        }
    }

    /**
     * pre: user started the game;
     * post: the button array is set up
     */
    public void setUp(){
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j <btn.length ; j++) {
                btn[i][j]=new Button();
                btn[i][j].setMinHeight(40);
                btn[i][j].setMinWidth(40);
            }
        }
     
    }
    /**
     * pre: game has begun, timer started
     * post: result is shown for the user
     */
    public void timer(){
        https://www.baeldung.com/java-measure-elapsed-time

        finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        leaderBoard.add(timeElapsed/1000);
        sortLeaderBoard();
        lstLeaderboard.getItems().clear();
        if(leaderBoard.size()<5){
            for (int i = 0; i < leaderBoard.size(); i++) {
                if (developerMode) lstLeaderboard.getItems().add(leaderBoard.get(i)+" seconds (dev)");
                else lstLeaderboard.getItems().add(leaderBoard.get(i)+"seconds");
            }
        }
        else{
            for (int i = 0; i <5; i++) {
                if (developerMode) lstLeaderboard.getItems().add(leaderBoard.get(i)+" seconds (dev)");
                else lstLeaderboard.getItems().add(leaderBoard.get(i)+"seconds");
            }
        }
    }

    /**
     * pre:leaderboard has some values
     * post: leaderboard is sorted
     */
    public void sortLeaderBoard(){
        for (int i = 0; i < leaderBoard.size(); i++) {
            for (int j = 1; j <leaderBoard.size() ; j++) {
                if (leaderBoard.get(j - 1) > leaderBoard.get(j)){
                    long temp= leaderBoard.get(j - 1);
                    leaderBoard.set(j-1,leaderBoard.get(j));
                    leaderBoard.set(j,temp);
                }
            }
        }
    }
    /**
     * pre: user has initialized the game
     * post: the board is displayed
     */
    @FXML private void newBoard(ActionEvent event) {
        game.resetBOARD();
        start = System.currentTimeMillis();
        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){
                btn[i][j].setText("*");
                btn[i][j].setStyle("-fx-background-color: black;");
                game.fillBoard(btn[i][j].getText(),i,j);

            }
        }
        game.placeWord();
        if (!developerMode){
            game.fillBoardWithCharacters();
        }
        else {
            for (int i = 0; i < btn.length; i++) {
                for (int j = 0; j < btn.length; j++) {
                    if (game.getBoardItem(i, j).equals("*")) {
                        game.fillBoard("", i, j);
                    }
                }
            }
        }
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j <btn.length ; j++) {
                btn[i][j].setText(game.getBoardItem(i,j));
                if (!started) gPane.add(btn[i][j], j, i);

            }
        }
        if (!started) started=true;
        gPane.setVisible(true);
        EventHandler z = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t)
            {
                if (!game.returnIfClicked(GridPane.getRowIndex(((Button) t.getSource())),GridPane.getColumnIndex(((Button) t.getSource())))) {
                    game.setIfClicked(GridPane.getRowIndex(((Button) t.getSource())), GridPane.getColumnIndex(((Button) t.getSource())), true);
                }
                else {
                    game.setIfClicked(GridPane.getRowIndex(((Button) t.getSource())), GridPane.getColumnIndex(((Button) t.getSource())), false);
                }
                if (game.returnIfClicked(GridPane.getRowIndex(((Button) t.getSource())), GridPane.getColumnIndex(((Button) t.getSource())))){
                    ((Button) t.getSource()).setStyle("-fx-background-color: green;");
                }
                else {
                    ((Button) t.getSource()).setStyle("-fx-background-color: black;");
                }
//                System.out.println(game.returnIfClicked(GridPane.getRowIndex(((Button) t.getSource())), GridPane.getColumnIndex(((Button) t.getSource()))));

            }

        };
        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){
                btn[i][j].setOnMouseClicked(z);
            }
        }
        lstWords.getItems().clear();
        lstWordsFound.getItems().clear();
        for (Word temp:game.returnListOfWords()) {
            lstWords.getItems().add(temp.getName());
        }
    }

}
