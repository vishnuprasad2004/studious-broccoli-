import java.util.*;

public class StringsPractice {


    public static boolean isPalindrome(String ori) {
        String rev = "";
        for (int i=ori.length()-1; i>=0; i--) {
            rev += ori.charAt(i);
        }
        if(ori.equals(rev)) return true;
        else                return false;
    }

    public static float shortestPath(String path) { //GOOD QUESTION
        int x=0,y=0;          //WNEENESENNN distance = 5
        for (int i=0; i<path.length(); i++) {
            if(path.charAt(i)=='N') y++; //North
            else if(path.charAt(i)=='S') y--; //South
            else if (path.charAt(i)=='W') x--; //West
            else x++; //East
        }
        float distance = (float)Math.sqrt((x*x)+(y*y));
        return distance;
    }

    public static String subStrings(String str, int si, int ei) {
        String subString = "";
        for(int i=si;i<ei;i++) {
            subString =+ str.charAt(i);
        }
        return subString;
    }

    public static String toUpperCase(String str) {  //GOOD QUESTION 
        StringBuilder sb = new StringBuilder("");
        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);

        for(int i=1;i<str.length();i++) {
            if(str.charAt(i)==' ' && i < str.length()-1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
             
            }else{
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String stringCompression(String str) {
        //aaabbbcccccdd = a3b3c4d2
        StringBuilder sb = new StringBuilder("");

        for(int i=0;i<str.length();i++) {
            int count = 1;
            while(i<str.length()-1 && str.charAt(i)==str.charAt(i+1)) {
                count++;
                i++;
            }
            sb.append(str.charAt(i));
            if(count!=1) sb.append(count);
            
        }
        return sb.toString();
        
    }

    public static boolean areAnagram(String str1 , String str2) {  //GOOD QUESTION
        //as we dont want to compare for uppercase and lowercase separately
        str1.toLowerCase();
        str2.toLowerCase();
        char str1Array[] = str1.toCharArray();
        char str2Array[] = str2.toCharArray();
        if(str1Array.length == str2Array.length) {
            Arrays.sort(str1Array);
            Arrays.sort(str2Array);
            
            return Arrays.equals(str1Array, str2Array);
        }
        else{
            return false;
        }
    }

    public static int numOfLowerCase(String str) {
        int count =0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)>='a' && str.charAt(i)<='z') {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String fullname = "Tony Stark";
        String str = "hello world";
        String s = "abc";
        String s2 = "bca";
        System.out.println(areAnagram(s, s2));
        
        

        
    }    
}

