package neatBacto;

import java.util.ArrayList;

public class Field {
	
	ArrayList<Phage> phages;
	ArrayList<Attack> attacks;
	ArrayList<Attack> removedAttacks;
	Bacteria player1;
	Bacteria player2;
	double bonus;
	Field(){
		phages=new ArrayList<>();
		attacks=new ArrayList<>();
		removedAttacks=new ArrayList<>();
	}
	
	public Field(Bacteria player1,Bacteria player2) {
		this.player1=player1;
		this.player2=player2;
		bonus=0;
		phages=new ArrayList<>();
		attacks=new ArrayList<>();
		removedAttacks=new ArrayList<>();
		Phage p1=new Phage(this,player1,40,40,40,20);
		Phage p2=new Phage(this,player2,40,600,440,20);
		phages.add(p1);
		phages.add(p2);
		while(phages.size()<10) {
			Phage neutral=new Phage(this);
			boolean collides=false;
			for(Phage p:phages) {
				if (neutral.collides(p)) {
					collides=true;
					break;
				}
			}
			if(!collides)phages.add(neutral);
		}
	}
	
	void makePlay(Bacteria player,int[] move ) {
		Phage obj=phages.get(0);
		for(int i=10;i<20;i++) {
			if(move[i]==1) {
				obj=phages.get(i-10);
			}
		}
		for(int i=0;i<10;i++) {
			if(move[i]==1&&player.equals(phages.get(i).getType())) {
				phages.get(i).attackPhage(obj);
			}
		}
	}
	
	void addAttack(Attack att) {
		attacks.add(att);
	}
	
	void update(double dt){
		for(Phage p:phages) {
			p.update(dt);
		}
		for(Attack a:attacks) {
			a.update(dt);
		}
		attacks.removeAll(removedAttacks);
	}

	public void removeAttack(Attack attack) {
		for(Attack a:removedAttacks){
		}
		removedAttacks.add(attack);
	}

	public ArrayList<Phage> getPhages() {
		return phages;
	}
	
	public boolean isDefeated(Bacteria type){
		boolean ded=true;
		for(Phage p:phages){
			if(p.getType().equals(type)){
				ded=false;
				break;
			}
		}
		return ded;
	}
	
	double score(Bacteria player){
		double ans=0;
		for(Phage p:phages){
			if(p.getType().equals(player))ans+=500+p.getUnits();//50+p.getUnits();
			//else ans-=50+p.getUnits();
		}
		return ans+bonus;
		
	}
	
	void updateThreat(){
		for(Phage p:phages){
			p.setThreat(0);
		}
		for(Attack a: attacks){
			Phage p=a.getObjective();
			if(p.getType().equals(a.getType())){
				p.setThreat(p.getThreat()+a.getUnits());
			}
			else{
				p.setThreat(p.getThreat()-a.getUnits());
			}
		}
	}
	
	public double[] getInput(Bacteria type) {
		double[] input=new double[51];
		int pos=0;
		for(Phage p:phages){
			input[pos]=p.getType().equals(type)?1:0;
			input[pos+1]=p.getX()/640;
			input[pos+2]=p.getY()/480;
			input[pos+3]=Math.min(p.getUnits()/100,1);
			double threat=p.getThreat()-(p.getUnits()+p.getType().getReproduction()*3);
			input[pos+4]=Math.min(1, Math.max(0, threat));
			pos+=5;
		}
		input[50]=1;
		return input;
	}

	void applyBonus(Bacteria type1, Bacteria type2) {
		if(player1.equals(type1) && player2.equals(type2)){
			bonus+=250;
		}
	}

}
