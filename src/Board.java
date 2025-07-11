// Create the game board, will use for both players...facilitate communication between

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Board {
	
	enum Ships { 
		CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);
		
		private int health;
		
		public int getHealth() { return this.health; }
		
		private Ships(int health) { this.health = health; }
	}
	
	private ArrayList<ArrayList<Grid>> gameBoard = new ArrayList<>();
	final int boardHeightWidth = 10;
	Scanner input = new Scanner(System.in);
	
	public Board() {
		for (int currentRow = 0; currentRow < boardHeightWidth; currentRow++) {
			ArrayList<Grid> boardRow = new ArrayList<>();
			for (int currentCol = 0; currentCol < boardHeightWidth; currentCol++ ) {
				Grid newGrid = new Grid(currentCol, currentRow);
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
		this.placeShips(2);
		
	}
	
	public void placeShips(int health) {
		System.out.println("Starting Coordinate");
		int[] startCoord = getGridLocation();
		System.out.println("Ending coordinate");
		int[] endCoord = getGridLocation();
		System.out.println(Arrays.toString(startCoord));
		System.out.println(Arrays.toString(endCoord));
		if (validPlacement(startCoord, endCoord, health) != true) {
			System.out.println("Invalid coordinate");
			return;
		}
		System.out.println("Good coords");
		
	}
	
	public int[] getGridLocation() {
		int[] gridArray = new int[2];
		System.out.println("Input x coordinate: ");
		gridArray[0] = Integer.valueOf(input.nextLine());
		System.out.println("Input y coordinate: ");
		gridArray[1] = Integer.valueOf(input.nextLine());
		return gridArray;
	}
	
	public boolean validPlacement(int[] startCoord, int[] endCoord, int health) {
		ArrayList<Integer> deviationCheck = new ArrayList<>();
		int maxCoordsAllowed = 2;
		int placementSize = health - 1;
		int horizontalDeviation = endCoord[0] - startCoord[0];
		int verticalDeviation = endCoord[1] - startCoord[1];
		
		
		//Maybe replace with while loop or just each if statements with a normal boolean check
		if (horizontalDeviation != 0) {
			deviationCheck.add(startCoord[0]);
			deviationCheck.add(endCoord[0]);
		}
		if (verticalDeviation != 0) {
			deviationCheck.add(startCoord[1]);
			deviationCheck.add(endCoord[1]);
		}
		
		if (deviationCheck.size() != maxCoordsAllowed || deviationCheck.get(1) - deviationCheck.get(0) != placementSize || outOfBoundsCheck(deviationCheck) == false)
			return false;
		
		return true;
	}
	
	public boolean outOfBoundsCheck(ArrayList<Integer> coordCheck) {
		for (int coord : coordCheck) {
			if (coord < 0 || coord > 9) { return false; }
		}
		return true;
	}
	
	public String toString() {
		String returnString = ""; 
		for (int currentRow = 0; currentRow < boardHeightWidth; currentRow++) {
			String rowOut = "";
			for (int currentCol = 0; currentCol < boardHeightWidth; currentCol++) {
				rowOut += gameBoard.get(currentRow).get(currentCol) + " ";
			}
			returnString += rowOut + "\n";
		}
		return returnString;
	}

}