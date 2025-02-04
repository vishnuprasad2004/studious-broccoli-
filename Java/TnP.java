import java.io.*;
import java.util.*;

public class TnP {

    public void isDisarium(int n) {

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

    public static void main(String[] args) throws IOException {
        System.out.println(magicNumber(99, 3));
    }
}
