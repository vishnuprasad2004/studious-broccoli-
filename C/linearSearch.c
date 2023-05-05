#include <stdio.h>

int linearSearch(int arr[10],int key) {
    for(int i=0;i<10;i++) {
        if(arr[i] == key) return i;
    }
    return -1;
}

void main() {
    int arr[] = {1,4,7,5,2,5,9,6,3,10};
    printf("%d is the index of key %d",linearSearch(arr,4),4);
}