package markov;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.jfree.data.xy.XYSeries;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFormattedTextField;

import java.awt.Label;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interface {

	public JFrame frame;
	public JPanel panel_modele;
	public JLabel lblNewLabel;
	public JFormattedTextField FieldBH;
	public JLabel lblBordureBas;
	public JFormattedTextField FieldBB;
	public JLabel lblRavine;
	public JFormattedTextField FieldRavine;
	public JLabel lblRewardBorder;
	public JFormattedTextField FieldRewardB;
	public JLabel lblLimiteSpeed;
	public JFormattedTextField FieldLimitSpeed;
	public JLabel lblPuissance;
	public JFormattedTextField FieldPower;
	public JLabel lblHauteurColine;
	public JFormattedTextField FieldHauteurColine;
	public JLabel label;
	public JFormattedTextField FieldPente;
	public JLabel lblDiscretisationPosition;
	public JFormattedTextField FieldDiscPosition;
	public JLabel label_1;
	public JFormattedTextField FieldDiscVitesse;
	public JLabel lblVitesseActuelle;
	public JFormattedTextField FieldVitesse;
	public JLabel lblPositionActuelle;
	public JFormattedTextField FieldPosition;
	public JPanel panel_graph;
	public JLabel Car;
	public JLabel Landscape;
	public JLabel Parameters;
	public JLabel Gamma;
	public JFormattedTextField FieldGamma;
	public JLabel Epsilon;
	public JFormattedTextField FieldEpsilon;

	public Graphique graph;
	public JButton btnLancerSimulation;

	public double[] data;
	XYSeries series1 = new XYSeries("Simulation");
	XYSeries seriesVitesse = new XYSeries("Vitesse");
	XYSeries seriesPosition = new XYSeries("Position");
	XYSeries seriesAlt = new XYSeries("Altitude");
	XYSeries serieRoute = new XYSeries("Route");
	

	XYSeries Saveseries1 = new XYSeries("Simulation");
	XYSeries SaveseriesVitesse = new XYSeries("Vitesse");
	XYSeries SaveseriesPosition = new XYSeries("Position");
	XYSeries SaveseriesAlt = new XYSeries("Altitude");
	XYSeries SaveserieVoiture = new XYSeries("Voiture");


	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_simulation;
	private JPanel panel_Voiture;
	private JButton btnSuivant;
	private JButton btnPrecedent;
	private JSlider slider;
	private JLabel lblValeurSlide;

	public int compteur=1;
	public int compteurMax=100;

	/**
	 * Launch the application.
	 */
	/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}*/

	/**
	 * Create the application.
	 */
	public Interface() {

		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("IA project : Hill climbing car");
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[970.00px,grow]", "[643px,grow]"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");

		panel_simulation = new JPanel();
		tabbedPane.addTab("Model data", null, panel_simulation, null);
		panel_simulation.setLayout(new MigLayout("", "[332.00px,left][176.00,grow]", "[550px,grow][1px]"));

		panel_modele = new JPanel();
		panel_simulation.add(panel_modele, "cell 0 0,alignx left,aligny top");
		panel_modele.setLayout(new MigLayout("", "[left][149.00,grow]", "[][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][][][]"));

		Car = new JLabel("----------------Car Parameters ------------- ");
		panel_modele.add(Car, "cell 0 0,alignx left,aligny top");
		
		Landscape = new JLabel("---------Landscape Parameters---------");
		panel_modele.add(Landscape, "cell 0 5,alignx left");
		
		Parameters = new JLabel("-------- General Parameters--------------");
		panel_modele.add(Parameters, "cell 0 12,alignx left");
		
		
		lblNewLabel = new JLabel("Right border");
		panel_modele.add(lblNewLabel, "cell 0 6,alignx left");
		
		FieldBH = new JFormattedTextField();
		FieldBH.setText("0.6");
		panel_modele.add(FieldBH, "cell 1 6,growx");
		
		lblBordureBas = new JLabel("Left border");
		panel_modele.add(lblBordureBas, "cell 0 7,alignx left");
		
		FieldBB = new JFormattedTextField();
		FieldBB.setText("-1.2");
		panel_modele.add(FieldBB, "cell 1 7,growx");
		
		lblRavine = new JLabel("Ravine");
		panel_modele.add(lblRavine, "cell 0 8,alignx left");
		
		FieldRavine = new JFormattedTextField();
		FieldRavine.setText("-1.15");
		panel_modele.add(FieldRavine, "cell 1 8,growx");
		
		lblRewardBorder = new JLabel("Reward border");
		panel_modele.add(lblRewardBorder, "cell 0 9,alignx left");
		
		FieldRewardB = new JFormattedTextField();
		FieldRewardB.setText("0.55");
		panel_modele.add(FieldRewardB, "cell 1 9,growx");
		
		lblLimiteSpeed = new JLabel("Limit speed");
		panel_modele.add(lblLimiteSpeed, "cell 0 4,alignx left");
		
		FieldLimitSpeed = new JFormattedTextField();
		FieldLimitSpeed.setText("0.07");
		panel_modele.add(FieldLimitSpeed, "cell 1 4,growx");
		
		lblPuissance = new JLabel("Power");
		panel_modele.add(lblPuissance, "cell 0 3,alignx left");
		
		FieldPower = new JFormattedTextField();
		FieldPower.setText("0.001");
		panel_modele.add(FieldPower, "cell 1 3,growx");
		
		lblHauteurColine = new JLabel("Hill height");
		panel_modele.add(lblHauteurColine, "cell 0 10,alignx left");
		
		FieldHauteurColine = new JFormattedTextField();
		FieldHauteurColine.setText("0.0025");
		panel_modele.add(FieldHauteurColine, "cell 1 10,growx");
		
		label = new JLabel("Slope");
		panel_modele.add(label, "cell 0 11,alignx left");
		
		FieldPente = new JFormattedTextField();
		FieldPente.setText("3"); 
		panel_modele.add(FieldPente, "cell 1 11,growx");
		
		lblDiscretisationPosition = new JLabel("Position Discretisation");
		panel_modele.add(lblDiscretisationPosition, "cell 0 13,alignx left");
		
		FieldDiscPosition = new JFormattedTextField();
		FieldDiscPosition.setText("100");
		panel_modele.add(FieldDiscPosition, "cell 1 13,growx");
		
		label_1 = new JLabel("Speed Discretisation");
		panel_modele.add(label_1, "cell 0 14,alignx left");
		
		FieldDiscVitesse = new JFormattedTextField();
		FieldDiscVitesse.setText("100");
		panel_modele.add(FieldDiscVitesse, "cell 1 14,growx");
		
		lblVitesseActuelle = new JLabel("Initial Speed");
		panel_modele.add(lblVitesseActuelle, "cell 0 2,alignx left");
		
		FieldVitesse = new JFormattedTextField();
		FieldVitesse.setText("0");
		panel_modele.add(FieldVitesse, "cell 1 2,growx");
		
		lblPositionActuelle = new JLabel("Initial Position");
		panel_modele.add(lblPositionActuelle, "cell 0 1,alignx left");
		
		FieldPosition = new JFormattedTextField();
		FieldPosition.setText("0");
		panel_modele.add(FieldPosition, "cell 1 1,growx");
		
		Gamma = new JLabel("Gamma");
		panel_modele.add(Gamma, "cell 0 15,alignx left");
		
		FieldGamma = new JFormattedTextField();
		FieldGamma.setText("0.99");
		panel_modele.add(FieldGamma, "cell 1 15,growx");
		
		Epsilon = new JLabel("Epsilon");
		panel_modele.add(Epsilon, "cell 0 16,alignx left");
		
		FieldEpsilon = new JFormattedTextField();
		FieldEpsilon.setText("0.01");
		panel_modele.add(FieldEpsilon, "cell 1 16,growx");
		

		btnLancerSimulation = new JButton("Launch Simulation");
		btnLancerSimulation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				Algorithm.Action.clear();
				Algorithm.Value.clear();
				Saveseries1.clear();
				SaveseriesPosition.clear();
				SaveseriesVitesse.clear();
				Modele.road.clear();
				Modele.hole.clear();
				Modele.inc = 0;
				Modele.inc2 = 0;
				Modele.init(Integer.parseInt(FieldDiscPosition.getText()), Integer.parseInt(FieldDiscVitesse.getText()),Double.parseDouble(FieldBB.getText()), Double.parseDouble(FieldRavine.getText()),Double.parseDouble(FieldRewardB.getText()), Double.parseDouble(FieldBH.getText()),Double.parseDouble(FieldLimitSpeed.getText()), Double.parseDouble(FieldGamma.getText()), Double.parseDouble(FieldEpsilon.getText()),Double.parseDouble(FieldPosition.getText()),Double.parseDouble(FieldVitesse.getText()),Double.parseDouble(FieldHauteurColine.getText()),Double.parseDouble(FieldPente.getText()),Double.parseDouble(FieldPower.getText()));
				Modele.set_current_altitude(Modele.current_position);
				Algorithm.creation_values();
				Algorithm.convergence();
				Algorithm.set_action();
				Modele.set_road();
				Modele.set_hole();
				

				for (int i=0; i < Modele.hole.size();i++)
				{
					double y = i * Modele.frequency_position/4 + Modele.low_border;
					double x = Modele.hole.get(i);
					serieRoute.add(y,x);
				}
				for (int i=0; i < Modele.road.size();i++)
				{
					serieRoute.add(i * Modele.frequency_position/4 + Modele.ravine,Modele.road.get(i));
				}
					

				int t = 0;
				//System.out.println(Modele.current_position);
				//System.out.println(Integer.parseInt(FieldDiscPosition.getText())+" / "+ Integer.parseInt(FieldDiscVitesse.getText())+" / "+Double.parseDouble(FieldBB.getText())+" / "+ Double.parseDouble(FieldRavine.getText())+" / "+Double.parseDouble(FieldRewardB.getText())+" / "+ Double.parseDouble(FieldBH.getText())+" / "+Double.parseDouble(FieldLimitSpeed.getText())+" / "+ Double.parseDouble(FieldGamma.getText())+" / "+ Double.parseDouble(FieldEpsilon.getText())+" / "+Double.parseDouble(FieldPosition.getText())+" / "+Double.parseDouble(FieldVitesse.getText())+" / "+Double.parseDouble(FieldHauteurColine.getText())+" / "+Double.parseDouble(FieldPente.getText())+" / "+Double.parseDouble(FieldPower.getText()));
				
				while(Modele.current_position != Integer.parseInt(FieldDiscPosition.getText())-1 && t<10000){
					if (Modele.X_array.get(Modele.current_position) <= Modele.ravine){
						JOptionPane.showMessageDialog(null,"In the Ravine \nYou're dead, game over!!!!!!");
						break;
					}
					if(t==9999){
						JOptionPane.showMessageDialog(null,"Infinite Loop : dicretisation too large");
						
					}
					Modele.speed_position_update();
					Saveseries1.add(Modele.X_array.get(Modele.current_position),Modele.speed_array.get(Modele.current_speed));
					SaveseriesPosition.add(t,Modele.X_array.get(Modele.current_position));
					SaveseriesVitesse.add(t,Modele.speed_array.get(Modele.current_speed));
					SaveseriesAlt.add(Modele.X_array.get(Modele.current_position),(Number)Modele.current_altitude);
					t++;
					
				}
				if(Modele.current_position == Integer.parseInt(FieldDiscPosition.getText())-1){
				JOptionPane.showMessageDialog(null,"Well done, you've climed the Hill \n in "+t+" iterations");
				}
				compteurMax = t;
				compteur=1;


				double vide[]={0};
				


				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(Saveseries1,"","Position","Vitesse"));
				panel_graph.add(Graphique.createChart(SaveseriesAlt,serieRoute,"","Altitude","Position"));
				panel_graph.add(Graphique.createChart(vide,"","Temps","Position"));
				panel_graph.add(Graphique.createChart(vide,"","Temps","Vitesse"));
				frame.repaint();

			}
		});
		panel_modele.add(btnLancerSimulation, "cell 0 17,growx");

		btnPrecedent = new JButton("Previous");
		btnPrecedent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
		        if (key == KeyEvent.VK_LEFT) 
		        {
		        	btnPrecedent.doClick();
		        }
				
				
			}
		});
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int tmpAjout = slider.getValue();

				if(compteur-tmpAjout > 0)
				{
					compteur = compteur-tmpAjout;
				}
				else
				{
					compteur = 0;
				}


				try {
					//series1 = (XYSeries) Saveseries1.clone();
					seriesPosition = (XYSeries) SaveseriesPosition.clone();
					seriesVitesse = (XYSeries) SaveseriesVitesse.clone();
					//seriesAlt = (XYSeries) SaveseriesAlt.clone();


				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			//	series1.delete(compteur, compteurMax-1);
				seriesPosition.delete(compteur, compteurMax-1);
				seriesVitesse.delete(compteur, compteurMax-1);
				//seriesAlt.delete(compteur, compteurMax-1);
				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(Saveseries1,"","Position","Vitesse"));
				panel_graph.add(Graphique.createChart(SaveseriesAlt,serieRoute,"","Position","Altitude"));
				panel_graph.add(Graphique.createChart(seriesPosition,"","Temps","Position"));
				panel_graph.add(Graphique.createChart(seriesVitesse,"","Temps","Vitesse"));
				frame.repaint();
			}
		});
		panel_modele.add(btnPrecedent, "cell 0 18,growx");

		btnSuivant = new JButton("Next");
		btnSuivant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
		        if (key == KeyEvent.VK_RIGHT) 
		        {
		        	btnSuivant.doClick();
		        }
				
				
			}
		});
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int tmpAjout = slider.getValue();

				if(compteur+tmpAjout <= compteurMax)
				{
					compteur = compteur+tmpAjout;
				}
				else
				{
					compteur = compteurMax;
				}


				try {
				//	series1 = (XYSeries) Saveseries1.clone();
					seriesPosition = (XYSeries) SaveseriesPosition.clone();
					seriesVitesse = (XYSeries) SaveseriesVitesse.clone();
				//	seriesAlt = (XYSeries) SaveseriesAlt.clone();


				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			//	series1.delete(compteur, compteurMax-1);
				seriesPosition.delete(compteur, compteurMax-1);
				seriesVitesse.delete(compteur, compteurMax-1);
				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(Saveseries1,"","Position","Speed"));
				panel_graph.add(Graphique.createChart(SaveseriesAlt,serieRoute,"","Position","Altitude"));
				panel_graph.add(Graphique.createChart(seriesPosition,"","Time","Position"));
				panel_graph.add(Graphique.createChart(seriesVitesse,"","Time","Speed"));
				frame.repaint();
			}
		});
		panel_modele.add(btnSuivant, "cell 1 18,growx");




		lblValeurSlide = new JLabel("1");
		panel_modele.add(lblValeurSlide, "cell 0 20 2 1,alignx center");

		scrollPane = new JScrollPane();
		panel_simulation.add(scrollPane, "cell 1 0,grow");

		panel_graph = new JPanel();
		panel_graph.setBounds(new Rectangle(0, 0, 500, 500));
		scrollPane.setViewportView(panel_graph);
		panel_graph.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_graph.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_graph.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_graph.setLayout(new GridLayout(3, 1));

		panel_Voiture = new JPanel();
		tabbedPane.addTab("Simulation", null, panel_Voiture, null);

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				int valeur = slider.getValue();
				lblValeurSlide.setText(Integer.toString(slider.getValue()));

			}
		});
		slider.setMinimum(1);
		slider.setValue(10);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(10);
		panel_modele.add(slider, "cell 0 19 2 1,growx");




	}
}
