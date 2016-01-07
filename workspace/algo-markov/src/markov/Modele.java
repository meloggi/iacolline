package markov;

import java.util.ArrayList;

public class Modele {

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
	static ArrayList<Double> reward = new ArrayList<Double>();
	static ArrayList<Double> road = new ArrayList<Double>();
	static ArrayList<Double> hole = new ArrayList<Double>();
	static int current_position;
	static int current_speed;
	static double current_altitude;
	static double current_hole;
	static double power;
	static int inc = 0;
	static int inc2 = 0;
	static double current_road;
	
/*	public Modele(double gamma, double epsilon, int discretisation_position, int discretisation_speed, double height, double slope, double ravine, double limit_speed, double high_border, double low_border, double reward_border ){
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
	
	*/
	////////////////////////////////////////////////////////////////////////////////////
	////////																	////////
	////////							Initialisation							////////
	////////																	////////
	////////////////////////////////////////////////////////////////////////////////////
	
	public static void set_frequency_position(double discretisation_position, double low_border, double high_border){
		frequency_position = (( Math.abs(high_border) + Math.abs(low_border)) / (discretisation_position-1));
		System.out.println("Valeur du pas de la position = "+frequency_position);
	}
	
	public static void set_frequency_speed(double discretisation_speed, double limit_speed){
		frequency_speed = ( ( 2 * Math.abs(limit_speed) ) / (discretisation_speed-1) ); 
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
	
	public static void set_reward_init(int discretisation_position, double limit_speed, double ravine, int current_speed){
		for(int i = 0; i < discretisation_position; i++){
			if(X_array.get(i) <= ravine){
				reward.add((double) -1);
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}else if(X_array.get(i) >= reward_border){
				reward.add(limit_speed-Math.abs(speed_array.get(current_speed)));
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}else{
				reward.add((double) 0);
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}
		}
	}
	
	public static void init(int discretisation_position, int discretisation_speed, double low_border, double ravine, double reward_border,double high_border, double limit_speed, double gamma, double epsilon, double current_position_value, double current_speed_value, double height, double slope, double power){
		
		X_array.clear();
		speed_array.clear();
		reward.clear();
		
		
		Modele.gamma = gamma;
		Modele.epsilon = epsilon;
		Modele.discretisation_position = discretisation_position;
		Modele.discretisation_speed = discretisation_speed;
		Modele.ravine = ravine;
		Modele.limit_speed = limit_speed;
		Modele.high_border = high_border;
		Modele.low_border = low_border;
		Modele.height = height; 
		Modele.slope = slope;
		Modele.power = power;
		Modele.reward_border = reward_border;
		System.out.println("Gamma = "+Modele.gamma);
		System.out.println("Epsilon = "+Modele.epsilon);
		set_frequency_position(discretisation_position, low_border, high_border); 
		set_frequency_speed(discretisation_speed, limit_speed);
		set_X(discretisation_position, low_border);
		set_speed(discretisation_speed, limit_speed);
		set_current_position(current_position_value);
		set_current_speed(current_speed_value);
		set_reward_init(discretisation_position, limit_speed, ravine, current_speed);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
	////////																	////////
	////////							Current									////////
	////////																	////////
	////////////////////////////////////////////////////////////////////////////////////
	
	public static void set_current_position(double position){
		if(position >= X_array.get(discretisation_position-1)){
			Modele.current_position = discretisation_position-1;
		}else if(position <= X_array.get(0)){
			Modele.current_position = 0;
		}else{
			int i = 0;
			while (position >= X_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(position)-Math.abs(X_array.get(i-1)))) < (Math.abs(Math.abs(position)-Math.abs(X_array.get(i)))) ){
				Modele.current_position = i-1;
			}else{
				Modele.current_position = i;
			}
		}
	}
	
	public static void set_current_speed(double speed){
		if(speed >= speed_array.get(discretisation_speed-1)){
			Modele.current_speed = discretisation_speed-1;
		}else if(speed <= speed_array.get(0)){
			Modele.current_speed = 0;
		}else{
			int i = 0;
			while (speed >= speed_array.get(i)){
				i++;
			}
			if( (Math.abs(Math.abs(speed)-Math.abs(speed_array.get(i-1)))) < (Math.abs(Math.abs(speed)-Math.abs(speed_array.get(i)))) ){
				Modele.current_speed = i-1;
			}else{
				Modele.current_speed = i;
			}
		}
	}
	
	public static void set_current_reward(){
		for(int i = 0; i < discretisation_position; i++){
			if(X_array.get(i) >= reward_border){
				reward.set(i, limit_speed-Math.abs(speed_array.get(current_speed)));
				System.out.println("Recompense pour "+i+", a pour valeur = "+reward.get(i));
			}
		}
	}
	
	public static void set_current_altitude(double position){
		Modele.current_altitude = (Modele.height/Modele.slope)*Math.sin(Modele.slope * position);
	}
	
	public static void speed_position_update(){
		//System.out.println("Position précédente: "+current_position);
		//System.out.println("Vitesse précédente: "+current_speed);
		double calculate_position = X_array.get(current_position) + speed_array.get(current_speed);
		double calculate_speed = speed_array.get(current_speed) + power * Algorithm.Action.get(Algorithm.get_index_value(current_speed, current_position)) - height * Math.cos(slope * X_array.get(current_position));
		set_current_speed(calculate_speed);
		set_current_position(calculate_position);
		set_current_altitude(calculate_position);
		//System.out.println("Position actualisée: "+current_position);
		//System.out.println("Vitesse actualisée: "+current_speed);
	}
	
	public static void set_road(){
		while ((inc * frequency_position/4 + Modele.ravine) <= (Modele.high_border)){
			Modele.current_road = (Modele.height/Modele.slope)*Math.sin(Modele.slope * (inc * frequency_position/4 + Modele.ravine));
			Modele.road.add(current_road);
			inc ++;
		}
	}
	
	public static void set_hole(){
		while ((inc2 * frequency_position/4 + Modele.low_border) <= (Modele.ravine)){
			Modele.current_hole = (Modele.height/Modele.slope)*Math.sin(Modele.slope * (inc2 * frequency_position/4 + Modele.low_border));
			Modele.hole.add(current_hole);
			inc2 ++;
		}
	}
}
