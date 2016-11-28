import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PerceptonTest {

	private Perceptron p;

	@Test	
	public void singleCenario() {
			double inputs[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
			int outputs[] = { 0, 0, 0, 1 };
			double threshold = 0.2;
			double learning_rate = 0.1;
			int epoch = 200;
			p.Train( inputs, outputs, threshold, learning_rate, epoch );
			assertEquals(p.Output( new double[] { 0, 0 } ) , 0 );
			assertEquals(p.Output( new double[] { 1, 0 } ) , 0 );
			assertEquals(p.Output( new double[] { 0, 1 } ) , 0 );
			assertEquals(p.Output( new double[] { 1, 1 } ) , 1 );
	}
	
	@Before
	public void init() {
		p = new Perceptron();
	}

}