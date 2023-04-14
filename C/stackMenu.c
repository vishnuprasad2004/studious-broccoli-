#include <stdio.h>

static int stack[50];
int limit = 50, top = 0;

void push(int data) {
    if(top == limit) {
        printf("STACK OVERFLOW");
        return;
    }
    stack[++top] = data;
}

void pop() {
    if(top == 0) {
        printf("STACK UNDERFLOW");
        return;
    }
    stack[top--] = 0;
}

void display() {
    for(int i=1; i<=top; i++) {
        printf("%d ",stack[i]);
    }
    printf("\n");
}

void main() {
    char choice = 'y';
    while(choice == 'y' || choice == 'Y') {
        printf("1. Push\n2. Pop\n3. Display\n4. Peek");
        char oper;
        printf("\nEnter the operation(1-4):");
        scanf("%d",&oper);

        if(oper == 1) {

            int item;
            printf("Enter the item:");
            scanf("%d",&item);
            push(item);

        }else if(oper == 2) {

            pop();

        }else if(oper == 3) {

            display();

        }else if(oper == 4) {

            printf("Topmost Element in Stack: %d",stack[top]);

        }else {

            printf("Invalid Operation\n Try Again...");
        
        }
        char buffer;
        printf("\nDo you want to continue(y/n):");
        scanf("%c",&buffer);
        scanf("%c",&choice);

    }
}