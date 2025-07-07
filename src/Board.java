// Create the game board, will use for both players...facilitate communication between

import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Grid>> gameBoard = new ArrayList<>();
	
	public Board() {
		for (int row = 0; row < 10; row++) {
			ArrayList<Grid> boardRow = new ArrayList<>();
			for (int col = 0; col < 10; col++ ) {
				Grid newGrid = new Grid(col, row);
				boardRow.add(newGrid);
			}
			gameBoard.add(boardRow);
		}
	}
	
	public String toString() {
		String returnString = ""; 
		for (int row = 0; row < 10; row++) {
			String rowOut = "";
			for (int col = 0; col < 10; col++) {
				rowOut += gameBoard.get(row).get(col) + " ";
			}
			returnString += rowOut + "\n";
		}
		return returnString;
	}

}