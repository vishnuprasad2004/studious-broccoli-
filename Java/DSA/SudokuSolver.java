import java.util.HashSet;

public class SudokuSolver {

	/**
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
		A sudoku solution must satisfy all of the following rules:

			Each of the digits 1-9 must occur exactly once in each row.
			Each of the digits 1-9 must occur exactly once in each column.
			Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
		The '.' character indicates empty cells.
	 */
	static class Solution {
		public void solveSudoku(char[][] board) {
			solve(board, 0, 0);
		}

		public boolean solve(char[][] board, int row, int col) {
			if(row == 9) return true; // we reached the final cell successfully

			int nextRow = row;
			int nextCol = col+1;
			if(nextCol == 9) {
				nextCol = 0;
				nextRow ++;
			}

			if(board[row][col] != '.') {
				return solve(board, nextRow, nextCol);
			}

			for(char i='1'; i<= '9'; i++) {
				if(isSafe(board, row, col, i)) {
					board[row][col] = i;
					if(solve(board, nextRow, nextCol)) return true;
					board[row][col] = '.';
				}
			}
			return false;
		}

		public boolean isSafe(char[][] board, int row, int col, char c) {
			// horizontal & vertical check
			for(int i=0; i<9; i++) {
				if(board[row][i] == c) return false; 
				if(board[i][col] == c) return false; 
			}
			// smaller grid check
			int startRow = (row / 3) * 3; 
			int startCol = (col / 3) * 3; 

			for(int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					if(board[startRow+i][startCol+j] == c) return false;
				}
			}

			return true;
		}
	}

	public static boolean isValidSudoku(char[][] board) {
        HashSet<Character> rowSet = new HashSet<>();
        HashSet<Character> colSet = new HashSet<>();

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j] != '.') {
                    if(rowSet.contains(board[i][j])) {
                        return false;
                    } else {
                        rowSet.add(board[i][j]);
                    }
                }

                if(board[j][i] != '.') {
                    if(colSet.contains(board[j][i])) {
                        return false;
                    } else {
                        colSet.add(board[j][i]);
                    }
                }
            }
            rowSet.clear();
            colSet.clear();
        }

        HashSet<Character> boxSet = new HashSet<>();
        int startRow = 0;
        int startCol = 0;
        for(int x=0; x<9; x++) {

            for(int i=startRow; i<startRow+3; i++) {
                for(int j=startCol; j<startCol+3; j++) {
                    if(board[i][j] != '.') {
                        if(boxSet.contains(board[i][j])) {
                            return false;
                        } else {
                            boxSet.add(board[i][j]);
                        }
                    }
                }
            }
            startCol += 3;
            if(startCol == 9) {
                startCol = 0;
                startRow += 3;
            }
            boxSet.clear();
        }


        return true;
    }

	public static void main(String[] args) {

		char[][] board = {
				{ '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' }
		};

		Solution solver = new Solution();
		System.out.println(isValidSudoku(board) ? "This is a Valid Sudoku Puzzle.": "This is not a Valid Sudoku Puzzle.");
		solver.solveSudoku(board);

		System.out.println("Solved Sudoku:");
		printBoard(board);
	}

	static void printBoard(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
