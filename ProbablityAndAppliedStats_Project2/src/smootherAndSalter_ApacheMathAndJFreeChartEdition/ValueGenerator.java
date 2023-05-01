package smootherAndSalter_ApacheMathAndJFreeChartEdition;

import org.apache.commons.math4.core.jdkmath.JdkMath;
import org.apache.commons.math4.legacy.stat.StatUtils;
import org.apache.commons.lang3.*;

public class ValueGenerator {

	private double[] xValueList;
	private double[] yValueList;
	private double[] saltValueList;
	private double[] smoothValueList;

	public double[] getxValueList() {
		return xValueList;
	}

	public double[] getyValueList() {
		return yValueList;
	}

	public double[] getSaltValueList() {
		return saltValueList;
	}

	public double[] getSmoothValueList() {
		return smoothValueList;
	}
	/**
	 * populates xValuelist with x values.
	 * @param size: size of the array
	 */
	public void populateXList(int size) {
		xValueList = new double[size];
		for (int i = 0; i < size; i++) {
			xValueList[i] = i + 1;
		}
	}
	/**
	 * populates yValueList with y values(F(x) = x^2 + X)
	 * @param size: size of the array
	 */
	public void populateYList(int size) {
		yValueList = new double[size];
		for (int i = 0; i < size; i++) {
			double y = JdkMath.pow(xValueList[i], 2) + xValueList[i];
			yValueList[i] = y;
		}
	}
	/**
	 * populates the saltValueList with salted y Values
	 * @param size: size of the area
	 * @param randomNumberRange: random number between (-input1,input1)
	 */
	public void populateSaltList(int size, int randomNumberRange) {
		saltValueList = new double[size];
		for (int i = 0; i < size; i++) {
			int randomNumbers = RandomUtils.nextInt(0, randomNumberRange * 2) - randomNumberRange;
			double salt = yValueList[i] - randomNumbers;
			saltValueList[i] = salt;
		}
	}
	/**
	 * populates the smoothValueList with moving average of y values.
	 * @param size: size of the array
	 * @param windowRange: moving average range
	 * @param runs: amount times you want to smooth the y values.
	 */
	public void populateSmootherList(int size, int windowRange, int runs) {
		smoothValueList = new double[size];
		double[] tempYList = new double[size];
		for (int i = 0; i < size; i++) {
			tempYList[i] = yValueList[i];
		}
		double[] tempWindowValueList = new double[windowRange * 2 + 1];
		double mean = 0;
		GraphSmoother gs = new GraphSmoother();
		if (runs > 0) {
			for (int k = 0; k < runs; k++) {
				for (int i = 0; i < size; i++) {
					if (i < windowRange) {
						gs.startCase(windowRange, i, tempWindowValueList, tempYList);
					} else if (i < size - windowRange) {
						gs.midCase(windowRange, i, tempWindowValueList, tempYList);
					} else {
						gs.endCase(windowRange, i, size, tempWindowValueList, tempYList);
					}
					int count = gs.countingNonZeros(tempWindowValueList);
					mean = StatUtils.mean(tempWindowValueList, 0, count);
					count = 0;
					smoothValueList[i] = mean;

				}
				for (int i = 0; i < size; i++) {
					tempYList[i] = smoothValueList[i];
				}
			}
		} else {
			System.out.println("Run = 0: No Smoothing");
		}
	}
}
