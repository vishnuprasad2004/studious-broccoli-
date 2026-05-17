
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

	public static void activiySelection(int[] start, int[] end) {
		int maxActivities = 0;
		ArrayList<Integer> ans = new ArrayList<>();
		
		maxActivities = 1;
		ans.add(0);
		int lastEnded = end[0];
		
		for(int i=1; i<end.length; i++) {
			if(start[i] >= lastEnded) {
				maxActivities++;
				ans.add(i);
				lastEnded = end[i];
			}
		}

		System.out.println("Max Activities:" + maxActivities);
		

	}

	/**
	 * Given a 2D array intervals[][], where intervals[i] = [starti, endi]. Find the minimum number of intervals need to be removed to make the rest of the intervals non-overlapping.
	 */
	public static void getNonOverlappingIntervals(int[][] intervals) {
		
		Arrays.sort(intervals, Comparator.comparingDouble((o) -> o[1]));
		int count = 1;
		int lastEnded = intervals[0][1];
		
		for(int i=1; i<intervals.length; i++) {
			if(intervals[i][0] >= lastEnded) {
				count++;
				lastEnded = intervals[i][1];
			}
		}

		System.out.println("Max Intervals: " + count);
	}


	public int[][] insertIntervals(int[][] intervals, int[] newInterval) {
			List<int[]> list = new ArrayList<>();
			int n = intervals.length;
			int i = 0;
			
			while(i<n && intervals[i][1] < newInterval[0]) {
					list.add(intervals[i]);
					i++;
			}

			while(i<n && intervals[i][0] < newInterval[1]) {
					newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
					newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
					i++;
			}
			list.add(newInterval);

			while(i<n) {
					list.add(intervals[i]);
					i++;
			}

			int[][] ans = new int[list.size()][2];

			for(int j=0; j<list.size(); j++) {
					ans[j] = list.get(j);
			}

			return ans;

	}


	public static void main(String[] args) {
		int values[] = {60, 100, 120};
		int wt[] = {10, 20, 30};
		int W = 50;
		System.out.println(fractionalKnapsack(values, wt, W));
	}
}
