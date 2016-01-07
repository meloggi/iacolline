package markov;

import java.awt.Component;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
;


public class Graphique {
	
	public static ChartPanel createChart(double[] data, String titre,String LegendX,String LegendY){
		final XYSeries series1 = new XYSeries("Simulation");
		
		for(int i =0;i<data.length;i++){
			series1.add(i, data[i]);
		}
		XYSeriesCollection dts = new XYSeriesCollection();
		dts.addSeries(series1);
		JFreeChart jfc = ChartFactory.createXYLineChart(
				titre,
				LegendX,
				LegendY, 
				dts, 
				PlotOrientation.VERTICAL, 
				false, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}
	
	public static ChartPanel createChart(final XYSeries series1, String titre,String LegendX,String LegendY){
		
		XYSeriesCollection dts = new XYSeriesCollection();
		dts.addSeries(series1);
		JFreeChart jfc = ChartFactory.createScatterPlot
				(
				titre,
				LegendX,
				LegendY, 
				dts, 
				PlotOrientation.VERTICAL, 
				false, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}

	public static Component createChart(XYSeries seriesPosition, XYSeries seriesVitesse, String titre,String LegendX,String LegendY)
	{
		XYSeriesCollection dts = new XYSeriesCollection();
		dts.addSeries(seriesPosition);
		dts.addSeries(seriesVitesse);
		JFreeChart jfc = ChartFactory.createScatterPlot(
				titre,
				LegendX,
				LegendY,  
				dts, 
				PlotOrientation.VERTICAL, 
				true, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}

}
