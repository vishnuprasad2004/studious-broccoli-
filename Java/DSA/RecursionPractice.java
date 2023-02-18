import java.util.*;


public class RecursionPractice {

    public static void printDec(int n) {
        if(n==0) return;
        System.out.print(n+" ");
        printDec(n-1);
        
    }

    public static int factorial(int n) {
        if(n==0) return 1;
        // System.out.print(n*factorial(n-1)+"  ");
        // System.out.print(n);
        return n*factorial(n-1);
    }

    public static int sum(int n) {
        if(n==1) return 1;
        return n + sum(n-1);  
    }
    
    public static int fib(int n) {
        if(n==0 || n==1){
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
    
    public static boolean isSorted(int arr[],int i) {
        if(i==arr.length-1) return true;
        return arr[i]<arr[i+1] && isSorted(arr,i+1);
        
    }

    public static int pow(int x,int n) {
        if(n==1) return x;
        return x*pow(x,n-1);
    }

    public static int optimizedPow(int x,int n) {  //0(logn)
        if(n==1) return x;

        int halfpower = optimizedPow(x, n/2);
        int halfPowerSq = halfpower * halfpower;
        
        if(n%2!=0) halfPowerSq*=x;
        
        return halfPowerSq;
    }

    public static int tilingProblem(int n) {
        if(n==0 || n==1) return 1;
        //vertical choice
        int vertical = tilingProblem(n-1);
        //horizontal choice
        int horizontal = tilingProblem(n-2);
        return vertical+horizontal;
    }

    public static void removeDuplicates(String str, int idx, StringBuilder newStr , boolean map[]) {
        //base case
        if(idx==str.length()) {
            System.out.println(newStr);
            return;
        }    

        //Actual kaam
        char currChar = str.charAt(idx);
        if(map[currChar-'a']==true){
            removeDuplicates(str, idx+1, newStr, map);
        }
        else{
            map[currChar-'a']=true;
            removeDuplicates(str, idx+1, newStr.append(currChar), map);
        }
    }

    public static int friendsPairing(int n) {
        if(n==1 || n==0) return n;
        return friendsPairing(n-1) + friendsPairing(n-2)*(n-1);
    }

    //good question!!
    public static void printBinStrings(int n,String str,int lastPlace) {
        //base case
        if(n==0) {
            System.out.println(str);
            return;
        }

        //kaam
        printBinStrings(n-1,str+"0",0);
        if(lastPlace == 0) {
            printBinStrings(n-1, str+"1",1);
        }
    }

    public static String digits[] = {"zero","one","two","three","four","five","six","seven","eight","nine"};

    public static void yearsInStrings(int year) {
        //base cases
        if(year==0) return;
        //kaam
        yearsInStrings(year/10);
        System.out.print(digits[year%10]+" ");

    }

    public static void reverse(String str,int idx) {
        //base case
        if(idx==str.length()) {
            return;
        }
        //kaam 
        reverse(str, idx+1);
        System.out.print(str.charAt(idx)+" ");
        
    }
    
    public static int lenStr(String str) {
        if(str.length() == 0) {
            return 0;
        }
        return lenStr(str.substring(1))+1;
    }
    
    
    
    public static void main(String[] args) {
        int arr[] = {3,2,3,4,5};
        String str = "caar";
        yearsInStrings(1947);
        System.out.println(lenStr(str));
        
        
    }
}
