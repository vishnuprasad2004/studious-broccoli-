#include<stdio.h>
#include<stdlib.h>

struct Node {
    int data;
    struct Node* next;
};

struct Node* top = NULL;
int size = 0;
int MAX = 10;

void display() {
    // if stack is empty
    if(top == NULL) {
        printf("\nStack is Empty");
        return;
    }
    struct Node* temp = top;

    printf("\n");
    while(temp != NULL) {
        printf("%d -> ",temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

void peek() {
    printf("Top value: %d", top->data);
}

void push(int info) {
    //overflow condition
    if(size >= MAX) {
        printf("Stack Overflow");
        return;
    }
    //creating a new node
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = info;
    if(top == NULL) {
    // for first element 
        top = newNode;
        top->next = NULL;
    }else{
    // for any other element
        newNode->next = top;
        top = newNode;
    }
    printf("\nElement added");
    size++;
}

void pop() {
    // underflow condition
    if(size == 0) {
        printf("Stack Underflow");
        return;
    }
    struct Node* temp = top;
    top = top->next;
    free(temp);
    printf("\nElement popped");
    size--;
}


void main() {
    push(40);
    push(30);
    push(20);
    push(10);
    display();
    pop();
    display();
}