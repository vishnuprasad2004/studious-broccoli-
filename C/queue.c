#include<stdio.h>

int Q[5],limit=5;
int F=-1,R=-1;



void display(){
    if(F==-1 && R==-1) {
        printf("Queue is Empty");
        return;
    }
    printf("\nElements are:");
    // for(int i=F; i<=R+1; i=(i+1) % limit) {
    //     printf("%d ",Q[i]);
    // }
    int i = F;
    while(i!=R) {
        printf("%d ",Q[i]);
        i = (i+1) % limit;
    }
    printf("%d ",Q[i]);
    printf("\n");
}



void insert(int data) {
    if( (R+1)%limit == F) {
        printf("QUEUE OVERFLOW!!!\n");
        return;
    }
    if(F==-1 && R==-1){
        F=0,R=0;
    }   
    else{
        R = (R+1) % limit;
    }    
    
    Q[R] = data;

}


int delete() {
    if(F+1 == R || (F==-1 && R==-1)) {
        printf("QUEUE UNDERFLOW !!!\n");
        return 0;
    }
    int popped = Q[F];
    F = (F+1) % limit;
    return popped;
}



int main() {
    printf("WELCOME !!");
    int choice;
    while(1) {
        printf("\n1. Insert\n2. Display\n3. Deletion\n4. Exit");
        printf("\nEnter your Choice:");
        scanf("%d",&choice);

        if(choice == 1) {
            printf("\nInsert an Element...");
            int data;
            printf("\nEnter Element:");
            scanf("%d",&data);
            insert(data);
            printf("\nFront: %d \nRear: %d",F,R);

        }else if(choice == 2){
            display();
            printf("\nFront: %d \nRear: %d",F,R);

        }else if(choice == 3){
            printf("\nFront: %d \nRear: %d",F,R);
            printf("\nPopped Element:");
            printf(" %d ",delete());
            printf("\nFront: %d \nRear: %d",F,R);
        
        }else if(choice == 4){
            break;
        }else{
            printf("Invalid Operation");
        }
    }
    return 0;
}