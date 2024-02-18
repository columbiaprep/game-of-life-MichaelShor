public class Board {

  private Cell[][] board;

  public Board(int rows, int cols) {
    board = new Cell[rows][cols];
    clearBoard();
    placeFirstGen();
    displayBoard();
  }

  //loops through 2D array and sets every char to the default emoji
  public void clearBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = new Cell(false);
      }
    }
  }

  //prints the board
  public void displayBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + "\t");
      }
      System.out.println();
    }
    System.out.println();
  }

  //places first generation of bacteria on board
  public void placeFirstGen() {
    board[2][2] = new Cell(true);
    board[2][3] = new Cell(true);
    board[2][4] = new Cell(true);
    board[3][1] = new Cell(true);
    board[3][2] = new Cell(true);
    board[3][3] = new Cell(true);
  }

  //counts the number of neighbors who are alive, returns the result as an integer
	//counts all eighth neighboring spaces
  public int countLiveNeighbors(int i, int j) {
    // check spot r,c for getisAlive and then make sure that r < board.length && and c < board[j].length
    //board[i][j].getIsAlive()
    // loops around the cell i, j in the 2d matrix
    int counter = 0;
    for (int r = i - 1; r<= i+1; r++) {
      for (int c = j - 1; c <= j+1; c++) {
        // checks each adjacent cell to i, j making sure that it is not the middle cell itself and that it doesn't go out of bounds, it then
        // checks to see if that cell is alive and adds it to a counter to count the total number of alive neighbors
        if ((r!=i || c != j) && r>=0 && c>=0 && r < board.length && c < board[0].length && board[r][c].getIsAlive() == true) {
          counter ++;
        }
      }
    }
    return counter;
  }

  public void createNewGeneration() {
    //creates a blank board of same size called newGenBoard
    Cell[][] nextGenBoard = new Cell[board.length][board[0].length]; 
    //all changes should be reflected only on nextGenBoard, and we copy them over on the last line of the method

	//for each space in the nextGenBoard:
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        boolean isalive = false;

        int count = countLiveNeighbors(r, c);
        if (board[r][c].getIsAlive() && count == 2 || count == 3) {
          isalive = true;
        }
        if (!board[r][c].getIsAlive() && count == 3) {
          isalive = true;
        }
        if (!board[r][c].getIsAlive() && count == 6) {
          isalive = true;
        }
        nextGenBoard[r][c] = new Cell(isalive);
      }
    }
      //a live cell with 2-3 neighbors survives
      

      //a dead cell with 3 live neighbors becomes live

      //a live cell with 0, 1, or >=4 neighbors dies

    // a dead cell comes back to life if it is surrounded by 6 living cells
  


    //copies all changes simultaneously. this line must be last
    board = nextGenBoard;
  }

}
