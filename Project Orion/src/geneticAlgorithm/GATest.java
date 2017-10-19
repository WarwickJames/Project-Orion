package geneticAlgorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GATest {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		a.add(-11);
		a.add(-5);
		a.add(5);
		a.add(11);
		BufferedWriter bw = null;
		try { bw = new BufferedWriter(new FileWriter("output.txt")); } catch (IOException e) { e.printStackTrace(); }
		for( int g = 10; g < 101; g+=10 ) {
			for( int p = 10; p < 101; p+=10 ) {
				for( int mr = 0; mr < 11; mr++ ) {
					for( int mb = 1; mb < 20; mb+=2 ) {
						List<Integer> l = GA.solve(new Equation(new int[]{1,136,3025}, new char[]{'4','2','c'}, new char[]{'-','+'}), // Equation to solve
								   g, // Generations
								   new int[]{-11,11,5,-5,0}, // Initial settings
								   p, // Population
								   mr * 0.1, // Mutation rate
								   mb, // Bound on the range of mutations
								   a.size()); // Number of answers wanted
						Collections.sort(l);
						int score = 0;
						for( int i = 0; i < l.size(); i++ ) score += Math.abs(a.get(i) - l.get(i));
						String line = g + ", " + p + ", " + mr + ", " + mb + "     -->     " + score;
						try {bw.write(line); bw.newLine();} catch (IOException e) { e.printStackTrace();}
					}
				}
			}
		}
	}
}
