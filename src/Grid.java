
public class Grid {
	Boolean isEmpty;
	Boolean isShot;
	String shipName;
	Ship shipObject;
	
	int gridX;
	int gridY;
	
	public Grid(int gridX, int gridY) {
		this.isEmpty = true;
		this.isShot = false;
		this.shipName = "";
		
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public Boolean getIsEmpty() { return isEmpty; }
	public Boolean getIsShot() { return isShot; }
	public String getShipName() { return shipName; }
	public int getX() { return gridX; }
	public int getY() { return gridY; }
	public String getGridLocation() { return "(" + gridX + ", " + gridY + ")"; }
	public void setShip(Ship ship) { this.shipObject = ship; }
	public Ship getShip() { return shipObject; }
	
	public void fillGrid() { isEmpty = false; }
	public void shootGrid() { 
		isShot = true;
		if (shipName != "") {
			shipName = "X";
		} else {
			shipName = "*";
		}
		
		}
	public void setShipName(String setName) { shipName = setName; }
	public void setX(int x) { gridX = x; }
	public void setY(int y) { gridY = y; }
	
	
	public String toString() {
		String shipString = "";
		if (isEmpty == true)  { 
			shipString = "Empty"; 
		} else {
			shipString = shipName;
		}
		if (this.getShipName() != "") return this.getShipName();
		else return this.getShipName() + "-";
		//return "Grid status: " + shipString + " - " + isShot + " at coordinates: " + this.getGridLocation();
	}
}
