package markov;

import java.util.ArrayList;

public class Algorithm {
	
	static double gamma = ValueIteration.gamma;   // gamma close to 1 to have a far sight to the reward 
	static double epsilon = ValueIteration.epsilon;
	static ArrayList<ValueClass> Value = new ArrayList<ValueClass>(); // Values
	
	public static int get_index_value(int speed, int position){
		return ((speed*ValueIteration.discretisation_position)+position);
	}
	
	public static int get_speed_from_index(int index){
		return index/ValueIteration.discretisation_position;
	}
	
	public static int get_position_from_index(int index){
		return (index-(index*ValueIteration.discretisation_position));
	}
	
	public static int get_array_position(double position){
		int index;
		if(position >= ValueIteration.X_array.get(ValueIteration.discretisation_position-1)){
			index = ValueIteration.discretisation_position-1;
		}else if(position <= ValueIteration.X_array.get(0)){
			index = 0;
		}else{
			int i = 0;
			while (position >= ValueIteration.X_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(position)-Math.abs(ValueIteration.X_array.get(i-1)))) < (Math.abs(Math.abs(position)-Math.abs(ValueIteration.X_array.get(i)))) ){
				index = i-1;
			}else{
				index = i;
			}
		}
		return index;
	}
	
	public static int get_array_speed(double speed){
		int index;
		if(speed >= ValueIteration.speed_array.get(ValueIteration.discretisation_speed-1)){
			index = ValueIteration.discretisation_speed-1;
		}else if(speed <= ValueIteration.speed_array.get(0)){
			index = 0;
		}else{
			int i = 0;
			while (speed >= ValueIteration.speed_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(speed)-Math.abs(ValueIteration.speed_array.get(i-1)))) < (Math.abs(Math.abs(speed)-Math.abs(ValueIteration.speed_array.get(i)))) ){
				index = i-1;
			}else{
				index = i;
			}
		}
		return index;
	}
	
	public static void value_calculate(int i){
		double calculate_position = ValueIteration.X_array.get(get_position_from_index(i)) + ValueIteration.speed_array.get(get_speed_from_index(i));
		ValueIteration.set_current_reward();	//Ici à modifier pas bon car ici on n'affecte pas les currents
		double reward = ValueIteration.reward.get(get_array_position(calculate_position));
		//Action forward
		double calculate_speed_forward = ValueIteration.speed_array.get(get_speed_from_index(i)) + ValueIteration.power * 1 - ValueIteration.height * Math.cos(ValueIteration.slope * ValueIteration.X_array.get(get_position_from_index(i)));
		int sizef = Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.size();
		double value_forward = reward + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.get(sizef-1); 
		//Action backward
		double calculate_speed_backward = ValueIteration.speed_array.get(get_speed_from_index(i)) + ValueIteration.power * (-1) - ValueIteration.height * Math.cos(ValueIteration.slope * ValueIteration.X_array.get(get_position_from_index(i)));
		int sizeb = Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.size();
		double value_backward = reward + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.get(sizeb-1); 
		
		double new_value = Math.max(value_forward,value_backward) ;
		Value.get(i).ValueT.add(new_value);
	}
	
	public static void creation_values(){
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			Value.add(new ValueClass(i));
			Value.get(i).ValueT.clear();
			Value.get(i).ValueT.add((double) 0);
		}
	}
	
	public static void convergence(){
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			int t = Value.get(i).ValueT.size();
			t--;
			while(Math.abs(Value.get(i).ValueT.get(t+1)-Value.get(i).ValueT.get(t))>=epsilon*(1-gamma)/(2*gamma)){
				value_calculate(i);
				t++;
			}
		}
	}
	
	public static void main(String args[]){
		creation_values();
		convergence();
		
		
		/*
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
			
			Value.get(i).setQvalue_forward(reward + gamma*Value.get(i).ValueT.get(t));		// Qvalue moving forward
			Value.get(i).setQvalue_backward(reward + gamma*Value.get(i).ValueT.get(t)); // Qvalue moving backward
			
			// missing optimal policy
		}*/
		
	}

}
