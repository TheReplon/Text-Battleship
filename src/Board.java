// Create the game board, will use for both players...facilitate communication between

import java.util.ArrayList;



public class Board {
	
	enum Ships { 
		CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);
		
		private int health;
		
		public int getHealth() { return this.health; }
		
		private Ships(int health) { this.health = health; }
	}
	
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
		Ship newShip2 = new Ship("B", 4);
		gameBoard.get(0).get(2).fillGrid();
		gameBoard.get(0).get(2).setShipName(newShip2.getName().substring(0,1));
		
		for (Ships ship : Ships.values()) {
			Ship newShip = new Ship(ship.name().substring(0,1), ship.getHealth());
			System.out.println("" + newShip.getName() + " " + newShip.getHealth());
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