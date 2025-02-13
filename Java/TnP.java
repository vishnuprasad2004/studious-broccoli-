import java.io.*;
import java.util.*;

public class TnP {
    /*
     * Sarah eats 1 chocolate everyday and saves the wrappers in a container.
     * 7 wrappers -> 1 chocolate
     * Calculate the no of days she can eat chocolate/day.
     */
    public static int Question1(int ch, int w) {
        int days = ch;
        int wrappers = ch + w;
            // while (wrappers >= 7) { 
            //     int q = wrappers / 7;
            //     int r = wrappers % 7;
            //     days += q;
            //     wrappers -= q;
            //     wrappers += r;
            // }
        return days;
    }

    /*
     * DIFFICULT : Like trapping rainwater wala question
     * 
     */
    public static void Question2(int heights[]) {
        int n = heights.length;
        int maxArea = 0;
        for(int i=0;i<n-1;i++) {
            for(int j=i; j<n; j++) {
                int width = j-i;
                int height = Math.min(heights[i], heights[j]);
                int area = width * height;
                maxArea = Math.max(maxArea, area);
            }
        }

        System.out.println(maxArea);
    }

    public static void Question3(String str) {
        // char strArr[] = str.toCharArray();
        // Arrays.sort(strArr);
        // int i = 0;
        // int count = 0;
        // while(i < strArr.length-1) { 
        //     if (strArr[i] == strArr[i+1]) {
        //         i += 2;
        //     } else {
        //         count += 1;
        //         i++;
        //     }
        // }
        // if(count <= 1) {
        //     System.out.println("YES");
        // } else {
        //     System.out.println("NO");
        // }
        // char arr[] = str.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<str.length(); i++) {
            // if the char exists
            if(map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i))+1);
            } else {
                map.put(str.charAt(i),1);
            }
        }

        // if(map.containsValue(2)) {
        //     System.out.println("YES");
        // } else {
        //     System.err.println("NO");
        // }
    }


    public static int ratsNHouses(int r, int units, int arr[]) {
        if(arr.length == 0) return -1;

        int houses = 0;
        int totalunits = r * units;
        int sum =0;

        for(int i=0;i< arr.length;i++){
            sum += arr[i];
            houses++;
            if(sum >= totalunits) {
                return houses;
            }
        
        }
        if(sum < totalunits) {
            return 0;
        } else {
            return houses;
        }

    }

    public static void checkSum(int arr[], int target) {

    } 

    public static int buyNSellStocks(int prices[]) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (buyPrice < prices[i]) {
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(profit, maxProfit);
            } else {
                buyPrice = prices[i];
            }
        }

        return maxProfit;
    }

    public static int feedMonkeys(int n, int m, int p, int k, int j) {
        while (m >= k) {
            m = m - k;
            n--;
        }
        while (p >= j) {
            p = p - j;
            n--;
        }
        if (m > 0 || p > 0) {
            n--;
        }
        return n;
    }

    public static void strTokenizer() {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
        int c = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        switch (c) {
            case 1 ->
                System.out.println(a + b);
            case 2 ->
                System.out.println(b - a);
            case 3 ->
                System.out.println(a * b);
            case 4 ->
                System.out.println(a / b);
            default ->
                System.out.println("Invlaid value of c");
        }
        sc.close();
    }

    public static boolean checkPassword(String s) {
        int n = s.length();

        // Atleast length is 4
        // Must not have sapces or slashes
        if (n < 4 || !s.contains(" ") || !s.contains("/")) {
            return false;
        }
        if (Character.isDigit(s.charAt(0))) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasUppercase = false;
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                hasUppercase = true;
            }
            if (i != 0 && Character.isDigit(s.charAt(i))) {
                hasDigit = true;
            }
        }

        return true && hasDigit && hasUppercase;

    }

    // question 10.
    // scheme for income generation
    public static void commission() throws IOException {
        // Scanner sc = new Scanner(System.in);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String parent = sc.readLine();
        String choice = sc.readLine();
        if ("Y".equals(choice)) {
            System.out.print("Enter children:");
            String buf = sc.readLine();
            StringTokenizer st = new StringTokenizer(buf, ",");

            System.out.println("TOTAL MEMBERS:" + (st.countTokens() + 1));
            System.out.println("COMMISSION DETAILS");

            System.out.println(parent + ": " + (st.countTokens() * 500) + " INR");
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken() + ": 250 INR");
            }
        } else if ("N".equals(choice)) {
            System.out.println("TOTAL MEMBERS: 1");
            System.out.println("COMMISSION DETAILS");
            System.out.println(parent + ": 250 INR");
        } else {
            System.out.println("Invalid choice");
        }

        // sc.close();
    }

    public static int sumOfDigits(int num) {
        int sum = 0;
        while(num > 0) {
            sum = sum + num%10;
            num = num/10;
        }
        return sum;
    }

    public static int magicNumber(int N, int R) {
        int sum = 0;
        while(N > 0) {
            sum = sum + N%10;
            N = N/10;
        }

        sum = sum * R;
        
        if (sum > 9) {
            while(sum > 9) {
                sum = sumOfDigits(sum);
            }
            return sum;
        } else {
            return sum;
        }
    }

    //its actually a diamond shape
    /*
        Question from Hackerrank drive
    **.**
    *...*
    .....
    *...*
    **.**
     */
    public static void printTrapezium(int n) {
        //part 1
        for(int i=n; i>=1;i--) {
            for(int j=1; j<i; j++) {
                System.out.print("* ");
            }
            for(int j=0;j<(n-i+1)*2-1;j++) {
                System.out.print(". ");
            }
            for(int j=1; j<i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // part 2
        for(int i=1; i<n;i++) {
            for(int j=0; j<i; j++) {
                System.out.print("* ");
            }
            for(int j=(n-i)*2-1; j>=1; j--) {
                System.out.print(". ");
            }
            for(int j=0; j<i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }
    /*
        Question from Accenture Drive
        find if autobiographical number 
    */
    public static int findAutoCount(String s) {
        if (s.length() < 2) return 0;
        // first check if the number is autobiographical or not
        int zeroCount = Integer.parseInt(s.substring(0, 1));
        int oneCount = Integer.parseInt(s.substring(1, 2));
        int uniqueNumCount = 2;
        // HashMap<Integer,Integer> count = new HashMap<>();

        int z = 0;
        int o = 0;
        for (int i=0; i<s.length(); i++) {
            // System.out.println(Integer.parseInt(s.substring(i, i+1)));
            if(Integer.parseInt(s.substring(i, i+1)) == 0) {
                z++;
            } else if (Integer.parseInt(s.substring(i, i+1)) == 1) {
                o++;
            }
        }
    
        if(zeroCount == z && oneCount == o) {
            if (zeroCount != 0 && zeroCount != 1) {
                uniqueNumCount++;
            }
            if (oneCount != 0 && oneCount != 1) {
                uniqueNumCount++;
            }
            return uniqueNumCount;
        } else {
            return 0;
        }
    }

    /**
     * will the 2 kangaroos meet with start positions as x1, x2 and velocities as v1, v2
     * return "Yes" if they meet or "No" if they don't meet 
     */
    public static String willKangaroosMeet(int x1, int v1, int x2, int v2) {
        if (v1-v2 <= 0) return "No";
        int i = x1;
        int j = x2;
        while(i<=j) {
            if (i == j) {
                return "Yes";
            }
            i += v1;
            j += v2;
        }
        return "No";
    } 

    /**
     * Given the scores for the season, determine the number of times Maria breaks her record for most and least points scored during the season
     * show the number of changes in min score and max score
     */
    public static int[] breakingRecords(int scores[]) {
        int minmumScore = scores[0];
        int maximumScore = scores[0];
        int count[] = {0, 0};
        // i represents the game number
        for(int i=1; i<scores.length; i++) {
            // min score record
            if (minmumScore >= scores[i]) {
                minmumScore = scores[i];
                count[0]++;
            } else {
                maximumScore = scores[i];
                count[1]++;
            }
        }
        return count;
    } 

    public static void main(String[] args) throws IOException {
        // printTrapezium(3);
        // System.out.println(findAutoCount("1210"));
        int arr[] = {12,24,10,24};
        Scanner sc = new Scanner(System.in);
        System.out.println(breakingRecords(arr)[0]);
        System.out.println(breakingRecords(arr)[1]);

    }
}
