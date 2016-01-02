package markov;

import java.util.ArrayList;

public class ValueClass {
	
	public int state;
	public ArrayList<Double> ValueT;  // ArrayList of values at T moments
	public double Qvalue_forward;
	public double Qvalue_backward;
	
	
	public ValueClass(int state){
		this.state=state;
		this.ValueT = new ArrayList<Double>();
		//ValueT.add((double)0);   // t=0
		Qvalue_forward = 0;
		Qvalue_backward = 0;
	}


	public void setQvalue_forward(double value) {
		this.Qvalue_forward=value;
		
	}
	
	public void setQvalue_backward(double value) {
		this.Qvalue_backward=value;
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
