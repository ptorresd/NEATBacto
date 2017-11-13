package neatBacto;

import jNeatCommon.EnvConstant;
import jneat.*;


public class NeatPlayer implements Player {
	
	Network net;
	Bacteria type;
	double frecuency;
	double timer;
	
	public NeatPlayer(Network net,Bacteria type,double frecuency){
		this.net=net;
		this.type=type;
		this.frecuency=frecuency;
		timer=0;
	}
	
	@Override
	public int[] play(Field field, double dt) {
		timer+=dt*frecuency;
		int[] output=new int[20];
		if(timer<1)return output;
		timer--;
		double[] input=field.getInput(type);
		net.load_sensors(input);
		boolean success=false;
		
		
		if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL){
			for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++){
				success = net.activate();
			}
		}
		else{   	            
			// first activation from sensor to next layer....
			success = net.activate();
			// next activation while last level is reached !
			// use depth to ensure relaxation
			for (int relax = 0; relax <= net.max_depth(); relax++){
				success = net.activate();
			}
		}		
		if(success){
			double max=0;
			int pos=0;
			for( int j=0; j < 10; j++){
				double val=net.getOutputs().elementAt(j).getActivation();
				if (val>max){
					max=val;
					pos=j;
				}
				output[j] = (int) Math.round(val);
			}
			output[pos]=1;
			max=0;
			pos=0;
			for( int j=10; j < 20; j++){
				double val=net.getOutputs().elementAt(j).getActivation();
				if (val>max){
					max=val;
					pos=j;
				}
				output[j] = 0;
			}
			output[pos]=1;
		}
		return output;
	}

	@Override
	public void reset() {
		net.flush();
	}

}
