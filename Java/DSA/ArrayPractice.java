
public class ArrayPractice {

    private static int linearSearch(int arr[], int key) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }

    public static int largestNum(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++) {
            if(largest < arr[i]) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static int smallestNum(int arr[]) {
        int smallest = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++) {
            if(smallest > arr[i]) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public static int binarySearch(int arr[], int key) {
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = (start+end)/2;
            if(arr[mid]==key) {
                return mid;
            }
            else if(arr[mid]>key) { //left
                end = mid-1;
            }else {
                start =mid +1;
            }
        }
        return -1;
    }

    public static void reverse(int arr[]) {
        int f=0 , l=arr.length-1;
        while(f<l) {
            //swap
            int temp = arr[f];
            arr[f] = arr[l];
            arr[l] = temp;
            f++;
            l--;
        }
    }

    //IMPORTANT Question to understand... Trapping Rainwater
    public static int TrappingRainwater(int arr[]) {
        int n = arr.length;

        //calculating the left max boundary aux. array
        int leftMax[] = new int[n];
        leftMax[0] = arr[0];
        for(int i=1 ; i<n; i++) {
            leftMax[i] = Math.max(arr[i],leftMax[i-1]);
        }

        //calculating the right max boundary aux. array
        int rightMax[] = new int[n];
        rightMax[n-1] = arr[n-1];
        for(int i=n-2 ;i>=0;i--) {
            rightMax[i] = Math.max(arr[i] , rightMax[i+1]);
        }

        int trappedRainwater = 0;

        //loop for individual height
        for(int i=0 ; i<n ; i++) {
            //waterLevel = min(left boundary , right boundary)
            int waterLevel = Math.min(leftMax[i] , rightMax[i]);
            //trapped water = waterLevel - height(arr[i])
            trappedRainwater += waterLevel - arr[i];
        }
        //return trapped water
        return trappedRainwater; 
    }

    public static int  buyAndSellStocks(int prices[]) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0 ; i<prices.length;i++) {
            if(buyPrice < prices[i]){
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(maxProfit , profit);
            }else{
                buyPrice = prices[i];
            }
        }
        return maxProfit;
        
    }

    public static void main(String[] args) {
        int arr[] = {1,-2,6,-1,3};
        int num[] = {-7,-2,-3,-6,-4,-1};
        int a[] = {/*0,1,0,2,1,2,0,2*/4,2,0,6,3,2,5};
        
        
        
        // System.out.println("max value is : " + SubArrays.kadanes(num)); 
        // System.out.println("max value is : " + SubArrays.kadanes(arr));
        System.out.println(buyAndSellStocks(a));
        

    }    
}
