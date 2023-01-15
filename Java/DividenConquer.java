
class DividenConquer{

    public static void printArr(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    } 
    /* 
    class MergeSort {
        public static void mergeSort(int[] array) {
            if (array.length < 2) {
                return;
            }
            int middle = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, middle);
            int[] right = Arrays.copyOfRange(array, middle, array.length);
            mergeSort(left);
            mergeSort(right);
            merge(array, left, right);
        }
    
        public static void merge(int[] array, int[] left, int[] right) {
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }
            while (i < left.length) {
                array[k++] = left[i++];
            }
            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    }
    */

    public static void mergeSort(int arr[],int si,int ei) {
        if(si>=ei) {
            return;
        } 
        //can also be mid = (si+ei)/2 but its inefficient for longgg arrays
        int mid = si + (ei-si)/2; 
        mergeSort(arr, si, mid);    //left part
        mergeSort(arr, mid+1, ei);  //right part
        merge(arr,si,mid,ei);
    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei-si+1];
        int i = si;
        int j = mid+1;
        int k=0;
        //merge ka kaam
        while(i<=mid && j<=ei) {
            if(arr[i]<arr[j])  temp[k] = arr[i++];
            else temp[k] = arr[j++];

            k++;
        }
        //for the leftovers in the left
        while(i<=mid) {
            temp[k++] = arr[i++];
        }
        //for the leftovers in the right 
        while(j<=ei) {
            temp[k++] = arr[j++];
        }
        //copying the arranged numbers in the original array
        for(k=0,i=si;k<temp.length;k++,i++) {
            arr[i] = temp[k];
        }
    }

    public static void quickSort(int arr[],int start,int end) {
        if(start >= end) return;

        int pIdx = partition(arr,start,end);
        quickSort(arr,start,pIdx-1);
        quickSort(arr,pIdx+1,end);
    }

    public static int partition(int arr[],int start,int end) {
        int pivot = arr[end];
        int i = start-1;
        for (int j=start; j<end ; j++) {
            if(arr[j]<=pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = pivot;
        arr[end] = arr[i];
        arr[i] = temp;
        return i;
    }

    public static void main(String[] args) {
        int arr[] = {6,3,9,1,0,-7,4};
        quickSort(arr,0,arr.length-1);
        printArr(arr);
        
    }
}