public class Ship {
	String name;
	int health;
	
	public Ship(String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	public String getName() { return name; }
	
	public int getHealth() { return health; }
	
	public void hitShip() { health -= 1; }
	
	public Boolean shipSunk() { return health <= 0; }
	
}
