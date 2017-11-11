package neatBacto;

public class Model {
	Field field;
	Player player1;
	Player player2;
	Bacteria type1;
	Bacteria type2;
	
	public Model(Field field,Player player1,Player player2,Bacteria type1, Bacteria type2) {
		this.field=field;
		this.player1=player1;
		this.player2=player2;
		this.type1=type1;
		this.type2=type2;
	}
	
	public Model() {
		field=new Field();
		type1=new Bacteria(80,3,3,3);
		type2=new Bacteria(80,3,3,3);
		player1=new GreedyAI(type1,1);
		player2=new GreedyAI(type2,1);
	}
	
	public double simulateMatch(double dt){
		double score=-4000;
		field.generate1v1Field(type1, type2);
		player1.reset();
		player2.reset();
		double elapsedTime=0;
		while(true){
			elapsedTime+=dt;
			field.update(dt);
			
			if(elapsedTime>300){
				score=0;
				break;
			}
			if(field.isDefeated(type2)){
				score=4000;
				break;
			}
			score=Math.max(score,field.score(type1));
			if(field.isDefeated(type1))break;
			long t1=System.nanoTime();
			int[] play1=player1.play(field, dt);
			int[] play2=player2.play(field, dt);
			long t2=System.nanoTime();
			field.makePlay(type1, play1);
			field.makePlay(type2, play2);
			long t3=System.nanoTime();
			System.out.println(t2-t1);
			System.out.println(t3-t2);
		}
		return score;
	}
	
	public double fitness(int matches){
		double score=0;
		for(int i=0;i<matches;i++){
			score+=simulateMatch(0.2);
		}
		return score/matches;
	}
	
	
	public static void main(String Args[]){
		Model a=new Model();
		System.out.println(a.fitness(100));
	}
	
}
