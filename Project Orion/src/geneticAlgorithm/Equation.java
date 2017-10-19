package geneticAlgorithm;

public class Equation {
	
	private int[] coefficients;
	private char[] variables;
	private char[] operators;

	public Equation(int[] coefficients, char[] variables, char[] operators) {
		this.coefficients=coefficients;
		this.variables=variables;
		this.operators=operators;
	}
	
	public int solve(int param) {
		int result = processVariable(variables[0], param);
		result *= coefficients[0];
		for( int i = 0; i < operators.length; i++ ) {
			int next = coefficients[i+1] * processVariable(variables[i+1], param);
			switch(operators[i]) {
				case '+' : result += next;
						   break;
				case '-' : result -= next;
						   break;
				case '*' : result *= next;
						   break;
				case '/' : result /= next;
						   break;
				default  : break;
			}
		}
		return result;
	}
	
	private int processVariable(char v, int param) {
		switch(v) {
			case 'l': return (int) Math.log(param);
			case 'e': return (int) Math.pow(Math.E, param);
			case 'c': return 1;
			default:  return (int) Math.pow(param, v - '0');
		}
	}
}
