#include <stdio.h>
#include <math.h>

void main() {
    float height,weight,bmi;
    int unit;
    char ch = 'y';

    printf("================= BMI Claculator ===============\n\n");
    
    while(ch == 'y') {

        printf("1. Kilograms / Meters\n2. Pounds / Inches\nSelect a unit:");
        scanf("%d",&unit);

        if(unit == 1) {
            
            // units are in kilograms and metres
            printf("Enter your height(m):");
            scanf("%f",&height);
            printf("Enter your weight(kg):");
            scanf("%f",&weight);
            bmi = weight/(height * height);
            printf("Your Body Mass Index is: %d",bmi);
        
        }else if(unit == 2) {

            // units are in pounds and inches
            printf("Enter your height(inches):");
            scanf("%f",&height);
            printf("Enter your weight(lb):");
            scanf("%f",&weight);
            bmi = 703 * (weight / (height * height));
            printf("Your Body Mass Index is: %d",bmi);
        
        }else {

            printf("invalid unit index");
        }

        char buffer;
        printf("\nDo want to do it again(y/n):");
        scanf("%c",&buffer);
        scanf("%c",&ch);
    }
    

}