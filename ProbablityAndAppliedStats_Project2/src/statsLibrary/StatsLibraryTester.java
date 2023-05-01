package statsLibrary;
import java.util.ArrayList;

public class StatsLibraryTester {
	
	public static void main(String[] args) {
		
		StatsLibrary test = new StatsLibrary();
		ArrayList<Double> someNumbers = new ArrayList<>();
		someNumbers.add(46.0);
		someNumbers.add(69.0);
		someNumbers.add(52.0);
		someNumbers.add(32.0);
		someNumbers.add(41.0);
		someNumbers.add(60.0);
		
		
		test.sort(someNumbers);
		double mean = test.mean(someNumbers);
		double standardDeviation = test.statdardDeviation(someNumbers);
		double median = test.median(someNumbers);
		
		
		System.out.println("Mean: " + mean);
		System.out.println("Median: " + median);
		test.mode(someNumbers);
		System.out.printf("Standard Deviation: %.4f\n" , standardDeviation);
		System.out.println("Combination: " + test.getCombination(20,5));
		System.out.println("Permutation: " + test.getPermutation(5, 2));
		System.out.println("Factorial: " + test.getFactorial(10));
		System.out.printf("Binomial Distribution: %.4f ", test.getBinomialDistribution(10, 0.80, 0.20, 7));
		System.out.printf("\nGeometric Distribution %.4f\n", test.getGeometricDistribution(3, 0.83, 0.16));
		System.out.println("Possion Distribution " + test.getPossionDistribution(5, 0));
		System.out.println("HyperGeometric Distribution " + test.getHyperGeometricNumber(20, 6, 4, 5));
		System.out.println("Tchebysheff Theorem: " + test.getTchebysheffTheorem(30, 2, 20, 40));
		test.getUniformDistribution(20, 100);
	}

}
