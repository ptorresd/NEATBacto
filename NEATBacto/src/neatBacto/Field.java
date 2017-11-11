package neatBacto;

import java.util.ArrayList;

public class Field {
	
	ArrayList<Phage> phages;
	ArrayList<Attack> attacks;
	
	Field(){
		phages=new ArrayList<>();
		attacks=new ArrayList<>();
	}
	
	void generate1v1Field(Bacteria player1,Bacteria player2) {
		Phage p1=new Phage(this,player1,40,40,40,20);
		phages.add(p1);
		Phage p2=new Phage(this,player2,40,600,440,20);
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
	}

	public void removeAttack(Attack attack) {
		attacks.remove(attack);
	}

	public ArrayList<Phage> getPhages() {
		return phages;
	}

}
