import java.util.*;

public class BubbleSort{
    public static void bubbleSort(int arr[]){
        for(int turn=0; turn < arr.length-1; turn++){
            for(int j=0; j < arr.length-1-turn; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void  displayArr(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println(" ");
    }
    public static void main(String arg[]){
        int num[] = {5,7,3,9,2,1,6,8,0,1};
        displayArr(num);
        bubbleSort(num);
        displayArr(num);


    }
}