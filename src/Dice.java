
public class Dice {
	
	private int sides;
	
	Dice(int sides){
		this.sides = sides;
	}
	
	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
	
// rollDie can either be called on an instance of a dice, and uses this.sides. 
//	or it can be called as a static method with an integer argument that sets the number of sides the die you are rolling has
	public static int rollDie(int sides) {
		return (int) (Math.random() * sides) + 1;
	}
	
	public int rollDie() {
		return (int) (Math.random() * this.sides) + 1;
	}
	
}
