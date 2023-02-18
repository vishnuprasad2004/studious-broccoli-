
import java.util.*;

public class patterns1{

    public static void invertedTri(int n){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=(n-i+1);j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void circlePattern(int radius) {
        // dist represents distance to the center
        double dist;
        // for horizontal movement
        for (int i = 0; i <= 2 * radius; i++) {
        // for vertical movement
        for (int j = 0; j <= 2 * radius; j++) {
            dist = Math.sqrt((i - radius) * (i - radius) +
                             (j - radius) * (j - radius));
     
            // dist should be in the range (radius - 0.5)
            // and (radius + 0.5) to print stars(*)
            if (dist > radius - 0.5 && dist < radius + 0.5)
            System.out.print("* ");
            else
            System.out.print("  ");
        }
        System.out.print("\n");
        }
    }
        
    public static void numPyramid(int n) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        
    }

    public static void charPyramid(int n) {
        char ch='A';
        for(int i=1;i<=n;i++){
            for(int j=1 ;j<=i;j++,ch++){
                System.out.print(ch + " ");
            }
            System.out.println();
        } 
    // A  
    // B C
    // D E F 
    // G H I J K 

    }


    
    public static void main(String args[]){
        charPyramid(5);
        
        
    }
}