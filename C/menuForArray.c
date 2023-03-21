#include <stdio.h>
/*
WAP for a menu for operations on array like deleteMiddle(), deleteFirst(), display() ,add()
*/
void display(int arr[],int n) {
    int i; 
    for(i=0;i<n;i++) {
        printf("%d ",arr[i]);
    }
    printf("\n\n");
}

void input(int arr[],int n) {
    printf("Enter the elements:");
    for(int i=0;i<n;i++) {
        scanf("%d",&arr[i]);
    }
    
}

void  deleteFirst(int arr[],int n) {
    for(int i=0;i<n-1;i++) {
        arr[i] = arr[i+1];
    }
}

void deleteMiddle(int arr[],int n) {
    int idx;
    printf("Enter the index to remove :");
    scanf("%d",&idx);
    for(int i=idx;i<n-1;i++) {
        arr[i] = arr[i+1];
    }
}

void add(int arr[],int n){
    int data;
    printf("Enter the number to add:");
    scanf("%d",&data);
    arr[n] = data;
}

static int arr[50];

void main() {
    int choice,n;
    printf("WELCOME!!!\n");
    printf("Enter the size of array:");
    scanf("%d",&n);
    while(1) {
        printf("=======================\n");
        printf("1. display\n2.input for size n\n3. delete first\n4. delete middle with some index\n5. add\n6. exit\n");
        printf("enter your choice(1-5):");
        scanf("%d",&choice);
        if(choice == 1) {
            display(arr,n);

        }else if(choice == 2) {
            input(arr,n);

        }else if(choice == 3) {
            deleteFirst(arr,n);
            n--;

        }else if(choice == 4) {
            deleteMiddle(arr,n);
            n--;

        }else if(choice == 5) {
            add(arr,n);
            n++;

        }else if(choice == 6) {
            break;

        }else {
            printf("Invalid Choice");

        }
        printf("\n");
    }
}