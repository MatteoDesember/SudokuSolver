class Sudoku {

    private static int[][] table;

    static void solve(int[][] inputTable) {

        table = inputTable;

        // Display input table
        System.out.println("Input table:");
        displayTable(inputTable);

        // process table
        boolean isSolution = start(0, 0);

        if (isSolution) {
            System.out.println("Solution: ");
            displayTable(table);
        } else {
            System.out.println("There is no solution.");
        }
        System.out.println("===========================");

    }

    private static boolean start(int row, int column) {

        // if row is 9 return true. Sudoku is solved!
        if (row == 9)
            return true;

        // if column is 9 start process next row
        if (column == 9)
            return start(row + 1, 0);

        // if there is empty cell
        if (table[row][column] == 0) {

            // check each possible digit
            for (int possibleDigit = 1; possibleDigit <= 9; possibleDigit++) {

                // if digit is valid
                if (checkCondition(row, column, possibleDigit)) {

                    // insert digit into table
                    table[row][column] = possibleDigit;

                    // check if next digit can be inserted in the next cell
                    if (start(row, column + 1)) {

                        //if so, return true
                        return true;
                    } else {

                        //else delete digit and try the next one
                        table[row][column] = 0;
                    }
                }
            }
            return false;
        } else
            // if there is no empty cell check the next one
            return start(row, column + 1);
    }

    private static boolean checkCondition(int row, int column, int digit) {

        // check if row or column contains digit
        for (int i = 0; i < 9; i++) {
            if (table[row][i] == digit || table[i][column] == digit)
                return false;
        }

        // start_row, end_row, start_column and end_column describes start and end 3x3 square indexes
        int start_row = (row / 3) * 3;
        int end_row = start_row + 3;
        int start_column = (column / 3) * 3;
        int end_column = start_column + 3;

        // check if 3x3 square contains digit
        for (int i = start_row; i < end_row; i++) {
            for (int j = start_column; j < end_column; j++) {
                if (table[i][j] == digit)
                    return false;
            }
        }
        return true;
    }

    // This function displays table with fantastic view
    private static void displayTable(int[][] table) {
        System.out.println("  -----------------------");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (j % 3 == 0)
                    System.out.print(" | ");
                else
                    System.out.print(" ");
                if (table[i][j] > 0)
                    System.out.print(table[i][j]);
                else
                    System.out.print(".");
            }
            System.out.print(" |");
            if (i == 2 || i == 5)
                System.out.println("\r\n | -----   -----   ----- |");
            else
                System.out.println();
        }
        System.out.println("  -----------------------");
    }
}

public class SudokuSolver {

    public static void main(String[] args) {
        int[][] emptySudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] normalSudoku = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int[][] hardSudoku = {
                {0, 0, 0, 0, 7, 4, 3, 1, 6},
                {0, 0, 0, 6, 0, 3, 8, 4, 0},
                {0, 0, 0, 0, 0, 8, 5, 0, 0},
                {7, 2, 5, 8, 0, 0, 0, 3, 4},
                {0, 0, 0, 0, 3, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 2, 7, 9, 8},
                {0, 0, 8, 9, 4, 0, 0, 0, 0},
                {0, 4, 0, 0, 8, 5, 9, 0, 0},
                {9, 7, 1, 3, 2, 6, 4, 8, 5}
        };
        int[][] unsolvableSudoku = {
                //--Same--|--|
                //        |  |
                //        v  v
                {0, 0, 0, 7, 7, 4, 3, 1, 6},
                {0, 0, 0, 6, 0, 3, 8, 4, 0},
                {0, 0, 0, 0, 0, 8, 5, 0, 0},
                {7, 2, 5, 8, 0, 0, 0, 3, 4},
                {0, 0, 0, 0, 3, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 2, 7, 9, 8},
                {0, 0, 8, 9, 4, 0, 0, 0, 0},
                {0, 4, 0, 0, 8, 5, 9, 0, 0},
                {9, 7, 1, 3, 2, 6, 4, 8, 5}
        };
        Sudoku.solve(emptySudoku);
        Sudoku.solve(normalSudoku);
        Sudoku.solve(hardSudoku);
        Sudoku.solve(unsolvableSudoku);
    }
}