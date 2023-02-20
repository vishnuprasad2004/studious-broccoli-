#include<stdio.h>
#include<math.h>

int isPrime(int n,int i) {
    if(i==2) return 2;
    return (n%i==0) && isPrime(n,i-1);
}


void main(){
    int x,y;
    // printf("Enter 2 no.s:");
    // scanf("%d%d",&x,&y);
    // gcdFunction(x,y);
    x = isPrime(5,sqrt(5));
    y = isPrime(30,sqrt(30));
    printf("%d %d",x,y);
    printf("%d",0 && 1);

}



void gcdFunction(int x,int y){
    int i,gcd;
    // i=1;
    // z=(x>y)?y:x;
    // while(i<=z){
    //     if(x%i==0 && y%i==0){
    //         gcd=i; 
    //     }
    //     i++;
    // }
    i=(x<y)?x:y;
    while(i>0){
        if(x%i==0 && y%i==0){
            gcd=i;
            break;
        }
        i--;
    }
    printf("GCD: %d",gcd);
}