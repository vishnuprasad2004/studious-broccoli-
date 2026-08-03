public class TwoPointers {

  // 1578. Minimum Time to Make Rope Colorful
  /**
   * Alice has n balloons arranged on a rope. You are given a 0-indexed string
   * colors where colors[i] is the color of the ith balloon.
   * 
   * Alice wants the rope to be colorful. She does not want two consecutive
   * balloons to be of the same color, so she asks Bob for help. Bob can remove
   * some balloons from the rope to make it colorful. You are given a 0-indexed
   * integer array neededTime where neededTime[i] is the time (in seconds) that
   * Bob needs to remove the ith balloon from the rope.
   * 
   * Return the minimum time Bob needs to make the rope colorful.
   */

  public static int minCost(String colors, int[] neededTime) {
    int ans = 0;
    int l = 0;
    for (int r = 1; r < colors.length(); r++) {
      if (colors.charAt(l) == colors.charAt(r)) {
        if (neededTime[l] < neededTime[r]) {
          ans += neededTime[l];
          l = r;
        } else {
          ans += neededTime[r];
        }
      } else {
        l = r;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    System.out.println(minCost("abaac", new int[]{1,2,3,4,5}));
  }
}
