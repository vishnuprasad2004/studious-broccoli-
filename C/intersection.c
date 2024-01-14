#include <stdio.h>

void find_intersection(int a[], int b[], int n) {
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            if(a[i] == b[j]) {
                printf("%d ",a[i]);
            }
        }
    }
}


void main() {
    int a[11] = {1,2,3,4,5,6,7,8,19,0,0};
    int b[11] = {3,61,72,99,11,36,52,19,0,10,2};
    find_intersection(a, b, 11);

}
