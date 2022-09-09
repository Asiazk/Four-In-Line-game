/**
 * This class represents an auxiliary class for the 'Four in line' game
 * @author Asia Zhivov
 *
 */
public class GameMatrix {
	// declaring variables and constants
	final int ROWS = 6;
	final int COLUMNS = 7;
	final int NUM_FOR_WIN = 4; // number of discs to win
	final int EMPTY_CELL = 0; 
	final int PLAYER_ONE = 1;
	final int PLAYER_TWO = 2;
	int currentPlayer = PLAYER_ONE;
	int[][] gameBoard;// 2D array filled with 0 by default

	// initialize a GameMatrix object
	public GameMatrix() {
		gameBoard = new int[ROWS][COLUMNS];
	}
	
	
	//
	/**
	 *  A method to check if we have a winner after adding a disc
	 * @param curRow current row
	 * @param curCol current column
	 * @return index of available row for disc
	 */
	boolean isThereAWinner(int curRow, int curCol) {
		int counter; // general discs counter
		int i;
		int j;
		
		// check the column
		i=curRow;
		counter=0;
		while (i<ROWS) {
			if(gameBoard[i][curCol]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				}
			}
			else {
				break;
			}
			i++;
		}
		
		// check the row 
		j=curCol;
		counter=0;
		while(j>=0) {
			if (gameBoard[curRow][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				} 
			}
			else {
				break;
			}
			j--;
		}
			
		j=curCol+1;	
		while(j<gameBoard.length) {
			if (gameBoard[curRow][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				}
			}
			else {
				break;
			}
			j++;
		}
		
		// check the diagonal
		i=curRow;
		j=curCol;
		counter=0;
		// check left-down diagonal
		while(j>=0 && i<gameBoard.length) {
			if (gameBoard[i][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				} 
			}
			else {
				break;
			}
			j--;
			i++;
		}
			
		
		// check right-up diagonal
		i=curRow-1;
		j=curCol+1;	
		while(i>=0 && j<gameBoard.length) {
			if (gameBoard[i][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				}
			}
			else {
				break;
			}
			j++;
			i--;
		}
		
		i=curRow;
		j=curCol;
		counter=0;
		// check right-down diagonal
		while(j<gameBoard[0].length && i<gameBoard.length) {
			if (gameBoard[i][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				} 
			}
			else {
				break;
			}
			j++;
			i++;
		}
			
		// check left-up diagonal
		i=curRow-1;
		j=curCol-1;	
		while(i>=0 && j>=0) {
			if (gameBoard[i][j]==gameBoard[curRow][curCol]) {
				counter++;
				if (counter==NUM_FOR_WIN) {
					return true;
				}
			}
			else {
				break;
			}
			j--;
			i--;
		}
		
		return false;
	}
	
	int addDisc(int j, int curplayer) {
		int freeRow = nextFreeCellInCol(j);
		
		if (freeRow!=-1)
			gameBoard[freeRow][j]=curplayer;
		
		return freeRow;
	}
	/**
	 * A method to check which row is empty in the chosen column
	 * @param j the index of chosen column
	 * @return index of available row, if there is no availble row return -1
	 */
	int nextFreeCellInCol(int j) {
		int i;
		for (i=0; i<gameBoard.length; i++) {
			if (gameBoard[i][j]!=0)
				return i-1;
		}
		return i-1;
	}
	
	/**
	 * Check if we reached a tie
	 * @return if we have reached a tie - return true
	 */
	boolean isTie() {
		for (int j=0; j<gameBoard[0].length; j++) {
			if(gameBoard[0][j]==0) {
				return false;
			}
		}
		return true;
	}
}