package markov;

import java.util.ArrayList;

public class ValueIteration {

	static double gamma; 
	static double epsilon;
	static int discretisation_position;
	static int discretisation_speed;
	static double height; 
	static double slope;
	static double ravine;
	static double limit_speed;
	static double high_border;
	static double low_border;
	static double reward_border;
	static double frequency_position;
	static double frequency_speed;
	static ArrayList<Double> X_array = new ArrayList<Double>();
	static ArrayList<Double> speed_array = new ArrayList<Double>();
	static ArrayList<Integer> reward = new ArrayList<Integer>();
	static int current_position;
	static int current_speed;
	
	public ValueIteration(double gamma, double epsilon, int discretisation_position, int discretisation_speed, double height, double slope, double ravine, double limit_speed, double high_border, double low_border, double reward_border ){
		this.gamma = gamma;
		this.epsilon = epsilon;
		this.discretisation_position = discretisation_position;
		this.discretisation_speed = discretisation_speed;
		this.height = height;
		this.slope = slope;
		this.ravine = ravine;
		this.limit_speed = limit_speed;
		this.high_border = high_border;
		this.low_border = low_border;
		this.reward_border = reward_border;
	}
	
	public static void set_frequency_position(double discretisation_position, double low_border, double high_border){
		frequency_position = (( Math.abs(high_border) + Math.abs(low_border)) / discretisation_position);
		System.out.println("Valeur du pas de la position = "+frequency_position);
	}
	
	public static void set_frequency_speed(double discretisation_speed, double limit_speed){
		frequency_speed = ( ( 2 * Math.abs(limit_speed) ) / discretisation_speed ); 
		System.out.println("Valeur du pas de la vitesse = "+frequency_speed);
	}
	
	public static void set_X(int discretisation_position, double low_border){
		for(int i = 0 ; i < discretisation_position; i++){
			X_array.add(low_border+ i * frequency_position);
			System.out.println("X = "+i+", et pour valeur = "+X_array.get(i));
		}
	}
	
	public static void set_speed(int discretisation_speed, double limit_speed){
		for(int i = 0 ; i < discretisation_speed; i++){
			speed_array.add( (-1) * limit_speed + i * frequency_speed);
			System.out.println("Vitesse = "+i+", et pour valeur = "+speed_array.get(i));
		}
	}
	
	public static void set_reward_init(int discretisation_position, double ravine){
		for(int i = 0; i < discretisation_position; i++){
			if(X_array.get(i) <= ravine){
				reward.add(-1);
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}else{
				reward.add(0);
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}
		}
	}
	
	public static void init(int discretisation_position, int discretisation_speed, double low_border, double ravine, double high_border, double limit_speed, double gamma, double epsilon, int current_position, int current_speed){
		ValueIteration.gamma = gamma;
		ValueIteration.epsilon = epsilon;
		ValueIteration.discretisation_position = discretisation_position;
		ValueIteration.discretisation_speed = discretisation_speed;
		ValueIteration.ravine = ravine;
		ValueIteration.limit_speed = limit_speed;
		ValueIteration.high_border = high_border;
		ValueIteration.low_border = low_border;
		ValueIteration.current_position = current_position;
		ValueIteration.current_speed = current_speed;
		System.out.println("Gamma = "+ValueIteration.gamma);
		System.out.println("Epsilon = "+ValueIteration.epsilon);
		set_frequency_position(discretisation_position, low_border, high_border); 
		set_frequency_speed(discretisation_speed, limit_speed);
		set_X(discretisation_position, low_border);
		set_speed(discretisation_speed, limit_speed);
		set_reward_init(discretisation_position, ravine);
	}
	
	public static void set_current_position(double position){
		if(position >= X_array.get(discretisation_position-1)){
			ValueIteration.current_position = discretisation_position-1;
		}else if(position <= X_array.get(0)){
			ValueIteration.current_position = 0;
		}else{
			int i = 0;
			while (position >= X_array.get(i)){
				i++;
			}
			i--;
			if( (Math.abs(Math.abs(position)-Math.abs(X_array.get(i)))) < (Math.abs(Math.abs(position)-Math.abs(X_array.get(i+1)))) ){
				ValueIteration.current_position = i;
			}else{
				ValueIteration.current_position = i+1;
			}
		}
		System.out.println(ValueIteration.current_position);
	}
	
	public static void main(String[] args) {
		init(32, 32, -1.2, -1.15, 0.6, 0.07, 0.99, 0.01, 0, 0);
		set_X(32, -1.2);
		set_current_position(0.4874999999999998);
		//set_frequency_position(32, -1.2, 0.6);
		//set_frequency_speed(32, 0.07);
		//set_X(32, -1.2);
		//set_speed(32, 0.07);
		//set_reward_init(32, -1.15);
	}
}
