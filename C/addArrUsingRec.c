#include <stdio.h>

// static int i = 0;
int add(int a[], int n,int i,int sum) {
    if(i >= n) {
        return sum;
    }
    return add(a, n, i+1, sum + a[i]);
}


void main() {
    int a[5] = {1,2,3,4,5};
    printf("%d",add(a,5,0,0));
}