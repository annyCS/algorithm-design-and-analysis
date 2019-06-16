package practicas;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Activities {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int n_cases = teclado.nextInt();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < n_cases; i++) {
			int n_activities = teclado.nextInt();
			Queue<Activity> queue = new PriorityQueue<Activity>();
			
			for (int j = 0; j < n_activities; j++) {
				int init = teclado.nextInt();
				int end = teclado.nextInt();
				
				Activity a = new Activity(init, end);
				queue.add(a);
			}
			
			result.add(resolveGreedyAlgorithm(queue));
		}
		
		printResult(result);
	}
	
	private static int resolveGreedyAlgorithm(Queue<Activity> queue) {
		int maxActivity = 0;
		int beforeEnd = 0;
		
		while ( !queue.isEmpty() ) {
			Activity a = queue.poll();
			
			if ( a.getInit() >= beforeEnd ) {
				maxActivity ++;
				beforeEnd = a.getEnd();
			}
		}
		
		return maxActivity;
	}
	
	private static void printResult(List<Integer> result) {
		for (int i: result) {
			System.out.println(i);
		}
	}

	public static class Activity implements Comparable<Activity> {
		private int init;
		private int end;
		
		public Activity(int init, int end) {
			this.init = init;
			this.end = end;
		}

		public int getInit() {
			return init;
		}

		public int getEnd() {
			return end;
		}

		@Override
		public int compareTo(Activity a) {
			if (this.end < a.end) return -1;
			if (this.end > a.end) return 1;
			
			return 0;
		}		
	}
}
