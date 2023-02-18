import java.util.*;

public class ArrayPractice2 {
    
    public static boolean areDuplicates(int a[]) {
        
        for(int i=0; i<a.length;i++) {
            for(int j=i+1;j<a.length;j++) {
                if(a[i]==a[j]) { 
                    return true;
                }        
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,6,7,8,3,1};
        if(areDuplicates(arr)) 
            System.out.println("There are Duplicates.");
    }   

}
