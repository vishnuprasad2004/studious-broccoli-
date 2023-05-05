#include<stdio.h>

int binarySearch(int arr[10],int key) {
    
    int start = 0;
    int end = 10;
    while(start <= end){
        int mid = (start+end)/2;
        if(arr[mid]==key) {
            return mid;
        }
        else if(arr[mid]>key) { 
            end = mid-1;
        }else {
            start =mid +1;
        }
    }
    return -1;
}

void main() {

    int arr[] = {1,4,7,5,2,5,9,6,3,10};
    printf("%d is the index of key %d",binarySearch(arr,4),4);

}