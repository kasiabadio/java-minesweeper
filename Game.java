import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on a 9x9 field?");
        int numberOfMines = scanner.nextInt();
        board.placeRandomMines(numberOfMines);
        board.printBoard();
        board.calculateNumberOfMinesAroundCells();
        board.printBoardCalc();
    }
}
