package neatBacto;

import java.util.StringTokenizer;

import jNeatCommon.EnvConstant;
import jNeatCommon.EnvRoutine;
import jNeatCommon.IOseq;
import jneat.Genome;
import jneat.Network;

public class Model {
	Field field;
	Player player1;
	Player player2;
	Bacteria type1;
	Bacteria type2;
	
	public Model(Player player1,Player player2,Bacteria type1, Bacteria type2) {
		this.player1=player1;
		this.player2=player2;
		this.type1=type1;
		this.type2=type2;
	}
	
	public Model() {
		type1=new Bacteria(80,3,3,3);
		type2=new Bacteria(80,3,3,3);
		player1=new GreedyAI(type1,1);
		player2=new GreedyAI(type2,1);
	}
	
	public double simulateMatch(double dt){
		double score=-4000;
		field=new Field(type1, type2);
		player1.reset();
		player2.reset();
		double elapsedTime=0;
		while(true){
			elapsedTime+=dt;
			field.update(dt);
			
			if(elapsedTime>300){
				score=10000;
				break;
			}
			if(field.isDefeated(type2)){
				score=12000;
				break;
			}
			score=Math.max(score,field.score(type1));
			if(field.isDefeated(type1))break;
			int[] play1=player1.play(field, dt);
			int[] play2=player2.play(field, dt);
			field.makePlay(type1, play1);
			field.makePlay(type2, play2);
		}
		return score;
	}
	
	public double[] fitness(int matches){
		double totalScore=0;
		double victories=0;
		
		for(int i=0;i<matches;i++){
			double score=simulateMatch(0.34);
			if(score>=9999){
				victories+=0.5;
			}
			if(score>=11999){
				victories+=0.5;
			}
			totalScore+=score;
		}
		return new double[]{totalScore/(matches),victories/matches};
	}
	
	
	
	public static void main(String Args[]){
		IOseq file=new IOseq();
		file = new IOseq("data/xwinner14_20");
		file.IOseqOpenR();
		file.IOseqRead();
		Genome genome = new Genome(20, file);
		Network net = genome.genesis(20);
		Bacteria type1=new Bacteria(80,3,3,3);
		Bacteria type2=new Bacteria(80,3,3,3);
		NeatPlayer player1=new NeatPlayer(net,type1,1);
		GreedyAI player2=new GreedyAI(type2,1);
		Model model=new Model(player1,player2,type1,type2);
		double[] res=model.fitness(10000);
		System.out.println(res[1]);
	}
	
}
