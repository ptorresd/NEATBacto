package neatBacto;

public class Attack {
	private Bacteria type;
	private double missingTime;
	private double units;
	private Phage objective;
	
	public Attack(Bacteria type,double time,double units, Phage objective){
		this.type=type;
		missingTime=time;
		this.units=units;
		this.objective=objective;
	}
	
	void update(int dt){
		missingTime-=dt;
		if(missingTime<=0){
			objective.recieveAttack(this);
		}
	}

	public Bacteria getType() {
		return type;
	}
	

	public double getUnits() {
		return units;
	}
}
