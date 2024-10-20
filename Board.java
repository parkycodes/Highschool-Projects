/*
 * parker Zhang
 * Turner pd 3
 * Sliding totoro that consists of a grid and the client can move totoro and create/change the Board object
 */
public class Board implements Comparable<Board> {

	private int[][] curBoard;
	private int movesMade; // how many moves to reach this board
	private Board previousBoard;
	private int[] totLoc; // location of totoro. [0] == row
	private int manhattan;

	public Board(int[][] c, int m, Board p) {
		
		movesMade = m;
		
		previousBoard = p;
		totLoc = new int[2];
		
		curBoard= new int[c.length][c[0].length];
		
		int total = 0;
		
		for (int row = 0; row < c.length; row++) {
			
			for (int col = 0; col < c[0].length; col++) {
				
				curBoard[row][col] = c[row][col];
				
				
				if (c[row][col] != 0) {
					
					//calculating ideal position
					int idealR= c[row][col] /c[0].length;
					if(c[row][col] %c[0].length==0)
						idealR--;
					
					int idealC = c[row][col] - c[0].length*idealR-1;
					
					//finds the difference between ideal and reality position
					total += Math.abs(idealR-row) + Math.abs(idealC-col);
				}
				else {
					totLoc[0]=row; 
					totLoc[1]=col;
				}
			}
		}
		manhattan = total;
	}

	public int compareTo(Board other) {

		return (this.manhattan + this.movesMade) - (other.manhattan + other.movesMade);

	}

	public boolean sameTotLoc(Board other) {

		return totLoc[0] == other.totLoc[0] && totLoc[1] == other.totLoc[1];

	}

	public boolean equals(Object o) {

		if (o == null || (!(o instanceof Board)))
			return false;
		Board otherBoard = (Board) o;

		if (sameTotLoc(otherBoard)) {
			for (int r = 0; r < curBoard.length; r++) {
				for (int c = 0; c < curBoard.length; c++)
					if (curBoard[r][c] != otherBoard.curBoard[r][c])
						return false;
			}
			return true;
		} else
			return false;
	}

	//swaps the parameters of row and col with the totoro location, assuming parameters are in bounds and adjacent
	public void Swap(int row, int col) {

		int tempNum = curBoard[row][col];

		this.curBoard[row][col] = 0;

		this.curBoard[totLoc[0]][totLoc[1]] = tempNum;
		
		//set new totLoc array elements
		totLoc[0]=row;
		totLoc[1]=col;
		
		this.curBoard[row][col]=0;

	}

	//prints the matrix
	public void Print() {

		for (int row = 0; row < curBoard.length; row++) {

			for (int col = 0; col < curBoard[0].length; col++) {

				if (curBoard[row][col] == 0)
					System.out.print(" totoro ");

				else
					System.out.print(" " + curBoard[row][col] + " ");
			}
			System.out.println();
		}

	}

	//returns true if all board pieces are in the correct order
	public boolean gameOver() {
		
		//checks if the bottom right has totoro in it
		if (curBoard[curBoard.length - 1][curBoard[0].length - 1] != 0)
			return false;
		
		int count = 1;
		//loops through and checks if count == the element
		for (int r = 0; r < curBoard.length; r++) {

			for (int c = 0; c < curBoard[0].length; c++) {

				if (curBoard[r][c] != count && count < curBoard.length * 2)
					return false;

				count++;
			}

		}
		return true;
	}

	//checks if the matrix is solvable based on the out of order count
	public boolean isSolveable() {
		
		int count = 0;
		//loops though matrix to get out of order count
		for (int r = 0; r < curBoard.length; r++) {

			for (int c = 0; c < curBoard[0].length; c++) {
				
				//checks if pos = totoro
				if (curBoard[r][c] != 0) {
					
					for(int c1 = c; c1< curBoard[0].length; c1++) {
						if (curBoard[r][c1] != 0 && curBoard[r][c] > curBoard[r][c1])
							count++;
					}
					
					//checks every num after row and col
					for (int r1 = r +1; r1 < curBoard.length; r1++) {

						for (int c1 = 0; c1 < curBoard[0].length; c1++) {


							if (curBoard[r1][c1] != 0 && curBoard[r][c] > curBoard[r1][c1])
								count++;

						}
					}

				}
			}
		}
		//checks the conditions regarding out of order count and lengths of the matrix
		if (curBoard.length % 2 != 0)
			return count % 2 == 0;

		else {

			if (totLoc[0] % 2 == 0)
				return count % 2 != 0;

			else
				return count % 2 == 0;

		}
	}
}
