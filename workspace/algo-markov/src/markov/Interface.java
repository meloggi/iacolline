package markov;



import javax.swing.JFrame;
import javax.swing.JButton;
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
	public Label label;
	public JFormattedTextField FieldPente;
	public JLabel lblDiscretisationPosition;
	public JFormattedTextField FieldDiscPosition;
	public Label label_1;
	public JFormattedTextField FieldDiscVitesse;
	public JLabel lblVitesseActuelle;
	public JFormattedTextField FieldVitesse;
	public JLabel lblPositionActuelle;
	public JFormattedTextField FieldPosition;
	public JPanel panel_graph;

	public Graphique graph;
	public JButton btnLancerSimulation;

	public double[] data;
	XYSeries series1 = new XYSeries("Simulation");
	XYSeries seriesVitesse = new XYSeries("Vitesse");
	XYSeries seriesPosition = new XYSeries("Position");

	XYSeries Saveseries1 = new XYSeries("Simulation");
	XYSeries SaveseriesVitesse = new XYSeries("Vitesse");
	XYSeries SaveseriesPosition = new XYSeries("Position");


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
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[970.00px,grow]", "[643px,grow]"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");

		panel_simulation = new JPanel();
		tabbedPane.addTab("Donn�es Modele", null, panel_simulation, null);
		panel_simulation.setLayout(new MigLayout("", "[332.00px,left][176.00,grow]", "[550px,grow][1px]"));

		panel_modele = new JPanel();
		panel_simulation.add(panel_modele, "cell 0 0,alignx left,aligny top");
		panel_modele.setLayout(new MigLayout("", "[left][149.00,grow]", "[][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][][][]"));

		lblNewLabel = new JLabel("Bordure haut");
		panel_modele.add(lblNewLabel, "cell 0 0,alignx left,aligny top");

		FieldBH = new JFormattedTextField();
		FieldBH.setText("0,6");
		panel_modele.add(FieldBH, "cell 1 0,growx");

		lblBordureBas = new JLabel("Bordure bas");
		panel_modele.add(lblBordureBas, "cell 0 1,alignx left");

		FieldBB = new JFormattedTextField();
		FieldBB.setText("-1,2");
		panel_modele.add(FieldBB, "cell 1 1,growx");

		lblRavine = new JLabel("Ravine");
		panel_modele.add(lblRavine, "cell 0 2,alignx left");

		FieldRavine = new JFormattedTextField();
		FieldRavine.setText("-1,55");
		panel_modele.add(FieldRavine, "cell 1 2,growx");

		lblRewardBorder = new JLabel("Reward border");
		panel_modele.add(lblRewardBorder, "cell 0 3,alignx left");

		FieldRewardB = new JFormattedTextField();
		FieldRewardB.setText("0,55");
		panel_modele.add(FieldRewardB, "cell 1 3,growx");

		lblLimiteSpeed = new JLabel("Limite speed");
		panel_modele.add(lblLimiteSpeed, "cell 0 5,alignx left");

		FieldLimitSpeed = new JFormattedTextField();
		FieldLimitSpeed.setText("0,07");
		panel_modele.add(FieldLimitSpeed, "cell 1 5,growx");

		lblPuissance = new JLabel("Puissance");
		panel_modele.add(lblPuissance, "cell 0 6,alignx left");

		FieldPower = new JFormattedTextField();
		FieldPower.setText("0,01");
		panel_modele.add(FieldPower, "cell 1 6,growx");

		lblHauteurColine = new JLabel("Hauteur coline");
		panel_modele.add(lblHauteurColine, "cell 0 8,alignx left");

		FieldHauteurColine = new JFormattedTextField();
		panel_modele.add(FieldHauteurColine, "cell 1 8,growx");

		label = new Label("Pente");
		panel_modele.add(label, "cell 0 9");

		FieldPente = new JFormattedTextField();
		panel_modele.add(FieldPente, "cell 1 9,growx");

		lblDiscretisationPosition = new JLabel("Discretisation position");
		panel_modele.add(lblDiscretisationPosition, "cell 0 11,alignx left");

		FieldDiscPosition = new JFormattedTextField();
		FieldDiscPosition.setText("32");
		panel_modele.add(FieldDiscPosition, "cell 1 11,growx");

		label_1 = new Label("Discretisation vitesse");
		panel_modele.add(label_1, "cell 0 12,alignx left");

		FieldDiscVitesse = new JFormattedTextField();
		FieldDiscVitesse.setText("32");
		panel_modele.add(FieldDiscVitesse, "cell 1 12,growx");

		lblVitesseActuelle = new JLabel("Vitesse actuelle");
		panel_modele.add(lblVitesseActuelle, "cell 0 14,alignx left");

		FieldVitesse = new JFormattedTextField();
		FieldVitesse.setText("0");
		panel_modele.add(FieldVitesse, "cell 1 14,growx");

		lblPositionActuelle = new JLabel("Position actuelle");
		panel_modele.add(lblPositionActuelle, "cell 0 15,alignx left");

		FieldPosition = new JFormattedTextField();
		FieldPosition.setText("0");
		panel_modele.add(FieldPosition, "cell 1 15,growx");

		btnLancerSimulation = new JButton("Lancer Simulation");
		btnLancerSimulation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Modele.init(100, 100, -1.2, -1.15, 0.54, 0.6, 0.07, 0.99, 0.01, 0, 0, 0.0025, 3, 0.001);
				Algorithm.creation_values();
				Algorithm.convergence();
				Algorithm.set_action();

				int t = 0;
				while(Modele.current_position != 99 ){
					if (Modele.X_array.get(Modele.current_position) <= Modele.ravine){
						System.out.println("You're dead game over!!!!!!");
						break;
					}
					Modele.speed_position_update();
					Saveseries1.add(Modele.X_array.get(Modele.current_position),Modele.speed_array.get(Modele.current_speed));
					SaveseriesPosition.add(t,Modele.X_array.get(Modele.current_position));
					SaveseriesVitesse.add(t,Modele.speed_array.get(Modele.current_speed));
					t++;


				}
				System.out.println("Nombre d'itération = "+t);
				compteurMax = t;
				compteur=1;


				//renverser le tableau
				/*
				for (int i = t-1;i>0;i--)
				{
					series1.add(Saveseries1.getX(i),Saveseries1.getY(i));
					seriesPosition.add(SaveseriesPosition.getX(i),SaveseriesPosition.getY(i));
					seriesVitesse.add(SaveseriesVitesse.getX(i),SaveseriesVitesse.getY(i));
				}

				try {
					Saveseries1 = series1;
					SaveseriesPosition = (XYSeries) seriesPosition.clone();
					SaveseriesVitesse = (XYSeries) seriesVitesse.clone();


				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 */

				double vide[]={0};


				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(vide,"","Position","Vitesse"));
				panel_graph.add(Graphique.createChart(vide,"","Temps","Position"));
				panel_graph.add(Graphique.createChart(vide,"","Temps","Vitesse"));
				frame.repaint();

			}
		});
		panel_modele.add(btnLancerSimulation, "cell 0 17,growx");

		btnPrecedent = new JButton("Precedent");
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
					series1 = (XYSeries) Saveseries1.clone();
					seriesPosition = (XYSeries) SaveseriesPosition.clone();
					seriesVitesse = (XYSeries) SaveseriesVitesse.clone();


				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				series1.delete(compteur, compteurMax-1);
				seriesPosition.delete(compteur, compteurMax-1);
				seriesVitesse.delete(compteur, compteurMax-1);
				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(series1,"","Position","Vitesse"));
				panel_graph.add(Graphique.createChart(seriesPosition,"","Temps","Position"));
				panel_graph.add(Graphique.createChart(seriesVitesse,"","Temps","Vitesse"));
				frame.repaint();
			}
		});
		panel_modele.add(btnPrecedent, "cell 0 18,growx");

		btnSuivant = new JButton("Suivant");
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
					series1 = (XYSeries) Saveseries1.clone();
					seriesPosition = (XYSeries) SaveseriesPosition.clone();
					seriesVitesse = (XYSeries) SaveseriesVitesse.clone();


				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				series1.delete(compteur, compteurMax-1);
				seriesPosition.delete(compteur, compteurMax-1);
				seriesVitesse.delete(compteur, compteurMax-1);
				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(series1,"","Position","Vitesse"));
				panel_graph.add(Graphique.createChart(seriesPosition,"","Temps","Position"));
				panel_graph.add(Graphique.createChart(seriesVitesse,"","Temps","Vitesse"));
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
