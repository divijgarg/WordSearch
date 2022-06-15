package wordSearch;

import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private String[][] board =new String[15][15];
    private boolean[][] ifClicked=new boolean[15][15];
    ArrayList<Word> words=new ArrayList<Word>();
    private Dictionary diction;

    public GameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "*";
            }
        }
        diction=new Dictionary();
    }
    /**
     * pre:game has been completed
     * post: board has been reset and new values added
     */
    public void resetBOARD(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "*";
                ifClicked[i][j]=false;
            }
        }
        words.clear();
        for (int i = 0; i <10 ; i++) {
            String wordToAdd;
            boolean inArray=false;
            do{
                inArray=false;
                wordToAdd=diction.wordAtIndex(returnRandInt(0,3242));
                for (Word word : words) {
                    if (wordToAdd.equals(word.getName())) {
                        inArray = true;
                        break;
                    }
                }
                if (wordToAdd.length()<4)inArray=true;
            } while (inArray);
            words.add(new Word(wordToAdd));
        }

    }
    /**
     * returns a random number
     */
    public int returnRandInt(int num1, int num2){
        return (int)((Math.random()*(num2-num1+1))+num1);
    }
    /**
     * pre: board has random letters loaded in
     * post: a word is put into the data
     * use random numbers to find a spot for the word
     */
    public void placeWord() {
        boolean[] checkForDirection = new boolean[4];
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).getPossibleDirections().size(); j++) {
                selectRandomPosition(i,j);
            }

