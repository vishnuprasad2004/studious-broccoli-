#include <stdio.h>
#include <string.h>


int main() {
        FILE* ptr = fopen("abc.txt", "r");
    if (ptr == NULL) {
        printf("no such file.");
        return 0;
    }
 
    /* Assuming that abc.txt has content in below
       format
       NAME    AGE   CITY
       abc     12    hyderabad
       bef     25    delhi
       cce     65    bangalore */
    char buf[100];
    while (fscanf(ptr, "%*s %s %*s ", buf) == 1)
        printf("%s\n", buf);
    return 0;
}