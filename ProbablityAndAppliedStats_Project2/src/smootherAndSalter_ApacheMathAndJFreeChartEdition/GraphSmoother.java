package smootherAndSalter_ApacheMathAndJFreeChartEdition;

public class GraphSmoother {
	/**
	 * Checks the start case moving average of the yValueList and stores it in to the windowValueList
	 * @param windowValue: moving average range
	 * @param index: current index location
	 * @param windowValueList: storing temporary values within the moving average range.
	 * @param yValueList: list we get the numbers from
	 */
	public void startCase(int windowValue, int index, double[] windowValueList, double[] yValueList)
	{
		int windowValueStartCase = windowValue + index;
		for(int j = 0; j <= windowValueStartCase; j++)
		{
			windowValueList[j] = yValueList[j];
		}
	}
	/**
	 * Checks the mid case moving average of the yValueList and stores it in to the windowValueList
	 * @param windowValue: moving average range
	 * @param index: current index location
	 * @param windowValueList: storing temporary values within the moving average
	 * @param yValueList: list we get the numbers from
	 */
	public void midCase(int windowValue, int index, double[] windowValueList, double[] yValueList)
	{
		for(int j = 0; j <= windowValue * 2; j++)
		{
			int windowRangeMidCase = (index - windowValue) + j;
			windowValueList[j] = yValueList[windowRangeMidCase];
		}
	}
	/**
	 * Checks the end case moving average of the yValueList and stores it in to the windowValueList
	 * @param windowValue: moving average range
	 * @param index: current index location
	 * @param size: size of the list
	 * @param windowValueList:storing temporary values within the moving average
	 * @param yValueList: list we get the numbers from
	 */
	public void endCase(int windowValue, int index,int size ,double[] windowValueList, double[] yValueList)
	{
		int windowRangeEndCase = size - (index - windowValue);
		for(int j = 0; j < windowValueList.length; j++)
		{
			windowValueList[j] = 0;
		}
		for(int j = 0; j < windowRangeEndCase; j++)
		{
			windowValueList[j] = yValueList[(index - windowValue) + j];
		}
	}
	/**
	 * counts non zero numbers within the list
	 * @param countedList: list we want to count
	 * @return returns the total non zeros in the list
	 */
	public int countingNonZeros(double[] countedList)
	{
		int counter = 0;
		for(int i = 0; i < countedList.length; i++)
		{
			if(countedList[i] != 0.0)
			{
				counter++;
			}
		}
		return counter;
	}

}
