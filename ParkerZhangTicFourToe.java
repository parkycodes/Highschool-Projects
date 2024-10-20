
/*
 * Parker Zhang
 * Turner pd  3
 * this program checks to if a board has a winning stae or not, not facilitating a game of tic four tac; no user input
 */

import java.util.*;

//4x4 version of Tic Tac Toe .  Q1, CP2
public class ParkerZhangTicFourToe {
	public static void main(String[] args) {
		
		char[][] board = { { 'x', 'o', 'x', 'x' }, { 'x', 'o', 'o', 'x' }, { 'o', 'x', 'x', 'o' },
				{ 'x', 'o', 'x', 'o' } };

		
		if (hasWon(board,'o'))
			System.out.println("o wins!");
		
		else if(hasWon(board,'x'))
			System.out.println("X wins!");
		
		else {
			System.out.println("Tie!");
		}
		
	}

	// rowOff and colOff represent the offset for where to start looking within the
	// board
	public static boolean checkQuad(char[][] board, int rowOff, int colOff, char target) {

		int half = board.length / 2;

		for (int row = 0; row < half; row++) {
			
			for (int col = 0; col < half; col++) {
				
				if (board[row + rowOff][col + colOff] != target)
					return false;
				
			}
		}

		return true;
	}

	// returns true if the target is found in all four corners
	public static boolean checkCorners(char[][] board, char target) {

		if (board[0][0] == target) {

			if (board[0][board[0].length-1] == target && board[board.length-1][0] == target
					&& board[board.length-1][board[0].length-1] == target) {
				return true;
			}
		}
		return false;
	}

	// returns true if target is found in ever location for provided row
	public static boolean checkRow(char[][] board, int row, char target) {


		for (int i = 0; i < board[0].length; i++) {

			if (board[row][i] != target) {
				return false;
			}

		}
		return true;
	}
	

	// returns true if target is found in ever location for provided column
	public static boolean checkCol(char[][] board, int col, char target) {

		for (int i = 0; i < board[0].length; i++) {

			if (board[i][col] != target) {
				return false;
			}
		}
		return true;
	}

	// returns true if target is found in every loc of major diagonal;
	public static boolean checkForwardDiag(char[][] board, char target) {

		for (int rowcol = 0; rowcol < board.length; rowcol++) {

			if (board[rowcol][rowcol] != target) {
				return false;
			}
		}
		return true;
	}
	
	//returns true if the bottom left to top right target found
	public static boolean checkBackDiag(char[][]board, char target) {
		
		int backCount = 0;
		for(int rowcol=board.length-1; rowcol>=0; rowcol--) {
			
			if(board[rowcol][backCount]!=target) {
		
				return false;
			}
			backCount++;
		}
		return true;
		
	}
	
	//examines all quadrants and calls function to check each quadrant
	public static boolean allQuads(char[][]board, char target) {
		
		int hLength = board.length/2;
		int counter=0;
		for(int row=0; row<board.length;row+=(hLength)) {
			
			if(!checkQuad(board,row,counter,target)||!checkQuad(board,row,counter+hLength,target)) {
				return false;
			}
			
		}
		return true;
	}
	
	//returns true if target has won game
	public static boolean hasWon(char[][]board, char target) {
		
		if(checkCorners(board, target))
			return true;
		
		else if(checkForwardDiag(board,target))
			return true;
		
		else if(checkBackDiag(board,target))
			return true;
		
		else if(allQuads(board,target)) 
			return true;
		
		//loop to check column and row
		for(int rowCol =0; rowCol<board.length;rowCol++) {
			if(checkRow(board,rowCol, target)||checkCol(board,rowCol,target)) {
				return true;
			}
		}
		return false;
	}

}
