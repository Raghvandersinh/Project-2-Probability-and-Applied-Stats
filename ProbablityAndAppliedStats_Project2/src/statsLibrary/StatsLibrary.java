package statsLibrary;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.math.BigDecimal;
/**
 * 
 * @author Raghvandersinh Solanki
 *	Contains methods that finds mean, median mode, standard deviation, permutation,
 *	Combinations, Factorial, and Binomial distribution
 */

public class StatsLibrary {

	/**
	 * Sorts an ArrayList
	 * @param numberList- ArrayList to be sorted
	 */
	public void sort(ArrayList<Double> numberList) {
		double temp = 0;
		for (int i = 0; i < numberList.size(); i++) {
			for (int j = 0; j < numberList.size(); j++) {
				if (numberList.get(i) < numberList.get(j)) {
					temp = numberList.get(i);
					numberList.set(i, numberList.get(j));
					numberList.set(j, temp);
				}
			}
		}
	}
	
	/**
	 * Finds the mean of an ArrayList.
	 * @param numberList = ArrayList of Double
	 * @return returns the mean of the ArrayList
	 */

	public double mean(ArrayList<Double> numberList) {

		int mean = 0;
		int total = 0;
		for (double n : numberList) {
			total += n;
		}

		mean = total / numberList.size();
		return mean;
	}
	
	/**
	 * Finds the median in the ArrayList
	 * @param numberList ArrayList of Double
	 * @return returns the median of the ArrayList.
	 */

	public double median(ArrayList<Double> numberList) {
		double median = 0;
		int middle = numberList.size() / 2;
		if (numberList.size() % 2 != 0) {

			median = numberList.get(middle);
		} else {
			median = (numberList.get(middle) + numberList.get(middle - 1)) / 2;
		}
		return median;
	}

