package smootherAndSalter_ApacheMathAndJFreeChartEdition;
import org.jfree.ui.RefineryUtilities;

public class Tester {

	public static void main(String[] args) {
		Chart c = new Chart("Smoother and Salter", "Smoother and Salter");
		c.pack();
		RefineryUtilities.centerFrameOnScreen(c);
	    c.setVisible(true);
	}
}
