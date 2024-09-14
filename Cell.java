public class Cell {
    private char value;
    private char mines;
    private int numberOfMines;

    public Cell(char value){
        this.value = value;
        this.mines = value;
        this.numberOfMines = 0;
    }

    public void setValue(char value){
        this.value = value;
        this.mines = value;
    }

    public char getValue(){
        return this.value;
    }

    public char getMines(){
        return this.mines;
    }

    public void setNumberOfMines(int numberOfMines){
        this.numberOfMines = numberOfMines;
        if (this.value != 'X' && numberOfMines != 0) {
            this.mines = Integer.toString(this.numberOfMines).charAt(0);
        }
    }

    public int getNumberOfMines(){
        return this.numberOfMines;
    }

    public boolean isMineCell(){
        if (this.value == 'X'){
            return true;
        }
        return false;
    }
}
