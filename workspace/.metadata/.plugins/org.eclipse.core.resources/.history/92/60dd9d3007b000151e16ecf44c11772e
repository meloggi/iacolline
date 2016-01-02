package markov;

import java.util.ArrayList;

public class ValueClass {
	
	public int state;
	public ArrayList<Double> ValueT;  // ArrayList of values at T moments
	public double Qvalue1;
	public double QvalueMinus1;
	
	
	public ValueClass(int state){
		this.state=state;
		this.ValueT = new ArrayList<Double>();
		ValueT.add((double)0);   // t=0
		Qvalue1=0;
		Qvalue1=0;
	}


	public void setQvalue1(double value) {
		this.Qvalue1=value;
		
	}
	
	public void setQvalueMinus1(double value) {
		this.QvalueMinus1=value;
		
	}
	
/*	public ValueClass(int state, int t, double Value){
		this.state=state;
		this.ValueT = new ArrayList<Double>();
		int i=0;
		while(i < t){
			ValueT.add((double)0);
			i++;
		}
		ValueT.add(Value);
	}*/

}
