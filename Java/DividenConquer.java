
class DividenConquer{

    public static void printArr(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
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
            temp[k] = arr[i++];
            k++;
        }
        //for the leftovers in the right 
        while(j<=ei) {
            temp[k] = arr[j++];
            k++;
        }
        //copying the arranged numbers in the original array
        for(k=0,i=si;k<temp.length;k++,i++) {
            arr[i] = temp[k];
        }
    }

    public static void main(String[] args) {
        int arr[] = {6,3,9,1,0,-7,4};
        mergeSort(arr, 0, arr.length-1);
        printArr(arr);
        
    }
}