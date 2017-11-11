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
	
	public Model(Player player) {
		field=new Field();
		
		
	}
}
