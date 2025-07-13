// Create the game board, will use for both players...facilitate communication between

import java.util.ArrayList;
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
	ArrayList<Ship> currentShips = new ArrayList<>();
	
	public Board() {
		for (int currentRow = 0; currentRow < boardHeightWidth; currentRow++) {
			ArrayList<Grid> boardRow = new ArrayList<>();
			for (int currentCol = 0; currentCol < boardHeightWidth; currentCol++ ) {
				Grid newGrid = new Grid(currentCol, currentRow);
				boardRow.add(newGrid);
			}
			gameBoard.add(boardRow);
		}
		
		for (Ships ship : Ships.values()) {
			Ship newShip = new Ship(ship.name(), ship.getHealth());
			System.out.println("" + newShip.getName() + " " + newShip.getHealth());
			this.placeShips(newShip);
			currentShips.add(newShip);
		}
		
	}
	
	private void placeShips(Ship ship) {
		System.out.println("Starting Coordinate");
		int[] startCoord = getGridLocation();
		System.out.println("Ending coordinate");
		int[] endCoord = getGridLocation();
		Boolean checkPlacement = validPlacement(startCoord, endCoord, ship.getHealth());
		
		while (checkPlacement != true) {
			System.out.println("Invalid coordinate - Try again");
			System.out.println("Starting Coordinate");
			startCoord = getGridLocation();
			System.out.println("Ending coordinate");
			endCoord = getGridLocation();
			checkPlacement = validPlacement(startCoord, endCoord, ship.getHealth());
		}
		
		if (startCoord[0] == endCoord[0]) {
			for (int currentCol = startCoord[1]; currentCol <= endCoord[1]; currentCol++) {
				Grid shipGrid = new Grid(startCoord[0], currentCol);
				shipGrid.setShip(ship);
				shipGrid.setShipName(ship.getName().substring(0, 1));
				gameBoard.get(startCoord[0]).set(currentCol, shipGrid);
			}
		} else {
			for (int currentRow = startCoord[0]; currentRow <= endCoord[0]; currentRow++) {
				Grid shipGrid = new Grid(startCoord[0], currentRow);
				shipGrid.setShip(ship);
				shipGrid.fillGrid();
				shipGrid.setShipName(ship.getName().substring(0, 1));
				gameBoard.get(currentRow).set(startCoord[1], shipGrid);
			}
		}
		
	}
	
	private int[] getGridLocation() {
		int[] gridArray = new int[2];
		System.out.println("Input x coordinate: ");
		gridArray[0] = Integer.valueOf(input.nextLine());
		System.out.println("Input y coordinate: ");
		gridArray[1] = Integer.valueOf(input.nextLine());
		return gridArray;
	}
	
	private boolean validPlacement(int[] startCoord, int[] endCoord, int health) {
		ArrayList<Integer> deviationCheck = new ArrayList<>();
		int maxCoordsAllowed = 2;
		int placementSize = health - 1;
		int horizontalDeviation = endCoord[0] - startCoord[0];
		int verticalDeviation = endCoord[1] - startCoord[1];
		boolean checkOverlap = overlapCheck(startCoord, endCoord, health);
		
		
		//Maybe replace with while loop or just each if statements with a normal boolean check
		if (horizontalDeviation != 0) {
			deviationCheck.add(startCoord[0]);
			deviationCheck.add(endCoord[0]);
		}
		if (verticalDeviation != 0) {
			deviationCheck.add(startCoord[1]);
			deviationCheck.add(endCoord[1]);
		}
		
		if (deviationCheck.size() != maxCoordsAllowed || deviationCheck.get(1) - deviationCheck.get(0) != placementSize || outOfBoundsCheck(deviationCheck) == false || checkOverlap == false)
			return false;
		
		return true;
	}
	
	private boolean overlapCheck(int[] startCoord, int[] endCoord, int health) {
		if (startCoord[0] == endCoord[0]) {
			for (int currentCol = startCoord[1]; currentCol <= endCoord[1]; currentCol++) {
				if (gameBoard.get(startCoord[0]).get(currentCol).getIsEmpty() == false){
					return false;
				}
			}
		} else {
			for (int currentRow = startCoord[0]; currentRow <= endCoord[0]; currentRow++) {
				if (gameBoard.get(currentRow).get(startCoord[1]).getIsEmpty() == false){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean outOfBoundsCheck(ArrayList<Integer> coordCheck) {
		for (int coord : coordCheck) {
			if (coord < 0 || coord > 9) { return false; }
		}
		return true;
	}
	
	public void getPlayerShot() {
		ArrayList<Integer> shotCoords = new ArrayList<>();
		boolean validCoords = false;
		int xCord;
		int yCord;
		
		while (validCoords == false) {
			System.out.println("Shooting Phase: X coordinate: ");
			xCord = (Integer.valueOf(input.nextLine()));
			System.out.println("Shooting Phase: Y coordinate: ");
			yCord = (Integer.valueOf(input.nextLine()));
			shotCoords.add(yCord); shotCoords.add(xCord);
			if (outOfBoundsCheck(shotCoords) == true) { validCoords = true; }
			if (gameBoard.get(xCord).get(yCord).getIsShot() == true) { validCoords = false;  System.out.println("Broke here");}
				
		}
		shootGrid(shotCoords);
	}
	
	private void shootGrid(ArrayList<Integer> gridCoords) {
		int xCord = gridCoords.get(1);
		int yCord = gridCoords.get(0);
		Grid currentGrid = gameBoard.get(xCord).get(yCord);
		
		if (currentGrid.getShip() != null) {
			Ship currentShip = currentGrid.getShip();
			currentGrid.shootGrid();
			currentShip.hitShip();
			System.out.println(currentGrid.getShipName());
			System.out.println(currentShip.getHealth());
		} else {
			currentGrid.shootGrid();
		}
	}
	
	public boolean checkGameStatus() {
		boolean gameActive = true;
		int remainingHealth = 0;
		for (Ship ships : currentShips) {
			remainingHealth += ships.getHealth();
		}
		if (remainingHealth <= 0) { gameActive = false; }
		return gameActive;
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