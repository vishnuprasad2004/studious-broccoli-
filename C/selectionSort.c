#include <stdio.h>

int main()
{
    int size=10;
    
    int arr[] = {2,5,8,1,5,8,6,10,20,0};
    for(int i=0;i<size-1;i++) {
        for(int j=i+1;j<size;j++) {
            if(arr[i] > arr[j]){
                //swap
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                
            }
        }
    }
    for(int k=0;k<size;k++) {
        printf("%d ",arr[k]);
    }

    return 0;
}