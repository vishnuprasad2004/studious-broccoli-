import java.util.*;

public class Matrices {

    public static void printMatrix(int matrix[][]) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print("|"+matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int a[][] ,int b[][]) {
        int row1 = a.length , row2 = b.length;
        int col1 = a[0].length , col2 = b[0].length;
        int c[][] = new int[col1][row2];
        for (int i = 0; i <col1; i++) {
            for (int j=0;j<row2;j++) {
                c[i][j] = 0;
                for(int k=0;k<row1;k++) {
                    c[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return c;
    }

    public static void printSpiral(int mat[][]) {
        int startRow = 0;
        int startCol = 0;
        int endRow = mat.length-1;
        int endCol = mat[0].length-1;
        while(startRow <= endRow && startCol <= endCol){
            //top
            for(int j=startCol;j<=endCol;j++) {
                System.out.print(mat[startRow][j]+" ");
            }
            //right
            for(int j=startRow+1; j<=endRow; j++) {
                System.out.print(mat[j][endCol]+" ");
            }
            //bottom
            for(int j=endCol-1;j>=startCol;j--) { 
                //IMPORTANT LINE for edge cases
                if(startRow == endRow) break;
                System.out.print(mat[endRow][j]+" ");
            }
            //left
            for(int j=endRow-1;j>startRow;j--){
                //IMPORTANT LINE for edge cases
                if(startCol == endCol) break; 
                System.out.print(mat[j][startCol]+" ");
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
    }

    public static int diagonalSum(int mat[][]) {
        int sum=0;
        // for(int i=0;i<mat.length;i++) {             0(n^2)
        //     for(int j=0;j<mat[0].length;j++) {
        //         if(i==j || (i+j)==mat.length-1) 
        //             sum += mat[i][j];
        //     }
        // }

        for (int i = 0; i < mat.length; i++) {     // 0(n)
            sum += mat[i][i];
            if(i != mat.length-i-1) sum += mat[i][mat.length-1-i];
        }
        return sum;
        
    }

    public static void staircaseSearch(int mat[][] , int key) {
        int row = 0, col = mat[0].length-1;

        while(row < mat.length && col>=0) {
            if(mat[row][col] == key){
                System.out.println("The key is at("+row+","+col+").");
                return;
            }else if(key > mat[row][col]){
                row++;
            }else{
                col--;
            }
        }
        System.out.println("Not Found");
        return;
        
    }


        


    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // for(int i=0;i<matrix.length;i++) {
        //     for(int j=0;j<matrix[0].length;j++) {
        //         matrix[i][j] = sc.nextInt();
        //     }
        // }
        
        int mat[][] = {{1,2,3},{4,5,1},{7,1,9}};
        int matrix[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        //System.out.println("The sum of Diagonals is:"+diagonalSum(mat));
        staircaseSearch(matrix, 11);
        



    }

    
}
