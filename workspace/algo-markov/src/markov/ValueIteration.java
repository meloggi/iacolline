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
	static double speed;
	static double high_border;
	static double low_border;
	static double reward_border;
	static double frequency_position;
	static double frequency_speed;
	static ArrayList<Double> X_array = new ArrayList<Double>();
	static ArrayList<Double> speed_array = new ArrayList<Double>();
	static ArrayList<Integer> reward = new ArrayList<Integer>();
	
	public ValueIteration(double gamma, double epsilon, int discretisation_position, int discretisation_speed, double height, double slope, double ravine, double speed, double high_border, double low_border, double reward_border ){
		this.gamma = gamma;
		this.epsilon = epsilon;
		this.discretisation_position = discretisation_position;
		this.discretisation_speed = discretisation_speed;
		this.height = height;
		this.slope = slope;
		this.ravine = ravine;
		this.speed = speed;
		this.high_border = high_border;
		this.low_border = low_border;
		this.reward_border = reward_border;
	}
	
	public static void set_frequency_position(double discretisation_position, double low_border, double high_border){
		frequency_position = (( Math.abs(high_border) + Math.abs(low_border)) / discretisation_position);
		System.out.println("Valeur du pas de la position = "+frequency_position);
	}
	
	public static void set_frequency_speed(double discretisation_speed, double speed){
		frequency_speed = ( ( 2 * Math.abs(speed) ) / discretisation_speed ); 
		System.out.println("Valeur du pas de la vitesse = "+frequency_speed);
	}
	
	public static void set_X(int discretisation_position, double low_border){
		for(int i = 0 ; i < discretisation_position; i++){
			X_array.add(low_border+ i * frequency_position);
			System.out.println("X = "+i+", et pour valeur = "+X_array.get(i));
		}
	}
	
	public static void set_speed(int discretisation_speed, double speed){
		for(int i = 0 ; i < discretisation_speed; i++){
			speed_array.add( (-1) * speed + i * frequency_speed);
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
	
	public static void main(String[] args) {
		set_frequency_position(32, -1.2, 0.6);
		set_frequency_speed(32, 0.07);
		set_X(32, -1.2);
		set_speed(32, 0.07);
		set_reward_init(32, -1.15);
	}
}
