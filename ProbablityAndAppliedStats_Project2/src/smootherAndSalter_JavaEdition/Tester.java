package smootherAndSalter_JavaEdition;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {

		ValueGenerator cs = new ValueGenerator();
		cs.writeXYSaltToCSV(100);
		cs.writeXSmoothSaltToCSV();
	}

}
