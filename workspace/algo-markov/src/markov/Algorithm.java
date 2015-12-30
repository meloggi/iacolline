package markov;

import java.util.ArrayList;

public class Algorithm {
	
	
	
	public static void main(String args[]){
		
		double gamma = 0.99;   // gamma close to 1 to have a far sight to the reward 
		double epsilon = 0.03;
		double reward = 0;
		ArrayList<ValueClass> Value = new ArrayList<ValueClass>(); // Values
		
		
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			// creation of values
			Value.add(new ValueClass(i)); // each state shall have its number
		}
		
		//loop on every state
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			int t = 0;
			
			Value.get(i).ValueT.add(Math.max(0.0, 0.0)); // first value for moving forward, second for moving backward
			
			//loop of stage 3, page37, MDP course
			while(Math.abs(Value.get(i).ValueT.get(t+1)-Value.get(i).ValueT.get(t))>=epsilon*(1-gamma)/(2*gamma)){  
				t++;
				Value.get(i).ValueT.add(Math.max(0.0, 0.0));  // new value for t to satisfy the ValueIteration inequality
				//index should be correct (no unbound exception thrown)
			}
			
			Value.get(i).setQvalue1(reward + gamma*Value.get(i).ValueT.get(t));		// Qvalue moving forward
			Value.get(i).setQvalueMinus1(reward + gamma*Value.get(i).ValueT.get(t)); // Qvalue moving backward
			
			// missing optimal policy
		}
		
	}

}
