package markov;

import java.util.ArrayList;

public class ValueClass {
	
	public int state;
	public ArrayList<Double> ValueT;  // ArrayList of values at T moments
	public int action;
	
	
	public ValueClass(int state){
		this.state=state;
		this.ValueT = new ArrayList<Double>();
		//ValueT.add((double)0);   // t=0
	}


}
