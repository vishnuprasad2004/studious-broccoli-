
public class basicSorting {

    public static void printArray(int arr[]) {
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        
    }

    public static int[] bubbleSort(int arr[]) {
        for(int turn=0;turn<arr.length-1;turn++) {
            for(int j=0;j<arr.length-1-turn;j++) {
                if(arr[j]>arr[j+1]){
                    int t =arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }
        return arr;
        
    }

    public static int[] selectionSort(int arr[]) {
        for(int i=0; i<arr.length-1; i++) {
            int minPos = i;
            for(int j=i+1; j<arr.length; j++) {
                if(arr[minPos] > arr[j]){
                    minPos = j;
                }
            }
            int t = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = t;
        }
        return arr;
        
    }

    public static int[] insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>temp) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        return arr;
    }

    public static int[] countingSort(int arr[]) {
        int largest = ArrayPractice.largestNum(arr);
        
        int count[] = new int[largest+1];
        for(int i=0; i<arr.length; i++) {
            count[arr[i]]++;
        }       

        //sorting
        int j=0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]>0) {
                arr[j] = i;
                j++;
                count[i]--;
            }    
        }
        return arr;
    }

    public static void main(String[] args) {
        int a[] = {5,1,6,1,2,9};
        printArray(a);
        printArray(countingSort(a));
        



    }
    
}
