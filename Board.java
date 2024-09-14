import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public static final int BOARD_SIZE = 9;
    private Cell[][] board;

    public Board(){
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new Cell('.');
            }
        }
    }

    public Cell[][] getBoard(){
        return board;
    }

    public void setBoardCell(int i, int j){
        board[i][j].setValue('X');
    }

    public void printBoard(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                System.out.print(board[i][j].getValue());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoardCalc(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                System.out.print(board[i][j].getMines());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeRandomMines(int numberOfMines){
        int currentNumberOfMines = 0;
        while (currentNumberOfMines < numberOfMines){
            int randomNumX = ThreadLocalRandom.current().nextInt(0, 9);
            int randomNumY = ThreadLocalRandom.current().nextInt(0, 9);
            int value = board[randomNumX][randomNumY].getValue();
            if (value == '.'){
                board[randomNumX][randomNumY].setValue('X');
                currentNumberOfMines += 1;
            }
        }
    }

    public void calculateNumberOfMinesAroundCells(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int numberOfMines = 0;
                if (i != 0 && j != 0 && i != BOARD_SIZE-1 && j != BOARD_SIZE-1){
                    numberOfMines = checkMiddle(i, j);
                }
                else if (i == 0 && j != 0 && j != BOARD_SIZE-1){
                    numberOfMines = checkLeft(i, j);
                }
                else if (i == BOARD_SIZE-1 && j != 0 && j != BOARD_SIZE-1){
                    numberOfMines = checkRight(i, j);
                }
                else if (j == 0 && i != 0 && i != BOARD_SIZE-1){
                    numberOfMines = checkUpper(i, j);
                }
                else if (j == BOARD_SIZE-1 && i != 0 && i != BOARD_SIZE-1){
                    numberOfMines = checkLower(i, j);
                } else {
                    numberOfMines = checkCorners(i, j);
                }
                board[i][j].setNumberOfMines(numberOfMines);
            }
        }
    }

    public int checkMiddle(int X, int Y){
        int numberOfMines = 0;
        for (int i = X-1; i <= X+1; i++){
            for (int j = Y-1; j <= Y+1; j++){
                if (!(i == X && j == Y)){
                    if (board[i][j].getValue() == 'X'){
                        numberOfMines += 1;
                    }
                }
            }
        }
        return numberOfMines;
    }

    public int checkLeft(int X, int Y){
        int numberOfMines = 0;
        if (board[X][Y-1].getValue() == 'X'){
            numberOfMines += 1;
        }
        if (board[X][Y+1].getValue() == 'X'){
            numberOfMines += 1;
        }
        for (int j = Y-1; j <= Y+1; j++){
            if (board[X+1][j].getValue() == 'X'){
                numberOfMines += 1;
            }
        }
        return numberOfMines;
    }

    public int checkRight(int X, int Y){
        int numberOfMines = 0;
        if (board[X][Y-1].getValue() == 'X'){
            numberOfMines += 1;
        }
        if (board[X][Y+1].getValue() == 'X'){
            numberOfMines += 1;
        }
        for (int j = Y-1; j <= Y+1; j++){
            if (board[X-1][j].getValue() == 'X'){
                numberOfMines += 1;
            }
        }
        return numberOfMines;
    }

    public int checkUpper(int X, int Y){
        int numberOfMines = 0;
        if (board[X-1][Y].getValue() == 'X'){
            numberOfMines += 1;
        }
        if (board[X+1][Y].getValue() == 'X'){
            numberOfMines += 1;
        }
        for (int i = X-1; i <= X+1; i++){
            if (board[i][Y+1].getValue() == 'X'){
                numberOfMines += 1;
            }
        }
        return numberOfMines;
    }

    public int checkLower(int X, int Y){
        int numberOfMines = 0;
        if (board[X-1][Y].getValue() == 'X'){
            numberOfMines += 1;
        }
        if (board[X+1][Y].getValue() == 'X'){
            numberOfMines += 1;
        }
        for (int i = X-1; i <= X+1; i++){
            if (board[i][Y-1].getValue() == 'X'){
                numberOfMines += 1;
            }
        }
        return numberOfMines;
    }

    public int checkCorners(int X, int Y){
        int numberOfMines = 0;
        // upper left
        if (X == 0 && Y == 0){
            if (board[X+1][Y].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X][Y+1].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X+1][Y+1].getValue() == 'X'){
                numberOfMines += 1;
            }
            // lower left
        } else if (X == 0 && Y == BOARD_SIZE-1){
            if (board[X+1][Y].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X][Y-1].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X+1][Y-1].getValue() == 'X'){
                numberOfMines += 1;
            }
            // upper right
        } else if (X == BOARD_SIZE-1 && Y == 0){
            if (board[X-1][Y].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X-1][Y+1].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X][Y+1].getValue() == 'X'){
                numberOfMines += 1;
            }
        } else if (X == BOARD_SIZE-1 && Y == BOARD_SIZE-1){
            if (board[X-1][Y].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X-1][Y-1].getValue() == 'X'){
                numberOfMines += 1;
            }
            if (board[X][Y-1].getValue() == 'X'){
                numberOfMines += 1;
            }
        }
        return numberOfMines;
    }
}
