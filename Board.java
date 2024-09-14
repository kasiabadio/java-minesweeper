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
        board[i][j].setUpCell('X');
    }

    public void printBoard(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                System.out.print(board[i][j].getValue());
            }
            System.out.println();
        }
    }

    public void placeRandomMines(int numberOfMines){
        int currentNumberOfMines = 0;
        while (currentNumberOfMines < numberOfMines){
            int randomNumX = ThreadLocalRandom.current().nextInt(0, 9);
            int randomNumY = ThreadLocalRandom.current().nextInt(0, 9);
            int value = board[randomNumX][randomNumY].getValue();
            if (value == '.'){
                board[randomNumX][randomNumY].setUpCell('X');
                currentNumberOfMines += 1;
            }
        }
    }
}
