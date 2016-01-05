package markov;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
;


public class Graphique {
	
	public static ChartPanel createChart(double[] data){
		final XYSeries series1 = new XYSeries("Simulation");
		
		for(int i =0;i<data.length;i++){
			series1.add(i, data[i]);
		}
		XYSeriesCollection dts = new XYSeriesCollection();
		dts.addSeries(series1);
		JFreeChart jfc = ChartFactory.createXYLineChart(
				"Simulation",
				"Position",
				"Vitesse", 
				dts, 
				PlotOrientation.VERTICAL, 
				false, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}
	
	public static ChartPanel createChart(final XYSeries series1){
		
		XYSeriesCollection dts = new XYSeriesCollection();
		dts.addSeries(series1);
		JFreeChart jfc = ChartFactory.createXYLineChart(
				"Simulation",
				"Position",
				"Vitesse", 
				dts, 
				PlotOrientation.VERTICAL, 
				false, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}
}
