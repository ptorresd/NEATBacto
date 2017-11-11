package neatBacto;

public class Phage {
	private double radius;
	private Field field;
	private Bacteria type;
	private double x;
	private double y;
	private double units;
	
	public Phage(Field field){
		radius=20;
		this.field=field;
		this.type=new Bacteria(0,0,3,0);
		this.x=Math.random()*(640-2*radius)+radius;
		this.y=Math.random()*(480-2*radius)+radius;
		units=Math.random()*6+1;
	}
	
	public Phage(Field field,Bacteria type, double radius, double x,double y, double units){
		this.radius=radius;
		this.field=field;
		this.type=type;
		this.x=x;
		this.y=y;
		this.units=units;
	}
	
	public boolean collides(Phage p) {
		return radius+p.getRadius()>distance(p);
	}
	
	public double distance(Phage des){
		double dx=x-des.getX();
		double dy=y-des.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	void attackPhage(Phage obj){
		double time=arrivingTime(obj);
		units=units/2;
		Attack att=new Attack(type,time,units,obj);
		field.addAttack(att);
	}

	void recieveAttack(Attack attack) {
		if(!type.equals(attack.getType())){
			double enemyStr=attack.getType().getStrength();
			double enemyUnits=attack.getUnits();
			if(enemyStr*enemyUnits>units*type.getDefense()){
				units=enemyUnits-units*type.getDefense()/enemyStr;
				type=attack.getType();
			}
			else units-=enemyUnits*enemyStr/type.getDefense();
		}
		else units+=attack.getUnits();
		field.removeAttack(attack);
	}

	public double getY() {
		return x;
	}

	public double getX() {
		return y;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public Bacteria getType() {
		return type;
	}
	
	public double arrivingTime(Phage obj) {
		return distance(obj)/type.getSpeed();
	}
	
	public double offPower() {
		return type.getStrength()*units/2;
	}
	
	public double defPower(double dt) {
		return type.getDefense()*(units+type.getReproduction()*dt);
	}
	
	public void update(double dt) {
		units+=dt*type.getReproduction();
	}

	public double getUnits() {
		return units;
	}
}
