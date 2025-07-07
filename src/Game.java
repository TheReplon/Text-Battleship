/*
 * Game: Runs the main loop for the game itself
 * Board: Handles the game board
 * Ship: Abstract class
 * (Ship types): Customized information for each type of ship
 * Grid location: Contains information on the grid square, if ship type then update ship object when shot, otherwise update to show already shot location
 */
public class Game {

	public static void main(String[] args) {
		Board newBoard = new Board();
		System.out.println(newBoard.toString());
	}

}
