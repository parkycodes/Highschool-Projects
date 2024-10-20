

public class Client {

	public static void main (String [] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,0,8}};
		Board totBoard = new Board(matrix, 0, null);
		totBoard.Print();
		
		if(totBoard.isSolveable())
			System.out.println("is Solveable");
		
		
		totBoard.Swap(2,2);

		if(totBoard.gameOver()) {
			System.out.println("Game Over");
		}
	}
}
