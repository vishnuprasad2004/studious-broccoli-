import java.util.*;


public class patterns2 {

    public static void hollowRectangle(int rows, int cols) {
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                
                //(i==5 && j==6 )//for any particular element int the matrix
                if(i==1 || i==rows || j==1 || j==cols || (i==5 && j==6 ) ){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void rotHalfPyramid(int n) {
        for(int i=1;i<=n;i++){
            //spaces
            for(int j=1;j<=n-i;j++){
                System.out.print("  ");
            }
            //stars
            for(int j=1;j<=i;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void invertedTriNum(int n) {
        for(int i=0;i<=n;i++){
            for(int j=1;j<=n-i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        
    }
    
    public static void floydsTri(int n) {
        int num=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++,num++){
                System.out.print(num+" ");
            }
            System.out.println();
        }
        
    }
   
    public static void zeroOneTri(int n) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                if((i+j)%2==0){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
   }
   
    public static void butterfly (int n) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(" *");
            }
            for(int j=1;j<=2*(n-i);j++){
                System.out.print("  ");
            }
            for(int j=1;j<=i;j++){
                System.out.print(" *");
            }  
            System.out.println();        
        }
        for(int i=n;i>=1;i--){
            for(int j=1;j<=i;j++){
                System.out.print(" *");
            }
            for(int j=1;j<=2*(n-i);j++){
                System.out.print("  ");
            }
            for(int j=1;j<=i;j++){
                System.out.print(" *");
            }  
            System.out.println();        
        }
        
    }   
   
    public static void solidRhombus(int rows,int cols) {
        for(int i=1;i<=rows;i++){
            for(int j=1;j<(rows-i+1);j++){
                System.out.print("  ");
            }
            for(int j=1;j<=cols;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }

    public static void hollowRhombus(int rows,int cols) {
        for(int i=1;i<=rows;i++){
            for(int j=1;j<(rows-i+1);j++){
                System.out.print("  ");
            }
            for(int j=1;j<=cols;j++){
                if(j==1 || j==cols || i==1 || i==rows){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        
    }

    public static void pascalTriangle(int num) {
        
        for(int n=1;n<=num;n++){
            for(int i=1;i<num-n+1;i++){
                System.out.print(" ");
                
                
            }
            int c=1;
            for(int r=1;r<=n;r++){
                System.out.print(c+" ");
                
                //c=c*(n-r+1)/r;
                c=c*(n-r)/r;

            }
            System.out.println();
        }
    }

    public static void printPascal(int n) {
        for(int line = 1; line <= n; line++) {
            
            int C=1;// used to represent C(line, i)
            for(int i = 1; i <= line; i++){
            // The first value in a line is always 1
                System.out.print(C+" ");
                C = C * (line - i) / i;
            }
            System.out.println();
        }
    }

    public static void diamondPattern(int n) {
        for(int i=1;i<=n;i++){
            for(int sp=1;sp<=n-i;sp++){
                System.out.print("  ");
            }
            for(int j=1;j<=2*i-1;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        for(int i=n;i>=1;i--){
            for(int sp=1;sp<=n-i;sp++){
                System.out.print("  ");
            }
            for(int j=1;j<=2*i-1;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }
    
    public static void main(String[] args) {
        //printPascal(10);
        //pascalTriangle(10);
        diamondPattern(10);
    }
    
}
