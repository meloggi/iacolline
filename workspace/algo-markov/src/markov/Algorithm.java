package markov;

import java.util.ArrayList;

public class Algorithm {
	
	static double gamma = Modele.gamma;   // gamma close to 1 to have a far sight to the reward 
	static double epsilon = Modele.epsilon;
	static ArrayList<ValueClass> Value = new ArrayList<ValueClass>(); // Values
	static ArrayList<Integer> Action = new ArrayList<Integer>();
	
	public static int get_index_value(int speed, int position){
		return ((speed*Modele.discretisation_position)+position);
	}
	
	public static int get_speed_from_index(int index){
		return index/Modele.discretisation_position;
	}
	
	public static int get_position_from_index(int index){
		return (index- ((index/Modele.discretisation_position) * Modele.discretisation_position));
	}
	
	public static int get_array_position(double position){
		int index;
		if(position >= Modele.X_array.get(Modele.discretisation_position-1)){
			index = Modele.discretisation_position-1;
		}else if(position <= Modele.X_array.get(0)){
			index = 0;
		}else{
			int i = 0;
			while (position >= Modele.X_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(position)-Math.abs(Modele.X_array.get(i-1)))) < (Math.abs(Math.abs(position)-Math.abs(Modele.X_array.get(i)))) ){
				index = i-1;
			}else{
				index = i;
			}
		}
		return index;
	}
	
	public static int get_array_speed(double speed){
		int index;
		if(speed >= Modele.speed_array.get(Modele.discretisation_speed-1)){
			index = Modele.discretisation_speed-1;
		}else if(speed <= Modele.speed_array.get(0)){
			index = 0;
		}else{
			int i = 0;
			while (speed >= Modele.speed_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(speed)-Math.abs(Modele.speed_array.get(i-1)))) < (Math.abs(Math.abs(speed)-Math.abs(Modele.speed_array.get(i)))) ){
				index = i-1;
			}else{
				index = i;
			}
		}
		return index;
	}
	
	public static double reward(double calculate_position, double calculate_speed){
		double reward;
		if(calculate_position <= Modele.ravine){
			reward = (-1);
		}else if(calculate_position >= Modele.reward_border){
			reward = Modele.limit_speed-Math.abs(calculate_speed);
		}else{
			reward = 0;
		}
		return reward;
	}
	
	public static int value_calculate(int i){ // return the value of action 1 or -1
		double calculate_position = Modele.X_array.get(get_position_from_index(i)) + Modele.speed_array.get(get_speed_from_index(i));
		//Action forward
		double calculate_speed_forward = Modele.speed_array.get(get_speed_from_index(i)) + Modele.power * 1 - Modele.height * Math.cos(Modele.slope * Modele.X_array.get(get_position_from_index(i)));
		int sizef = Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.size();
		double value_forward = reward(calculate_position, calculate_speed_forward) + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_forward),get_array_position(calculate_position))).ValueT.get(sizef-1); 
		//Action backward
		double calculate_speed_backward = Modele.speed_array.get(get_speed_from_index(i)) + Modele.power * (-1) - Modele.height * Math.cos(Modele.slope * Modele.X_array.get(get_position_from_index(i)));
		int sizeb = Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.size();
		double value_backward = reward(calculate_position, calculate_speed_backward) + gamma * Value.get(get_index_value(get_array_speed(calculate_speed_backward),get_array_position(calculate_position))).ValueT.get(sizeb-1); 
		double new_value = Math.max(value_forward,value_backward) ;
		Value.get(i).ValueT.add(new_value);
		if(value_forward >= value_backward) return 1;
		else return -1;
	}
	
	public static void creation_values(){
		for(int i = 0; i<Modele.discretisation_position*Modele.discretisation_speed;i++){
			Value.add(new ValueClass(i));
			Value.get(i).ValueT.add((double) 0);
		}
	}
	
	public static void convergence(){
		for(int i = 0; i<Modele.discretisation_position*Modele.discretisation_speed;i++){
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
		for(int i = 0; i<Modele.discretisation_position*Modele.discretisation_speed;i++){
			Action.add(Value.get(i).action);
		}
	}
	
	public static void main(String args[]){
/*		Modele.init(100, 100, -1.2, -1.15, 0.54, 0.6, 0.07, 0.99, 0.01, 0, 0, 0.0025, 3, 0.001);
		creation_values();
		convergence();
		set_action();*/
		
		
		Interface window = new Interface();
		
	/*	int t = 0;
		while(Modele.current_position != 99 ){
			if (Modele.X_array.get(Modele.current_position) <= Modele.ravine){
				System.out.println("You're dead game over!!!!!!");
				break;
			}
			Modele.speed_position_update();
			t++;
		}
		System.out.println("Nombre d'itération = "+t);
		*/
		

	}

}