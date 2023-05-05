#include<stdio.h>

void alphaSort(char str[10]) {
    // int n = 10;
    for(int i=1; str[i]!='\0';i++) {
        for(int j=i+1;str[j]!='\0';j++) {
            if(str[i] > str[j]) {
                char temp = str[i];
                str[i] = str[j];
                str[j] = temp;
            }
        }
    }
}


void main() {
    char str[] = "rjsnvkodhs";
    // printf("%d",('a'>'b'));
    alphaSort(str);
    printf("%s",str);
}
