package neatBacto;

public class Bacteria {
	private double speed;
	private double strength;
	private double defense;
	private double reproduction;
	
	public Bacteria(double speed, double strength,double defense,double reproduction){
		this.speed= speed;
		this.strength=strength;
		this.defense=defense;
		this.reproduction=reproduction;
	}

	public double getSpeed() {
		return speed;
	}

	public double getStrength() {
		return strength;
	}

	public double getDefense() {
		return defense;
	}

	public double getReproduction() {
		return reproduction;
	}
	
}
