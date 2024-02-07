
public class BackTracking {


    static int totalPermutations = 0;
    public static void printPermutations(String str, String ans) {
        if(str.length()==0) {
            System.out.println(ans);
            totalPermutations++;
            return;
        }

        for(int i=0;i<str.length();i++) {
            char curr = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i+1);
            printPermutations(newStr, ans+curr);

        }
    }


    public static void printSubsets(String str,String ans,int i) {
        if(i==str.length()) {
            System.out.println(ans);
            return;
        }
        printSubsets(str, ans+str.charAt(i), i+1);
        printSubsets(str, ans, i+1);
    }


//============== N Queens Problem ****=========================
    public static void printBoard(char board[][]) {
        System.out.println("====Board====");
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board.length;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean isSafe(char board[][],int row,int col) {
        //vertical up
        for(int i=row-1; i>=0; i--) { 
            if(board[i][col]=='Q') {
                return false;
            }
        }
        //left diagonal up
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--,j--) {
            if(board[i][j]=='Q') {
                return false;
            }
        }
        //right diagonal up
        for(int i=row-1,j=col+1; i>=0 && j<board.length; i--,j++) {
            if(board[i][j]=='Q') {
                return false;
            }
        }
        return true;
    }

    static int count=0;
    public static void nQueens(char board[][] , int row) {
        
        if(row == board.length) {
            //printBoard(board);
            count++;
            return;
        }

        for(int j=0;j<board.length;j++) {
            if(isSafe(board,row,j)) {
                board[row][j] = 'Q';
                nQueens(board, row+1);
                board[row][j] = 'x';
            }
            
        }

    }


//===================== Grid ways ****===========================
    
    static int ways = 0;
    public static void gridWays(char mat[][],int i,int j,int n,int m) {
        
        if(i==n-1 && j==m-1) {
            mat[i][j]='1';
            printBoard(mat);
            ways++;
            return;
        }
        if(i==n || j==m) {
            return;
        }
        mat[i][j] = '1';
        gridWays(mat,i+1,j,n,m);//down
        gridWays(mat, i,j+1,n,m);//right
        mat[i][j]='0';

    }

// Classic Questions

//===================== Question 1 Rat in a Maze =================

    public static void solveMaze(int maze[][]) {
        int n = maze.length;
        int sol[][] = new int[n][n];
        if(solution(maze,0,0,sol) == false) {
            System.out.println("solution doesn't exist.");
            return;
        }
        
        
    }

    public static boolean isSafe(int maze[][] ,int i, int j) {
        return (i>=0 && j>=0 && i < maze.length && j < maze.length && maze[i][j] == 1 );
    }

    public static boolean solution(int maze[][],int i,int j, int sol[][]) {
        if(i==maze.length-1 && j==maze.length-1) {
            sol[i][j]=1;
            printGrid(sol);
            return true;
        }
        
        if(isSafe(maze, i, j)) {
            if(sol[i][j]==1) return false;
            sol[i][j] = 1;
            if(solution(maze, i+1, j, sol)) return true; //down
            if(solution(maze, i, j+1, sol)) return true; //right
            sol[i][j] = 0;
            return false;
        }
        return false;

    }

    public static void printGrid(int sol[][]) {
        System.out.println("==== Grid ====");
        for(int i=0;i<sol.length;i++) {
            for(int j=0;j<sol.length;j++) {
                System.out.print(sol[i][j]+" ");
            }
            System.out.println();
        }
    }


//=================== Question 2 Keypad Combinations ================

    static char keypad[][] = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},
                              {'t','u','v'},{'w','x','y','z'}};

    public static void printCombinations(String num) {
        int length = num.length();
        if(length==0) {
            System.out.println("");
            return;
        }

        solution(num,"",0,length);
    }

    public static void solution(String num, String ans,int idx,int len) {
        if(idx == len) {
            System.out.println(ans);
            return;
        }

        char letter[] = keypad[Character.getNumericValue(num.charAt(idx))];
        for(int i=0;i<letter.length;i++) {
            String newAns = ans + letter[i];
            solution(num,newAns, idx+1, len);
            
        }
    }
    



    public static void main(String[] args) {
        int n = 4;
        char Board[][] = new char[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Board[i][j]='0';
            }
        }
        
        //gridWays(Board, 0, 0,n,n);
        //System.out.println("total ways:" + ways);
        int maze[][] = {{1,0,0,0},
                        {1,1,0,1},
                        {0,1,0,0},
                        {1,1,1,1}};
            
        //solveMaze(maze);
        printCombinations("2394");



    }

}