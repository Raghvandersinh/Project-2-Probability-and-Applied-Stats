package smootherAndSalter_JavaEdition;
import java.util.ArrayList;
public class GraphSmoother {
	
	/**
	 * Checks the start case moving average of the list and stores it into the windowValueList
	 * @param WindowValue: moving average range
	 * @param i: current index of the list
	 * @param windowValueList: storing the temporary values within the moving average range
	 * @param yValueList: list we get the numbers from
	 */
	public void startSmoothValue(int windowValue, int index, ArrayList<Double> windowValueList, ArrayList<Double> yValueList)
		{
			int windowValueStartCase = windowValue + index;
			for(int j = 0; j <= windowValueStartCase; j++)
			{
				windowValueList.add(yValueList.get(j));
			}
		}
	/**
	 * Checks the middle case moving average of the list and stores it into the windowValueList
	 * @param windowValue: moving average range
	 * @param index: current index of the list
	 * @param windowValueList: storing the temporary values within the moving average range
	 * @param tempYList: list we get the numbers from
	 */
	public void middleSmoothValue(int windowValue, int index, ArrayList<Double> windowValueList, ArrayList<Double> yValueList)
	{
		for(int j = 0; j <= windowValue * 2; j++)
		{
			int windowRangeMidCase = (index - windowValue) + j;
			windowValueList.add(yValueList.get(windowRangeMidCase));
		}
	}
	/**
	 * Checks the end case moving average and stores it into the windowValueList
	 * @param windowValue: moving average
	 * @param index: current index location
	 * @param size: size of the yValueList
	 * @param windowValueList: storing the temporary values within the moving average range
	 * @param yValueList: list we get the numbers from
	 */
	public void endSmoothValue(int windowValue, int index, int size, ArrayList<Double> windowValueList, ArrayList<Double> yValueList)
	{
		
		int windowRangeEndCase = size - (index - windowValue);
		for(int  j = 0; j < windowRangeEndCase; j++)
		{
			windowValueList.add(yValueList.get((index - windowValue) + j));
		}
	}
	/**
	 * gets mean of a list
	 * @param graphSmootherList: list we want to get the mean from
	 * @return returns a mean of the list
	 */
	public double getMean(ArrayList<Double> graphSmootherList)
	{
		double mean = 0;
		for(int j = 0; j < graphSmootherList.size(); j++)
		{
			mean += graphSmootherList.get(j);
		}
		return mean/graphSmootherList.size();
	}
}
