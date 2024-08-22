package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[3][3];
        initializeField(field);
        printField(field);

        // USERS MOVE
        boolean xTurn = true;
        while(true) {
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");

            if (coordinates.length != 2 || !isNumeric(coordinates[0]) || !isNumeric(coordinates[1])) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row = Integer.parseInt(coordinates[0]) - 1;
            int col = Integer.parseInt(coordinates[1]) - 1;

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (field[row][col] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            field[row][col] = xTurn ? 'X' : 'O';
            printField(field);

            String result = analyzeGameState(field);
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }
            xTurn = !xTurn;

        }
    }

    // INITIALIZE FIELD
    private static void initializeField(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
    }

    // PRINT FIELD
    private static void printField(char[][] field) {
        System.out.println("---------");
        for (char[] row : field) {
            System.out.print("| ");
            for (char element : row) {
                System.out.print(element + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // ANALYZE GAME STATE
    private static String analyzeGameState(char[][] field) {
        int xCount = 0;
        int oCount = 0;
        boolean xWins = false;
        boolean oWins = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') xCount++;
                if (field[i][j] == 'O') oCount++;
            }
        }

        // CHECK ROWS AND COLUMNS
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2]) {
                if (field[i][0] == 'X') xWins = true;
                if (field[i][0] == 'O') oWins = true;
            }
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                if (field[0][i] == 'X') xWins = true;
                if (field[0][i] == 'O') oWins = true;
            }
        }

        // CHECK DIAGONALS
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            if (field[0][0] == 'X') xWins = true;
            if (field[0][0] == 'O') oWins = true;
        }
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            if (field[0][2] == 'X') xWins = true;
            if (field[0][2] == 'O') oWins = true;
        }

        // CHECK GAME STATE
        if (Math.abs(xCount - oCount) > 1 || (xWins && oWins)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (xCount + oCount == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    // CHECK IF USER ENTERS NUMERIC VALUES
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