	/**
	 * Finds the mode in the ArrayList (Note: there can be only be one mode)
	 * @param numberList -  ArrayList of Double
	 */
	public void mode(ArrayList<Double> numberList) {
		double mode = 0;
		int maxCount = 0;
		int modeCountFail = 0;
		//Finds the mode 
		for (int i = 0; i < numberList.size(); i++) {
			int count = 0;

			for (int j = 0; j < numberList.size(); j++) {
				double elementI = numberList.get(i);
				double elementJ = numberList.get(j);
				if (elementI == elementJ) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				mode = numberList.get(i);
			}

			count = 0;
		}
		//Finds the another mode. Since we can only have one Mode
		for (int i = 0; i < numberList.size(); i++) {

			int count = 0;

			for (int j = 0; j < numberList.size(); j++) {
				double elementI = numberList.get(i);
				double elementJ = numberList.get(j);
				if(elementI == mode)
				{
				      count = 0;
				}
				else if (elementI == elementJ) {
					count++;
				}
			}
			if (count > modeCountFail) {
				modeCountFail = count;
			}
			count = 0;
		}
		if (modeCountFail == maxCount) {
			System.out.println("No Mode");

		} else {
			System.out.println("Mode: " + mode);
		}

	}
	/**
	 * Finds the Standard Deviation of the ArrayList.
	 * @param numberList ArrayList of Double.
	 * @return returns the Standard Deviation of the ArrayList.
	 */
	public double statdardDeviation(ArrayList<Double> numberList) {
		double stnDeviation;
		double mean = mean(numberList);
		ArrayList<Double> temp = new ArrayList<>();
		for (double n : numberList) {
			double newMean = Math.pow(n - 50, 2);
			temp.add(newMean);
		}
		int sumNewMean = 0;
		for (double t : temp) {
			sumNewMean += t;
		}
		double variance = sumNewMean / (temp.size() - 1);
		stnDeviation = Math.sqrt(variance);
		return stnDeviation;
	}
	/**
	 * gets a permutation number
	 * @param sampleSpace: enter a sample space.
	 * @param options: enter number of object selected.
	 */
	public BigInteger getPermutation(int sampleSpace, int objectSelected)
	{
		BigInteger r = getFactorial(sampleSpace - objectSelected);
		BigInteger permutation = getFactorial(sampleSpace).divide(r);
		return permutation;
	}
	/**
	 * gets a combination number
	 * @param sampleSpace: enter a sample space.
	 * @param options: enter number of object selected.
	 */
	public BigInteger getCombination(int sampleSpace, int options)
	{
		BigInteger r = getFactorial(sampleSpace - options);
		BigInteger combination = getFactorial(sampleSpace).divide((r.multiply(getFactorial(options))));		
		return combination;
	}
	/**
	 * gets a factorial number.
	 * @param input1: enter a number that you want to get factorial of
	 * Citation:
	 * I was lost on how to loop through a BigInteger, but I simply had to reassign the
	 * factorial variable to a new value. 
	 * https://www.youtube.com/watch?v=UD8rCe_QVOE
	 */
	public BigInteger getFactorial(int input1)
	{
		BigInteger factorial = BigInteger.ONE;
		
		for(int i = 1; i <= input1; i++)
		{
			long iToLong = i;
			BigInteger iValue = BigInteger.valueOf(iToLong);
			factorial = factorial.multiply(iValue);
		}
		return factorial;
	}
	/**
	 * gets a binomial distribution number
	 * @param sampleSpace: enter the sample space
	 * @param success: probability of success
	 * @param failure: probability of failure
	 * @param option: enter the number of object selected
	 * I didn't know how to turn a BigInteger into a double, I searched it up and found
	 * that I can use BigDecimal from stackOverflow.
	 * https://stackoverflow.com/questions/13105905/is-it-possible-to-multiple-a-biginteger-by-a-double-in-java
	 */
	public BigDecimal getBinomialDistribution(int sampleSpace,double success, double failure, int option)
	{
		BigDecimal combination = new BigDecimal(getCombination(sampleSpace, option));
		double variableSuccess = Math.pow(success, option);
		double variableFailure = Math.pow(failure, sampleSpace - option);
		combination = combination.multiply(new BigDecimal(variableSuccess));
		combination = combination.multiply(new BigDecimal(variableFailure));
		return combination;
		
	}
	/**
	 * gets a geometric distribution number 
	 * @param numberOfFailure: number of failure till we get a success
	 * @param failure: probability of failure
	 * @param success: probability of success
	 * @return returns a geometric distribution number..
	 */
	public double getGeometricDistribution(int numberOfFailure, double failure, double success)
	{
		double constantFail = failure;
		for(int i = 0; i < numberOfFailure - 1; i++)
		{
			failure *= constantFail;
		}
		double geometricDistribution = failure * success;
		return geometricDistribution;
	}
//============================================Project 2=========================================================
	/**
	 * gets a Possion Distribution number
	 * @param lambda: lambda number
	 * @param trials: number of trials
	 * @return a possion distribution number
	 */
	public BigDecimal getPossionDistribution(double lambda, int trials)
	{
		final double euler = 2.7182818;
		
		double eulerToTheLambda = 1/(Math.pow(euler, lambda));
		double lambdaToTheTrials = Math.pow(lambda, trials);
		BigDecimal possionDistribution = new BigDecimal(eulerToTheLambda);
		possionDistribution = possionDistribution.multiply(new BigDecimal(lambdaToTheTrials));
		possionDistribution = possionDistribution.divide(new BigDecimal(getFactorial(trials)),4,RoundingMode.HALF_UP);
		return possionDistribution;
	}
	/**
	 * gets a hyperGeometric Number
	 * @param sampleSpace: enter "N"
	 * @param success: enter "r"
	 * @param observedSuccess: enter "y"
	 * @param draws: enter "n"
	 * @return returns a Hyper Geometric Number.
	 */
	public BigDecimal getHyperGeometricNumber(int sampleSpace, int success, int observedSuccess, int draws)
	{
		BigInteger waysOfSelectingObservedSuccess = getCombination(success, observedSuccess);
		BigInteger waysOfSelectingBlackElements = getCombination(sampleSpace-success, draws-observedSuccess);
		BigInteger totalPossibleCombination = getCombination(sampleSpace,draws);
		
		BigDecimal hyperGeometric = new BigDecimal(waysOfSelectingObservedSuccess);
	    hyperGeometric = hyperGeometric.multiply(new BigDecimal(waysOfSelectingBlackElements));
	    hyperGeometric = hyperGeometric.divide(new BigDecimal(totalPossibleCombination), 4, RoundingMode.HALF_UP);
		
		return hyperGeometric;
		
	}
	/**
	 * gets the tchebysheff theorem number
	 * @param mean: enter a mean
	 * @param standardDeviation: enter a standard deviation
	 * @param minimumRange: enter the minimum range
	 * @param maximumRange: enter the maximum range
	 * @return return tchebysheff number
	 */
	public double getTchebysheffTheorem(double mean, double standardDeviation, int minimumRange, int maximumRange)
	{
			double withInNumber = mean - minimumRange;
			double k = withInNumber/standardDeviation;
			double result = 1 - 1/Math.pow(k, 2);
			return result;
	}
	/**
	 * gets the Uniform Distribution number
	 * @param minimumRange: enter the minimum Range
	 * @param maximumRange enter the maximum Range
	 */
	public void getUniformDistribution(int minimumRange, int maximumRange)
	{
		int middleRange = 0;
		boolean flag = false;
		Scanner in = new Scanner(System.in);
		while(flag == false)
		{
			System.out.println("Enter a number between: (" + minimumRange + ", " + maximumRange + ")");
			middleRange = in.nextInt();
			if(middleRange > minimumRange && middleRange < maximumRange)
			{
				flag = true;
			}
			else
			{
				System.out.println("Please following the instruction");
			}
		}
		double uniformMax = (maximumRange - middleRange)/2;
		double uniformMin = (middleRange - minimumRange)/2;
		System.out.println("Uniform: Max - Mid: " + uniformMax);
		System.out.println("Uniform: Mid - Min: " + uniformMin);
		
	}

}
