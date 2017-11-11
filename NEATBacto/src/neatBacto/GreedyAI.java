package neatBacto;

public class GreedyAI implements Player {
	
	Bacteria type;
	
	public GreedyAI(Bacteria type) {
		this.type=type;
	}
	
	@Override
	public int[] play(Field field) {
		int[] ans;
		ans=simplePlay(field);
		if(ans[0]!=1 || ans[10]!=1) {
			return ans;
		}
		ans = complexPlay(field);
		return ans;
	}
	
	private int[] complexPlay(Field field) {
		int[] ans=new int[20];
		int obj=0;
		double min=1000;
		for(int i=0;i<10;i++) {
			Phage p=field.getPhages().get(i);
			if(p.getUnits()<min&&!type.equals(p.getType())) {
				obj=i;
				min=p.getUnits();
			}
		}
		double defPow=0;
		double offPow=0;
		do {
			double max=0;
			int att=10;
			for(int i=0;i<10;i++) {
				Phage p=field.getPhages().get(i);
				if(type.equals(p.getType())&&p.getUnits()>max&&ans[i]==0) {
					max=p.getUnits();
					att=i;
				}
			}
			if(att==10)break;
			ans[att]=1;
			offPow+=field.getPhages().get(att).offPower();
			Phage p=field.getPhages().get(obj);
			double newDef=p.defPower(field.getPhages().get(att).arrivingTime(p));
			defPow=Math.max(defPow,newDef);
		}while(defPow>=offPow);
		return ans;
	}

	private int[] simplePlay(Field field) {
		double minTime=1000000;
		int att=0;
		int obj=0;
		for(int i=0;i<10;i++){
			Phage pAtt=field.getPhages().get(i);
			if (type.equals(pAtt.getType())){
				for(int j=0;j<10;j++) {
					Phage pObj=field.getPhages().get(j);
					if (!type.equals(pObj.getType())){
						if(pAtt.offPower()>pObj.defPower(pAtt.arrivingTime(pObj))) {
							if(pAtt.arrivingTime(pObj)<minTime) {
								minTime=pAtt.arrivingTime(pObj);
								att=i;
								obj=j;
										
							}
						}
					}
				}
			}
		}
		int[] ans=new int[20];
		ans[att]=1;
		ans[obj+10]=1;
		return ans;
	}
	
}
