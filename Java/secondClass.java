import java.util.*;

public class secondClass {
  
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the rows:");
        int rows = sc.nextInt();
        System.out.print("enter the cols:");
        int cols = sc.nextInt();
        int number[][] = new int[rows][cols];

        //input
        for (int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                number[i][j]= sc.nextInt();
            }
        }
        int x = sc.nextInt();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if (x==number[i][j]){
                    System.out.println("number found at location ("+i+","+j+")");
                }
                
            }
        }

        System.out.println();
        //output
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(number[i][j]+" ");
            }
            System.out.println();
        }

        
    }
    
}
