import java.util.*;

public class loopPrograms{
    public static int calculateFac(int num){
        int f=1;
        if(num==0 || num==1){
            return 1;
        }else{
            for(int i=num;i>1;i--){
                f*=i;    
            }
            return f;
        }
    }
    
    public static int calculateHcf(int x, int y) {
        int i = (x<y)?x:y;
        int hcf = 1;
        while(i>0){
            if(x%i==0 && y%i==0){
                hcf= i;
                break;
            }
            i--;
        }
        return hcf;
        
    }

    public static int calculateLcm(int x,int y) {
        int lcm = 1;
        int hcf = calculateHcf(x, y);
        lcm = (x*y)/hcf;
        return lcm;   
    }

    public static boolean isPrime(int n) {
        if(n==2){
            return true;
        }
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i==0){
                return false;
                
            }
            
        }
        return true;
    }

    public static int binToDecimal(int bin) {
        int dec =0;
        for(int pow=0; bin>0 ;pow++ ){
            //2^pow multipled by the last digit
            dec = dec + (bin%10)*(int)Math.pow(2,pow);
            bin=bin/10;
        }
        return dec;
    }

    public static int decToBinary(int dec) {
        int bin =0;
        for(int pow=0;dec>0;pow++){

            bin = bin + (dec%2)*(int)Math.pow(10,pow);
            dec = dec/2;
        }
        return bin;
        
    }

    public static int sumofDigits(int num) {
        int sum=0;
        for(int num2=num; num2>0 ;num2 = num2/10){
            int rem = num2%10;
            sum = sum + rem; 
        }
        return sum;
    }

    public static boolean isPalindrome(int num) {
        int num2 = num;
        int rev=0;
        for(;num2>0;rev=rev*10 + (num2%10), num2=num2/10);
        if(num==rev)
        return true;
        else
        return false;
        
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        

    }
}
