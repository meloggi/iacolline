package markov;

import net.sourceforge.chart2d.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
;


public class Graphique {
	
	public static ChartPanel createChart(double[] data){
		final XYSeries series1 = new XYSeries("Frequency");
		
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
				true, 
				true,
				false
		);
		return  new ChartPanel(jfc);
	}
	

	static LBChart2D  Graphique()
	{
		// TODO Auto-generated method stub
		Object2DProperties object2DProps = new Object2DProperties();
		object2DProps.setObjectTitleText ("Title for this Object");

		Chart2DProperties chart2DProps = new Chart2DProperties();

		LegendProperties legendProps = new LegendProperties();
		legendProps.setLegendExistence (false);

		GraphChart2DProperties graph2DProps = new GraphChart2DProperties();

		GraphProperties graphProps = new GraphProperties();

		MultiColorsProperties catColorsProps = new MultiColorsProperties();


		MultiColorsProperties multiColorsProps = new MultiColorsProperties();

		GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
		graphChart2DProps.setGraphComponentsColoringByCat (true);
		graphChart2DProps.setGraphComponentsColorsByCat (catColorsProps);

		Dataset dataset = new Dataset (1, 24, 1);
		dataset.set (0, 0, 0, 0);  //where data1 is your first value (ex. 23f)
		dataset.set (0, 1, 0, 1);  //where data2 is your second value (ex. 56f)
		dataset.set (0, 2, 0, 2);  //where data3 is your third value (ex. 185f)




		LBChart2D lbChart2D = new LBChart2D();
		lbChart2D.setObject2DProperties (object2DProps);
		lbChart2D.setChart2DProperties (chart2DProps);
		lbChart2D.setLegendProperties (legendProps);
		lbChart2D.setGraphChart2DProperties (graphChart2DProps);
		lbChart2D.addGraphProperties (graphProps);
		lbChart2D.addDataset (dataset);
		lbChart2D.addMultiColorsProperties (multiColorsProps);
		
		return lbChart2D;

	}

}
