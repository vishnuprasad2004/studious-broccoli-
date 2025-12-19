
public class BinarySearch {

    // standard binary search
    public int search(int[] arr, int tar) {
        int n = arr.length;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if(arr[mid] == tar) return mid;
            else if(tar > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Find Minimum in Rotated Sorted Array
    public int findMin(int[] arr) {
        int n = arr.length;
        int low = 0;
        int high = n-1;
        int min = Integer.MAX_VALUE;
        while(low <= high) {
            // find the sorted half
            int mid = low + (high-low)/2;
            if(arr[low] <= arr[mid]) {
                min = Math.min(arr[low], min);
                low = mid + 1;
            } else {
                min = Math.min(arr[mid], min);
                high = mid - 1;
            }
        }

        return min;
    }

    // Single Element in a Sorted Array
    /*
    You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
    Return the single element that appears only once.
    */
    public int singleNonDuplicate(int[] arr) {
        int n = arr.length;
        if(n == 1) return arr[0];
        if(arr[0] != arr[1]) return arr[0];
        if(arr[n-2] != arr[n-1]) return arr[n-1];

        int low = 1, high = n-2;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if(arr[mid-1] != arr[mid] && arr[mid] != arr[mid+1]) return arr[mid];
            else { 
                if((mid % 2 != 0 && arr[mid-1] == arr[mid]) || (mid % 2 == 0 && arr[mid+1] == arr[mid])) low = mid + 1;
                else high = mid - 1;
            }
        }


        return -1;

    }

    // Search a 2D Matrix
    public boolean searchMatrix(int[][] mat, int tar) {
        int low = 0;
        int high = mat.length * mat[0].length -1;
        int n = mat[0].length;

        while(low <= high) {
            int mid = low + (high - low)/2;
            int midVal = mat[mid/n][mid%n];
            if(midVal == tar) return true;
            else if(midVal < tar)  {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } 
        
        return false;
    }

    public int searchInRotatedArray(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if(arr[mid] == target) return mid;
            // identify which half is sorted
            // if left part is sorted
            if(arr[low] <= arr[mid]) {
                if(arr[low] <= target && target < arr[mid]) {
                    high = mid-1;
                } else {
                    low = mid + 1;
                }
            } else {
                //right part is sorted
                if(arr[mid] < target && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        
    }
}
