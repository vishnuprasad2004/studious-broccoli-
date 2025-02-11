

public class EquationsSolver {
    
    
    public static void coFactors(int A[][], int temp[][], int p, int q, int n) {
        int i = 0;
        int j = 0;

        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                //excluding the row and column of element to find cofactor matrix of element
                if(row !=p && col !=q) {
                    //adding in temp
                    temp[i][j++] = A[row][col];

                    if(j== n-1) {
                        i++;
                    }
                }
            }
        }
    }

    //recursive way to find determinant
    public static float determinant(int A[][],int n) {
        int det = 0;
        if(n == 1) {
            return A[0][0];
        }
        int sign = 1;
        int temp[][] = new int[n][n];
        for(int fRow=0; fRow<n; fRow++) {
            coFactors(A, temp, 0, fRow, n);
            det += (float)sign*A[0][fRow]*determinant(A,n-1);
        }
        sign *= -1;
        
        return det;

    }

    public static void adjoint(int A[][], float adj[][],int n) {
        int sign = 1;
        int temp[][] = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                coFactors(A, temp, i, j, n);
                sign = ((i+j)%2 == 0)? 1 : -1;
                adj[j][i] = sign*determinant(temp, n-1);
            }
        }
    }

    
    public static void inverse(int A[][], float inverse[][]) { 
        // //A^-1 = adj(A)/det(A)
        // int n = A.length;
        // int det = determinant(A, n);
        // float adj[][] = new float[n][n];
        // adjoint(A, adj, n);

        // for(int i=0; i<n; i++) {
        //     for(int j=0; j<n; j++) {
        //         inverse[i][j] = (float)adj[i][j]/det;
        //     }
        // }

    }

    public static float[][] matMultiply(float A[][], int B[][]) {
        int r1 = A.length;
        //int r2 = B.length;
        int c1 = A[0].length;
        int c2 = B[0].length;

        float C[][] = new float[r1][c2];
        for(int i=0; i<r1; i++) {
            for(int j=0; j<c2; j++) {
                C[i][j] = 0;
                for(int k=0; k<c1; k++) {
                    C[i][j] += A[i][k]*(float)B[k][j];
                }
            }
        }
        
        return C; 
    }



    // public static float[][] solution(int A[][],int b[][],int n) {
    //     float sol = new float[n][n];
    //     float inv = new float[n][n];
    //     inverse(A, inv);
    //     sol = matMultiply(inv, b);
    //     return sol;
    // }

    public static void main(String[] args) {
        // int A[][] = {{7,-5},{-5,9}};
        // int b[][] = {{2},{-7}};
        // float X[][] = new float[2][2];
        // X = solution(A, b, 2);
        // for(int i=0;i<2;i++) {
        //     System.out.print(X[i][i]+" ");
        // }
    }
}
