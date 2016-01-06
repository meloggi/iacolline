package markov;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.jfree.data.xy.XYSeries;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import java.awt.Label;
import java.awt.Button;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;

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
	final XYSeries series1 = new XYSeries("Simulation");
	final XYSeries seriesVitesse = new XYSeries("Vitesse");
	final XYSeries seriesPosition = new XYSeries("Position");
	private JScrollPane scrollPane;

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
	//	frame.setBounds(100, 100, 1232, 699);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[334.00px][771.00px,grow,right]", "[643px,grow]"));
		
		panel_modele = new JPanel();
		frame.getContentPane().add(panel_modele, "cell 0 0,alignx left,aligny top");
		panel_modele.setLayout(new MigLayout("", "[left][:229.00:100px,grow][47.00]", "[][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top][top]"));
		
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
				ValueIteration.init(100, 100, -1.2, -1.15, 0.54, 0.6, 0.07, 0.99, 0.01, 0, 0, 0.0025, 3, 0.001);
				Algorithm.creation_values();
				Algorithm.convergence();
				Algorithm.set_action();
				
				int t = 0;
				while(ValueIteration.current_position != 99 ){
					if (ValueIteration.X_array.get(ValueIteration.current_position) <= ValueIteration.ravine){
						System.out.println("You're dead game over!!!!!!");
						break;
					}
					ValueIteration.speed_position_update();
					series1.add(ValueIteration.X_array.get(ValueIteration.current_position),ValueIteration.speed_array.get(ValueIteration.current_speed));
					seriesPosition.add(t,ValueIteration.X_array.get(ValueIteration.current_position));
					seriesVitesse.add(t,ValueIteration.speed_array.get(ValueIteration.current_speed));
					t++;
					
				}
				System.out.println("Nombre d'itération = "+t);
				
				panel_graph.removeAll();
				panel_graph.add(Graphique.createChart(series1));
				panel_graph.add(Graphique.createChart(seriesPosition));
				panel_graph.add(Graphique.createChart(seriesVitesse));
				frame.pack();
				
			}
		});
		panel_modele.add(btnLancerSimulation, "cell 0 17");
		
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 1 0,grow");
		
		panel_graph = new JPanel();
		scrollPane.setViewportView(panel_graph);
		panel_graph.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_graph.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_graph.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_graph.setLayout(new GridLayout(3, 1));
		
		
		
		
	}
}