//            place the words
            int indexOfMove = returnRandInt(0, words.get(i).getPossibleDirections().size()-1);
            int amountDenied=0;
            for (int k = 0; k < words.get(i).getPossibleDirections().size(); k++) {
                if (words.get(i).getPossibleDirection(k)==0) amountDenied++;
            }
            if (amountDenied== words.get(i).getPossibleDirections().size()) {
                words.remove(i);
                i--;
                break;
            }
            while (words.get(i).getPossibleDirection(indexOfMove) == 0) {
                indexOfMove = returnRandInt(0, words.get(i).getPossibleDirections().size()-1);
            }
            if (indexOfMove == 0) {
                for (int j = 0; j < words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(0)][words.get(i).getPossibleColumn(0)+j] = words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove));
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)+j);
                }
            }
            else if (indexOfMove == 1) {
                for (int j = 0; j < words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(1)+j][words.get(i).getPossibleColumn(1)] = words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)+j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove));
                }
            }
            else if (indexOfMove==2){
                for (int j = 0; j <words.get(i).getName().length() ; j++) {
                    board[words.get(i).getPossibleRow(2)][words.get(i).getPossibleColumn(2)-j]=words.get(i).getName().charAt(j)+"";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove));
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)-j);
                }
            }
            else if (indexOfMove==3){
                for (int j = 0; j < words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(3)-j][words.get(i).getPossibleColumn(3)] = words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)-j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove));
                }
            }
            else if (indexOfMove==4){
                for (int j = 0; j <words.get(i).getName().length() ; j++) {
                    board[words.get(i).getPossibleRow(4)+j][words.get(i).getPossibleColumn(4)+j]=words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)+j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)+j);
                }
            }
            else if (indexOfMove==5){
                for (int j =0; j<words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(5)-j][words.get(i).getPossibleColumn(5)-j]=words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)-j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)-j);
                }
            }
            else if (indexOfMove==6){
                for (int j =0; j<words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(indexOfMove)-j][words.get(i).getPossibleColumn(indexOfMove)+j]=words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)-j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)+j);
                }
            }
            else if (indexOfMove==7){
                for (int j =0; j<words.get(i).getName().length(); j++) {
                    board[words.get(i).getPossibleRow(indexOfMove)+j][words.get(i).getPossibleColumn(indexOfMove)-j]=words.get(i).getName().charAt(j) + "";

                    words.get(i).setLetterRowPosition(words.get(i).getPossibleRow(indexOfMove)+j);
                    words.get(i).setLetterColumnPosition(words.get(i).getPossibleColumn(indexOfMove)-j);
                }

            }
            words.get(i).clearPossible();
        }
    }
    /**
     * pre: board has been reset and words decided
     * post: words have a potential position
     */
    public void selectRandomPosition(int index, int direction){
        words.get(index).setStartColumn(returnRandInt(0,14));
        words.get(index).setStartRow(returnRandInt(0,14));
        int counter=0;
        while(!returnIfAllSame(direction,index)){
            counter++;
            words.get(index).setStartColumn(returnRandInt(0,14));
            words.get(index).setStartRow(returnRandInt(0,14));
            if (counter==1000) break;
        }
        if (returnIfAllSame(direction,index)) {
            words.get(index).setPossibleDirections(direction, 1);
            words.get(index).setPossibleRow(direction, words.get(index).getStartRow());
            words.get(index).setPossibleColumn(direction, words.get(index).getStartColumn());
        }

    }
    /**
     * pre:row and column
     * post: board has that position filled
     */
    public void fillBoard(String letter, int row, int column){
        board[row][column]=letter;
    }

    /**
     *returns the board item called
     */
    public String getBoardItem(int row, int column) {
        return board[row][column];
    }
    /**
     * pre:row and column
     * post:sets a position as clicked or not
     */
    public void setIfClicked(int row, int column, boolean val){
        ifClicked[row][column]=val;
    }
    /**
     * pre: index of word to check and has a row and column
     * post: returns if the words position is allowed
     */
    public boolean returnIfAllSame(Integer val, int index ){
        int counter=0;
        if (val==0) {
            for (int i = 0; i < words.get(index).getName().length(); i++) {
                if (words.get(index).getStartColumn() + i>=15) return false;

                if (board[words.get(index).getStartRow()][words.get(index).getStartColumn()+i].equals("*")||board[words.get(index).getStartRow()][words.get(index).getStartColumn()+i].equals(""+words.get(index).getName().charAt(i))) {
                    counter++;
                }
            }
        }
        else if (val==1){
            for (int i = 0; i < words.get(index).getName().length(); i++) {
                if (words.get(index).getStartRow() + i>=15) return false;

                if (board[words.get(index).getStartRow() + i][words.get(index).getStartColumn()].equals("*")||board[words.get(index).getStartRow()+i][words.get(index).getStartColumn()].equals(""+words.get(index).getName().charAt(i))) {
                    counter++;
                }
            }
        }
        else if (val==2){
            for (int i = 0; i < words.get(index).getName().length(); i++) {
                if (words.get(index).getStartColumn() - i<0) return false;


                if (board[words.get(index).getStartRow()][words.get(index).getStartColumn()-i].equals("*")||board[words.get(index).getStartRow()][words.get(index).getStartColumn()-i].equals(""+words.get(index).getName().charAt(i))) {
                    counter++;
                }
            }
        }
        else if (val==3){
            for (int i = 0; i < words.get(index).getName().length(); i++) {
                if (words.get(index).getStartRow() - i<0) return false;
                if (board[words.get(index).getStartRow() - i][words.get(index).getStartColumn()].equals("*")||board[words.get(index).getStartRow()-i][words.get(index).getStartColumn()].equals(""+words.get(index).getName().charAt(i))) {
                    counter++;
                }
            }
        }
        else if(val==4) {
            /**
             * diagonal
             * check if every piece along the diagonal is *
             * use a double for loop to go through the locations
             */
            int k = words.get(index).getStartColumn();
            for (int j = words.get(index).getStartRow(); j < words.get(index).getStartRow() + words.get(index).getName().length(); j++) {
                if (j >= 15 || k >= 15) return false;
                if (board[j][k].equals("*")|| board[j][k].equals(words.get(index).getName().charAt(j - words.get(index).getStartRow()) + "")) {
                    counter++;
                }
                k++;
            }
        }
        else if(val==5) {
            /**
             * diagonal
             * check if every piece along the diagonal is *
             * use a double for loop to go through the locations
             */
            int k = words.get(index).getStartColumn();
            for (int j = words.get(index).getStartRow(); j>words.get(index).getStartRow()-words.get(index).getName().length(); j--) {
                if (j <0 || k <0) return false;

                if (board[j][k].equals("*")|| board[j][k].equals(words.get(index).getName().charAt( words.get(index).getStartRow()-j) + "")) {
                    counter++;
                }
                k--;
            }
        }
        else if(val==6) {
            /**
             * diagonal
             * check if every piece along the diagonal is *
             * use a double for loop to go through the locations
             * right up
             */
            int k = words.get(index).getStartColumn();
            for (int j = words.get(index).getStartRow();j>words.get(index).getStartRow()-words.get(index).getName().length(); j--) {
                if (j >= 15 || k <0||j<0||k>14) return false;
                if (board[j][k].equals("*")|| board[j][k].equals(words.get(index).getName().charAt(words.get(index).getStartRow()-j) + "")) {
                    counter++;
                }
                k++;
            }
        }
        else if(val==7) {
            /**
             * diagonal
             * check if every piece along the diagonal is *
             * use a double for loop to go through the locations
             * left down
             */
            int k = words.get(index).getStartColumn();
            for (int j = words.get(index).getStartRow(); j < words.get(index).getStartRow() + words.get(index).getName().length(); j++) {
                if (j >= 15 || k <0||j<0||k>14) return false;
                if (board[j][k].equals("*")|| board[j][k].equals(words.get(index).getName().charAt( j-words.get(index).getStartRow()) + "")) {
                    counter++;
                }
                k--;
            }
        }
        return counter==words.get(index).getName().length();
    }
    /**
     *returns list of words
     */
    public ArrayList<Word> returnListOfWords(){
        return words;
    }
    /**
     * returns if a position is clicked
     */
    public boolean returnIfClicked(int row, int column){
        return ifClicked[row][column];
    }
    /**
     * pre:index of word
     * post: checks if a word has been found by a user
     */
    public boolean checkIfWordFound(int index) {
        int counter = 0;
        if (ifClicked[words.get(index).getLetterRowPosition(0)][words.get(index).getLetterColumnPosition(0)]) {
            counter++;
        }
        if (ifClicked[words.get(index).getLetterRowPosition(words.get(index).getName().length()-1)][words.get(index).getLetterColumnPosition(words.get(index).getName().length()-1)]) {
            counter++;
        }
        return counter == 2;
    }
    /**
     * returns a word
     */
    public Word returnWord(int index){
        return words.get(index);
    }
    /**
     * removes words
     */
    public void removeWords(int index){
        words.remove(index);
    }
    /**
     * pre: words have been found
     * post: entire board is filled
     */
    public void fillBoardWithCharacters(){
        ArrayList<Character> letters=new ArrayList<Character>();
        for (int i = 97; i <= 122; i++) {
            letters.add((char) i);
        }
        for (int i = 0; i <words.size() ; i++) {
            for (int j = 0; j <3 ; j++) {
                letters.add(words.get(i).getName().charAt(0));
            }
        }
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("*")){
                    board[i][j]=letters.get(returnRandInt(0,letters.size()-1))+"";
                }
            }
        }
        System.out.println(letters.toString());
    }
}
