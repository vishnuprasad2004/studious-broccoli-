import java.util.*;

public class StackPractice {

    public static void printStack(Stack<Integer> s) {
        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    public static void pushAtBottom(int data,Stack<Integer> s) {
        if(s.empty()) {
            s.push(data);
            return;
        }   

        int top = s.pop();
        pushAtBottom(data, s);
        s.push(top);
    }

    public static String reverseStr(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            s.push(str.charAt(i));    
        }
        StringBuilder result = new StringBuilder("");
        while(!s.isEmpty()) {
            char curr = s.pop();
            result.append(curr);
        }

        return result.toString();
        
    }

    public static void reverseStack(Stack<Integer> s) {
        if(s.isEmpty()) {
            return;
        }

        int top = s.pop();
        reverseStack(s);
        pushAtBottom(top, s);
    }
 
    public static int maxArea(int heights[]) { // 0(n^2) Brute Force
        int maxArea = Integer.MIN_VALUE;
        for(int i=0; i<heights.length; i++) {
            int height = heights[i];
            int width = 1;
            int j = i+1;
            while (j < heights.length) {
                if(height <= heights[j]) {
                    width++;
                }
                j++;
            }
            j = i-1;
            while(j >= 0) {
                if(height <= heights[j]) {
                    width++;
                }
                j--;
            }
            int area = height * width;
            maxArea = Math.max(maxArea,area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        //printStack(s);
        // reverseStack(s);
        // printStack(s);

        int heights[] ={1,2,2};// {2,1,5,6,2,3};
        System.out.println(maxArea(heights));
       
    }
}