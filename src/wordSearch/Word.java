package wordSearch;

import java.util.ArrayList;

public class Word {
    private String name;
    private int startRow;
    private int startColumn;
    private ArrayList<Integer> possibleDirections=new ArrayList<Integer>();
    private ArrayList<Integer> possibleRow=new ArrayList<Integer>();
    private ArrayList<Integer> possibleColumn=new ArrayList<Integer>();
    private ArrayList<Integer> letterRowPosition=new ArrayList<Integer>();
    private ArrayList<Integer> letterColumnPosition=new ArrayList<Integer>();
    public Word(String nm) {
        name = nm;
        for (int i = 0; i < 8; i++) {
            possibleDirections.add(0);
            possibleColumn.add(0);
            possibleRow.add(0);
        }
    }
    /**
     * returns name
     */
    public String getName() {
        return name;
    }
    /**
     * returns start column
     */
    public int getStartColumn() {
        return startColumn;
    }
    /**
     * returns start row
     */
    public int getStartRow() {
        return startRow;
    }
    /**
     * sets the start column of a word
     */
    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }
    /**
     * sets the start row of a word
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    /**
     * sets possible directions
     */
    public void setPossibleDirections(int index, int val) {
        possibleDirections.set(index,val);
    }
    /**
     * sets a possible row for the word
     */
    public void setPossibleRow(int index, int val) {
        possibleRow.set(index,val);
    }
    /**
     * sets a possible column for the word
     */
    public void setPossibleColumn(int index, int val) {
        possibleColumn.set(index,val);
    }
    /**
     * returns the possible directions of a word
     */
    public int getPossibleDirection(int index){
        return possibleDirections.get(index);
    }
    /**
     * returns a possible row at a direction
     */
    public int getPossibleRow(int index){
        return possibleRow.get(index);
    }
    /**
     * returns a possible column at a direction
     */
    public int getPossibleColumn(int index){
        return possibleColumn.get(index);
    }
    /**
     * clears the possible array
     */
    public void clearPossible(){
        for (int i = 0; i <possibleDirections.size() ; i++) {
            possibleDirections.set(i,0);
        }
    }
    /**
     * returns an array of possible directions
     */
    public ArrayList<Integer> getPossibleDirections() {
        return possibleDirections;
    }
    /**
     * returns position of a letter
     */
    public int getLetterColumnPosition(int index) {
        return letterColumnPosition.get(index);
    }
    /**
     * returns position of a letter
     */
    public int getLetterRowPosition(int index) {
        return letterRowPosition.get(index);
    }
    /**
     * sets a column for a letter
     */
    public void setLetterColumnPosition(int column) {
        letterColumnPosition.add(column);
    }
    /**
     * sets  a row for a letter
     */
    public void setLetterRowPosition(int row) {
        letterRowPosition.add(row);
    }

}
