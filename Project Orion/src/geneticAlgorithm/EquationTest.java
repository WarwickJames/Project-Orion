package geneticAlgorithm;

import org.junit.*;

public class EquationTest {

	public static void main(String[] args) {
		test(new Equation(new int[]{1, 1}, new char[]{'1', 'c'}, new char[]{'+'}),
			 1,
			 2);
		test(new Equation(new int[]{1, 1}, new char[]{'3', '2'}, new char[]{'/'}),
				 3,
				 3);
		test(new Equation(new int[]{3, 2}, new char[]{'e', '2'}, new char[]{'*'}),
				 2,
				 168);
	}
	
	private static void test(Equation eq, int param, int expected) {
		Assert.assertEquals(eq.solve(param), expected);
	}

}
