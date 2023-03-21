#include <stdio.h>

void add(int a,int b) {
    printf("The sum is %d",a+b);
}

void subtract(int a,int b) {
    printf("The difference is %d",a-b);
}

void multiply(int a,int b) {
    printf("The product is %d",a*b);
}

void division(int a,int b) {
    if(b==0) {
        printf("zeroDivisionError");
        return;
    }
    printf("The quotient is %d and remainder is %d",a-(a%b));
}



void main() {

    int a,b,ch;
    printf("Enter the 2 numbers:");
    scanf("%d%d",&a,&b);
    printf("1.Add\n2.Subtract\n3.Multiplication\n4.Division\n");
    printf("Enter your choice:");
    scanf("%d",&ch);
    switch(ch) {
        case 1:add(a,b);break;
        case 2:subtract(a,b);break;
        case 3:multiply(a,b);break;
        case 4:division(a,b);break;
        default:printf("Wrong Choice");break; 
    }
}