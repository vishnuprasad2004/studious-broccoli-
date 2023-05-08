#include<stdio.h>
#include<stdlib.h>

struct Node {
    int data;
    struct Node* next;
};

struct Node* F = NULL;
struct Node* R = NULL;
int size = 0;

void display() {
    // if stack is empty
    if(F == NULL) {
        printf("\nStack is Empty");
        return;
    }
    struct Node* temp = F;

    printf("\n");
    while(temp != R) {
        printf("%d -> ",temp->data);
        temp = temp->next;
    }
    printf("%d -> ",temp->data);
    printf("NULL\n");
}


void insert(int info) {
    //creating a new node
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = info;
    if(F == NULL && R == NULL) {
    // for first element 
        F = newNode;
        R = newNode;
        F->next = NULL;
    }else{
    // for any other element
        R->next = newNode;
        newNode->next = NULL; 
        R = newNode;
    }
    printf("\nElement inserted");
    size++;
}

void delete() {
    // struct Node* previous = F;
  
    // struct Node* temp = previous->next;
    // R = previous;
    // previous->next = NULL;
    struct Node* temp = F;
    F = F->next;
    free(temp);
    printf("\nElement removed");
    size--;
}

void main() {
    insert(10);
    insert(20);
    insert(30);
    insert(40);
    display();
    delete();
    display();

}