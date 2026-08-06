public class SlidingWindow {
  /*
  1004. Max Consecutive Ones III
  Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
   */
  public int longestOnes(int[] nums, int k) {
    int maxLen = 0;
    int l = 0;
    int r = 0;
    int zeros = 0;
    while (r < nums.length) {
      if (nums[r] == 0)
        zeros++;
      while (zeros > k) {
        if (nums[l] == 0)
          zeros--;
        l++;
      }
      if (zeros <= k) {
        maxLen = Math.max(maxLen, r - l + 1);
      }
      r++;
    }
    return maxLen;
  }
  public static void main(String[] args) {
    
  }
}
