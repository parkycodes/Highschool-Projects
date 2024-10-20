
/*
 * Parker Zhang
 * Turner pd 3
 * on an 8x8 chess board, the knight chess piece moves randomly around board to brand new locations only.
 */
import java.util.*;

public class ParkerZhangExhaustedKnight {

	public static final int[] HORZ_DISP = { 1, 2, 2, 1, -1, -2, -2, -1 };
	public static final int[] VERT_DISP = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) {

		int[][] board = new int[8][8];
		int[] array = new int[0];
		
		int currentRow = 0;
		int currentCol = 0;
		int count = 1;
		
		array = determineMoves(board, currentRow, currentCol);
		
		while (array.length != 0) {
			
			//changes tehe value of the location so it doenst get occupied by another value
			board[currentRow][currentCol] = count;
			
			int rand = chooseRand(array);
			
			//set new position
			currentRow = currentRow + VERT_DISP[array[rand]]; 
			currentCol = currentCol + HORZ_DISP[array[rand]]; 
			count++;
			
			array = determineMoves(board, currentRow, currentCol); 
 
		}
		printBoard(board);
		System.out.println();
		System.out.println(count);
		System.out.println(3/2);

	}
	
	public static int[] determineMoves(int[][] board, int currentRow, int currentCol) {

		int[] returns = new int[0];

		//loops thorugh the displacement array
		for (int i = 0; i < VERT_DISP.length; i++) {

			int nextRow = currentRow + VERT_DISP[i];
			int nextCol = currentCol + HORZ_DISP[i];

			//check if the added displacement is inbounds and is not already occupied
			if (nextRow < board.length && nextRow >= 0 &&  nextCol< board[0].length && nextCol >= 0
					&& board[nextRow][nextCol] == 0) {

				int[] temp = new int[returns.length + 1];

				for (int c = 0; c < returns.length; c++)
					temp[c] = returns[c];

				temp[temp.length - 1] = i;
				returns = temp;
			}

		}
		return returns;
	}

	//prints formated matrix out
	public static void printBoard(int[][] board) {

		System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8");
		System.out.println();
		
		for (int row = 0; row < board.length; row++) {

			System.out.print(row + 1);
			for (int col = 0; col < board[0].length; col++) {

				System.out.print("\t" + board[row][col]);
			}
			
			System.out.println();
			System.out.println();
		}

	}

	//creates a random number
	public static int chooseRand(int[] array) {

		int rand = (int) (Math.random() * array.length);
		return rand;
	}

}
