package neatBacto;

public class Phage {
	private double radius;
	private Field field;
	private Bacteria type;
	private double x;
	private double y;
	private double units;
	
	public Phage(Field field){
		radius=30;
		
	}
	
	public Phage(Field field,Bacteria type, double x,double y, double units){
		this.radius=30;
		this.field=field;
		this.type=type;
		this.x=x;
		this.y=y;
		this.units=units;
	}
	
	public double distance(Phage des){
		double dx=x-des.getX();
		double dy=y-des.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	void attackPhage(Phage obj){
		double time=distance(obj)/type.getSpeed();
		units=units/2;
		Attack att=new Attack(type,time,units,obj);
		field.addAttack(att);
	}

	void recieveAttack(Attack attack) {
		if(!type.equals(attack.getType())){
			
		}
	}

	public double getY() {
		return x;
	}

	public double getX() {
		return y;
	}
}
