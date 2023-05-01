package smootherAndSalter_JavaEdition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;

public class ValueGenerator {

	private String filename = "QuadraticFunction.csv";
	private ArrayList<Integer> xValueList = new ArrayList<>();
	private ArrayList<Integer> yValueList = new ArrayList<>();
	private ArrayList<Double> saltValueList = new ArrayList<>();
	private ArrayList<Double> smootherValueList = new ArrayList<>();

	public ArrayList<Integer> getXValueList() {
		return xValueList;
	}

	public ArrayList<Integer> getYValueList() {
		return yValueList;
	}

	public ArrayList<Double> getSaltValueList() {
		return saltValueList;
	}

	public ArrayList<Double> smootherValueList() {
		return smootherValueList;
	}
	/**
	 * returns the result of the y function(F(x) = x^2 +x)
	 * @param x: input for the y function
	 * @return returns the result of the y function.
	 */
	public int getQuadraticFunction(int x) {
		int y = (x * x) + x;
		yValueList.add(y);
		return y;
	}
	/**
	 * Returns a salted value of y
	 * 
	 * @param randomNumberRange: range of the number you want to add or subtract
	 * @param y: y value
	 * @return returns the salted value
	 */
	private double getSaltedValue(int randomNumberRange, int y) {

		Random randomNumberGenerator = new Random();

		int randomNumber = randomNumberGenerator.nextInt(randomNumberRange) + 1;
		int positiveOrNegative = randomNumberGenerator.nextInt(2) + 1;
		double salt = 0;
		if (positiveOrNegative == 1) {
			salt = y + randomNumber;
		} else {
			salt = y - randomNumber;
		}
		saltValueList.add(salt);
		return salt;
	}
	/**
	 * Gets the moving average of the Y list 
	 * @param windowRange: moving average range
	 * @param runs: amount of times you want to smooth
	 */
	public void getSmootherValue(int windowRange, int runs) {
		ArrayList<String> yList = readColumn(1, filename, ",");
		ArrayList<Double> tempYList = new ArrayList<>();
		for (int i = 0; i < xValueList.size(); i++) {
			tempYList.add(Double.parseDouble(yList.get(i)));
		}
		ArrayList<Double> tempWindowValueList = new ArrayList<Double>();
		GraphSmoother gs = new GraphSmoother();
		double smoothMean = 0;
		if(runs > 0)
		{
			for(int k = 0; k < runs; k++)
			{
				for(int i = 0; i < xValueList.size(); i++)
				{
					if(i < windowRange)
					{
						gs.startSmoothValue(windowRange, i, tempWindowValueList, tempYList);
					}
					else if(i < xValueList.size() - windowRange)
					{
						gs.middleSmoothValue(windowRange, i, tempWindowValueList, tempYList);
					}
					else
					{
						gs.endSmoothValue(windowRange, i, xValueList.size(), tempWindowValueList, tempYList);
					}
					smoothMean = gs.getMean(tempWindowValueList);
					smootherValueList.add(smoothMean);
					tempWindowValueList.clear();
					smoothMean = 0;
				}
				tempYList.clear();
				for(Double s:smootherValueList)
				{
					tempYList.add(s);
				}
				if(k + 1 != runs)
				{
					smootherValueList.clear();
				}
			}
		}
		
	}

	/**
	 * prints out the X, Y, and Salt Value to the CSV file
	 * @throws FileNotFoundException
	 */
	public void writeXYSaltToCSV(int size) throws FileNotFoundException {

		File csvFile = new File(filename);
		PrintWriter out = new PrintWriter(csvFile);
		out.printf("%s, %s, %s\n", "X", "Y", "Salt");
		for (int i = 1; i <= size; i++) {
			int x = i;
			xValueList.add(x);
			int y = getQuadraticFunction(x);
			double salt = getSaltedValue(1000, y);
			out.printf("%d, %d, %.2f\n", x, y, salt);
		}
		out.close();
	}
	/**
	 * prints out the X, Smoothed Y, and Salt Values to the CSV file. 
	 */
	public void writeXSmoothSaltToCSV() throws FileNotFoundException
	{
		getSmootherValue(2, 100);
		File csvFile = new File(filename);
		PrintWriter out = new PrintWriter(csvFile);
		out.printf("%s, %s, %s\n", "X", "Y", "Salt");
		for(int i = 0; i < xValueList.size(); i++)
		{
			out.printf("%d, %.2f, %.2f\n", xValueList.get(i), smootherValueList.get(i), saltValueList.get(i));
		}
		out.close();
	}
	/**
	 * Reads a specific column from the csv file
	 * @param column: column number
	 * @param filepath: write the filepath of the file you want to read.
	 * @param delimeter: write the delimiter separating the values
	 * @return
	 */
	public ArrayList<String> readColumn(int column, String filepath, String delimeter) {
		String[] data;
		String currentLine;
		ArrayList<String> columnData = new ArrayList<>();

		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
				data = currentLine.split(delimeter);
				columnData.add(data[column]);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		columnData.remove(0);
		return columnData;
	}
}
