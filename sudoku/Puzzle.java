package sudoku;

import java.util.Random;
/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
   // All variables have package access
   int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
   boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
   Random random = new Random();
   
   // Constructor
   public Puzzle() {
      super();  // JPanel
   }

   public void newPuzzle(int numToGuess) {
	   
	  Generator generator = new Generator();
	   
	  int[][] hardcodedNumbers = generator.generateSudokuTemplate();
			  
      for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
         for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
            numbers[row][col] = hardcodedNumbers[row][col];
         }
      }

      // randomise which cell is not shown
      for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
         for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
            isShown[row][col] = true;
         }
      }
      for (int count=1; count<=numToGuess; count++) {	
		int rnum = random.nextInt(0,9), rnum2 = random.nextInt(0,9);
		isShown[rnum][rnum2] = false;
      }
      
   }

   //(For advanced students) use singleton design pattern for this class
}
