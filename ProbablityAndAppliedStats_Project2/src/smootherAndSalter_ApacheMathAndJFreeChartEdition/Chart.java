package smootherAndSalter_ApacheMathAndJFreeChartEdition;
import org.jfree.chart.ChartPanel;

import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart extends ApplicationFrame {

	public Chart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "X", "Y", createDataset(100),PlotOrientation.VERTICAL,
		         true,true,false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    setContentPane( chartPanel );
	}
	/**
	 * stores the values into the DefaultCategoryDataset
	 * @param: defines the size of the arrays we want to populate. 
	 * @return returns the data set
	 */
	 private DefaultCategoryDataset createDataset(int size) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		ValueGenerator vg = new ValueGenerator();
		vg.populateXList(size);
		vg.populateYList(size);
		vg.populateSaltList(size, 1000);
		vg.populateSmootherList(size, 3, 20);
		double[] xValueList = vg.getxValueList();
		double[] yValueList = vg.getyValueList();
		double[] saltValueList = vg.getSaltValueList();
		double[] smoothValueList = vg.getSmoothValueList();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter A: for Salter");
		System.out.println("Enter B: for Smoother");
		System.out.println("Else it will just print the smoother");
		
		String choice = in.nextLine();
		if(choice.equalsIgnoreCase("A")) {
			for(int i = 0; i < size; i++)
			{
				dataset.addValue(saltValueList[i], "Quadratic", String.valueOf(xValueList[i]));
			}
		}
		else if(choice.equalsIgnoreCase("B"))
		{
			for(int i = 0; i < size; i++)
			{
				dataset.addValue(smoothValueList[i], "Quadratic", String.valueOf(xValueList[i]));
			}
		}
		else
		{
			for(int i = 0; i < size; i++)
			{
				dataset.addValue(smoothValueList[i], "Quadratic", String.valueOf(xValueList[i]));
			}
		}

		 return dataset;
	 }
	      
}
