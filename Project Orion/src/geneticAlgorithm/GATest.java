package geneticAlgorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class GATest {

	public static void main(String[] args) {
		NavigableSet<Integer> a = new TreeSet<Integer>();
		a.add(-200);
		a.add(-67);
		a.add(-1);
		a.add(0);
		a.add(1);
		a.add(53);
		int gmin = 10, gmax = 100, dg = 20, pmin = 20, pmax = 100, dp = 10, mrmin = 0, mrmax = 10, dmr = 1, mbmin = 1, mbmax = 50, dmb = 5;
		BufferedWriter bw = null;
		try { bw = new BufferedWriter(new FileWriter("output.txt")); } catch (IOException e) { e.printStackTrace(); }
		Equation eq = new Equation(new int[]{1,214,752,710414,751,710200}, new char[]{'6','5','4','3','2','1'}, new char[]{'+','-','-','+','+'});
		Map<Integer, int[]> gens = new HashMap<Integer, int[]>();
		Map<Integer, int[]> pops = new HashMap<Integer, int[]>();
		Map<Integer, int[]> mrs = new HashMap<Integer, int[]>();
		Map<Integer, int[]> mbs = new HashMap<Integer, int[]>();
		List<Map<Integer, int[]>> maps = new ArrayList<Map<Integer, int[]>>();
		maps.add(gens);
		maps.add(pops);
		maps.add(mrs);
		maps.add(mbs);
		
		for( int g = gmin; g <= gmax; g+=dg ) {
			for( int p = pmin; p <= pmax; p+=dp ) {
				for( int mr = mrmin; mr <= mrmax; mr+=dmr ) {
					for( int mb = mbmin; mb <= mbmax; mb+=dmb ) {
						NavigableSet<Integer> l = GA.solve(eq, // Equation to solve
								   g, // Generations
								   new int[]{}, // Initial settings
								   p, // Population
								   mr * 0.1, // Mutation rate
								   mb, // Bound on the range of mutations
								   a.size()); // Number of answers wanted
						int score = 0;
						Iterator<Integer> ai = a.iterator();
						Iterator<Integer> li = l.iterator();
						while( li.hasNext() ) 
							score += Math.abs(ai.next() - li.next());
						
						String line = g + ", " + p + ", " + mr + ", " + mb + "     -->     " + score + " using: " + l.pollFirst();
						while( l.size() > 0 ) line += ", " + l.pollFirst();
						try {bw.write(line); bw.newLine();} catch (IOException e) { e.printStackTrace();}
						int[] current;
						int[] variables = new int[]{g, p, mr, mb};
						int i = 0;
						for( Map<Integer, int[]> m : maps ) {
							current = m.getOrDefault(variables[i], new int[]{0,0});
							current[0]++;
							current[1] += score;
							m.put(variables[i++], current);
						}
					}
				}
			}
		}
		System.out.println("====GenData====");
		for( int i = gmin; i <= gmax; i += dg ) {
			System.out.println(i + ": " + gens.get(i)[1] + " over " + gens.get(i)[0] + " = " + (gens.get(i)[1] / gens.get(i)[0]));
		}
		System.out.println("====PopData====");
		for( int i = pmin; i <= pmax; i += dp ) {
			System.out.println(i + ": " + pops.get(i)[1] + " over " + pops.get(i)[0] + " = " + (pops.get(i)[1] / pops.get(i)[0]));
		}
		System.out.println("====MrData====");
		for( int i = mrmin; i <= mrmax; i+=dmr ) {
			System.out.println(i + ": " + mrs.get(i)[1] + " over " + mrs.get(i)[0] + " = " + (mrs.get(i)[1] / mrs.get(i)[0]));
		}
		System.out.println("====MbData====");
		for( int i = mbmin; i <= mbmax; i += dmb ) {
			System.out.println(i + ": " + mbs.get(i)[1] + " over " + mbs.get(i)[0] + " = " + (mbs.get(i)[1] / mbs.get(i)[0]));
		}
	}
}
