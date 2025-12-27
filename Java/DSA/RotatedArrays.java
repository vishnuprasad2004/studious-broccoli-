
import java.util.Arrays;

public class RotatedArrays {

    // check if a given array is sorted in ascending order and rotated
    public static boolean isSorted(int arr[]) {
        // basically simulate that the whole array is twice the size and we are creating a sliding window
        int n = arr.length;
        int count = 1;
        if(n == 1) return true; // already sorted
        for(int i=0; i<n*2-1; i++) {
            if(arr[i%n] <= arr[(i+1)%n]) count++;
            else count = 1;

            if(count == n) return true;
        }

        return false;
    }

    // IMPORTANT
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static void rotateArray(int arr[], int k) {
        // brute force -> using extra space
        int n = arr.length;
        int temp[] = new int[n];
        for(int i=0;i<n; i++) {
            temp[i] = arr[(i+k)%n];
        }

        for(int i=0; i<n; i++) {
            arr[i] = temp[i];
        }

    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(arr));
        rotateArray(arr, 4);
        System.out.println(Arrays.toString(arr));
    }
}
