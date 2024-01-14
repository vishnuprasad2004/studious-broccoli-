#include <stdio.h>


void bubble_sort(int a[], int n) {
    for(int i=0; i<n-1; i++) {
        for(int j=0; j<(n-i)-1; j++) {
            if(a[j] > a[j+1]) {
                int temp = a[j];
                a[j] = a[j+1];
                a[j+1] = temp;
            }
        }
    }
}

void main() {
    int a[10];
    for(int i=0;i<10;i++) {
        scanf("%d",&a[i]);
    }
    bubble_sort(a,10);
    for(int i=0;i<10;i++) {
        printf("%d ",a[i]);
    }

}