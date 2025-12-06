
import java.util.Arrays;
import java.util.Comparator;

public class Greedy {

	public static int fractionalKnapsack(int values[], int wt[], int W) {
		
		int profit = 0;
		double ratio[][] = new double[values.length][2];

		for(int i=0; i<values.length; i++) {
			ratio[i][0] = i;
			ratio[i][1] = values[i] / (double)wt[i];
		}

		Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

		for(int i=values.length-1; i>=0; i--) {
			int idx = (int)ratio[i][0];
			if(W >= wt[idx]) {
				W -= wt[idx];
				profit += values[idx];
			} else {
				profit += (ratio[i][1] * W);
				W = 0;
				break;
			}
		}

		return profit;
	}

	public static void activiySelection() {
		
	}

	public static void main(String[] args) {
		int values[] = {60, 100, 120};
		int wt[] = {10, 20, 30};
		int W = 50;
		System.out.println(fractionalKnapsack(values, wt, W));
	}
}
