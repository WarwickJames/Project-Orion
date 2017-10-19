package geneticAlgorithm;

import java.util.*;

public class GA {
	
	private static Random r = new Random();
	
	public static List<Integer> solve(Equation eq, int generations, int[] initial, int pop, double mut, int mutBound, int answers) {
		List<Integer> results = new ArrayList<Integer>();
		int[] chromosomes = new int[pop];
		PriorityQueue<int[]> fitness = new PriorityQueue<int[]>(pop, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		int i;
		for( i = 0; i < Math.min(initial.length, pop); i++ ) 
			chromosomes[i] = initial[i];
		for( ; i < pop; i++ ) 
			chromosomes[i] = i;
		
		while( generations > 0 ) {
			for( int j = 0; j < chromosomes.length; j++ ) 
				fitness.offer(new int[]{chromosomes[j], Math.abs(eq.solve(chromosomes[j]))});
			
			for( int j = 0; j < chromosomes.length; j++ ) {
				if( r.nextDouble() <= mut || fitness.peek() == null ) chromosomes[j] = r.nextInt(2 * mutBound) - mutBound;
				else chromosomes[j] = fitness.poll()[0];
			}
			fitness.clear();
			generations--;
		}
		
		for( int j = 0; j < chromosomes.length; j++ ) 
			fitness.offer(new int[]{chromosomes[j], Math.abs(eq.solve(chromosomes[j]))});
		
		for( int j = 0; j < chromosomes.length && results.size() < answers ; j++ ) {
			int[] f = fitness.poll();
			results.add(f[0]);
		}
		return results;
	}
}
