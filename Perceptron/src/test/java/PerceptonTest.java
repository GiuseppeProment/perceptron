import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * building boolean logic by using machine learning techniques
 */
public class PerceptonTest {

	private Perceptron p;

	@Test @Ignore	
	public void xorBehavior() {
			double inputs[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
			int outputs[] = { 0, 1, 1, 0 };
			trainPerceptron(inputs, outputs);
			assertResultMatch(inputs,outputs);
	}

	@Test	
	public void orBehavior() {
			double inputs[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
			int outputs[] = { 0, 1, 1, 1 };
			trainPerceptron(inputs, outputs); 
			assertResultMatch(inputs,outputs);
	}
	
	@Test	
	public void andBehavior() {
			double inputs[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
			int outputs[] = { 0, 0, 0, 1 };
			trainPerceptron(inputs, outputs);
			assertResultMatch(inputs,outputs); 
	}
	
	private void trainPerceptron(double[][] inputs, int[] outputs) {
		double threshold = 0.2;
		double learning_rate = 0.1;
		int epoch = 200;
		p.Train( inputs, outputs, threshold, learning_rate, epoch );
	}
	
	private void assertResultMatch(double[][] inputs, int[] outputs) {
		for (int i = 0; i < inputs.length; i++) {
			assertEquals(inputs[i][0]+","+inputs[i][1], outputs[i], p.Output( inputs[i] ) );
		}
	}
	
	@Before
	public void init() {
		p = new Perceptron();
	}

}