package markov;

import java.util.ArrayList;

public class Algorithm {
	
	static double gamma = ValueIteration.gamma;   // gamma close to 1 to have a far sight to the reward 
	static double epsilon = ValueIteration.epsilon;
	static ArrayList<ValueClass> Value = new ArrayList<ValueClass>(); // Values
	static ArrayList<Integer> Action = new ArrayList<Integer>();
	
	public static int get_index_value(int speed, int position){
		return ((speed*ValueIteration.discretisation_position)+position);
	}
	
	public static int get_speed_from_index(int index){
		return index/ValueIteration.discretisation_position;
	}
	
	public static int get_position_from_index(int index){
		return (index- ((index/ValueIteration.discretisation_position) * ValueIteration.discretisation_position));
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
	
	public static double reward(double calculate_position, double calculate_speed){
		double reward;
		if(calculate_position <= ValueIteration.ravine){
			reward = (-1);
		}else if(calculate_position >= ValueIteration.reward_border){
			reward = ValueIteration.limit_speed-Math.abs(calculate_speed);
		}else{
			reward = 0;
		}
		return reward;
	}
	
	public static int value_calculate(int i){ // return the value of action 1 or -1
		double calculate_position = ValueIteration.X_array.get(get_position_from_index(i)) + ValueIteration.speed_array.get(get_speed_from_index(i));
		//Action forward
		double calculate_speed_forward = ValueIteration.speed_array.get(get_speed_from_index(i)) + ValueIteration.power * 1 - ValueIteration.height * Math.cos(ValueIteration.slope * ValueIteration.X_array.get(get_position_from_index(i)));
		int sizef = Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.size();
		double value_forward = reward(calculate_position, calculate_speed_forward) + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.get(sizef-1); 
		//Action backward
		double calculate_speed_backward = ValueIteration.speed_array.get(get_speed_from_index(i)) + ValueIteration.power * (-1) - ValueIteration.height * Math.cos(ValueIteration.slope * ValueIteration.X_array.get(get_position_from_index(i)));
		int sizeb = Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.size();
		double value_backward = reward(calculate_position, calculate_speed_backward) + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.get(sizeb-1); 
		double new_value = Math.max(value_forward,value_backward) ;
		Value.get(i).ValueT.add(new_value);
		if(value_forward >= value_backward) return 1;
		else return -1;
	}
	
	public static void creation_values(){
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			Value.add(new ValueClass(i));
			Value.get(i).ValueT.add((double) 0);
		}
	}
	
	public static void convergence(){
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			value_calculate(i);
			int t = Value.get(i).ValueT.size();
			t--;
			int a=1;
			while(Math.abs(Value.get(i).ValueT.get(t)-Value.get(i).ValueT.get(t-1))>=epsilon*(1-gamma)/(2*gamma)){
				value_calculate(i);
				t++;
			}
			a = value_calculate(i);
			Value.get(i).action = a;
			/*if(a==1){
				System.out.println("komel");
				Value.get(i).setQvalue_forward(Value.get(i).ValueT.get(t));		// Qvalue initialized
				//Value.get(i).setQvalue_backward(Value.get(i).ValueT.get(t));	// just to compare both values and to get the correct max value
				System.out.println("dzhfiudbfh= "+Value.get(i).ValueT.get(t));
				Value.get(i).setOptimalAction();									// optimal action set
			}else{
				Value.get(i).setQvalue_backward(Value.get(i).ValueT.get(t));
				System.out.println("vvvvvvvvv= "+Value.get(i).ValueT.get(t));
				//Value.get(i).setQvalue_forward(Value.get(i).ValueT.get(t));
				Value.get(i).setOptimalAction();
			}*/
		}
	}
	
	public static void set_action(){
		for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			Action.add(Value.get(i).action);
		}
	}
	
	public static void main(String args[]){
		ValueIteration.init(32, 32, -1.2, -1.15, 0.54, 0.6, 0.07, 0.99, 0.01, 0, 0, 0.0025, 3, 0.001);
		creation_values();
		convergence();
		set_action();
		
		/*for(int i = 0; i<ValueIteration.discretisation_position*ValueIteration.discretisation_speed;i++){
			//System.out.println("State" + i + "Action "+ Value.get(i).action);
		}*/
		int t = 0;
		while(ValueIteration.current_position != 31 ){
			if (ValueIteration.X_array.get(ValueIteration.current_position) <= ValueIteration.ravine){
				System.out.println("You're dead game over!!!!!!");
				break;
			}
			ValueIteration.speed_position_update();
			t++;
		}
		System.out.println("Nombre d'itération = "+t);
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