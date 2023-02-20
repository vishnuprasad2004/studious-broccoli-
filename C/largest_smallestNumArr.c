#include <stdio.h>

int largestNum(int arr[],int size) {
    int largest = -200;
    for(int i=0;i<size;i++) {
        largest = (arr[i]>largest)?arr[i]:largest;
    }
    return largest;
}

void main(){
    int arr[] = {3,7,9,2,89,1,43,1,67,100};
    printf("%d",largestNum(arr,10));

}