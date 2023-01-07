


public class SubArrays {

    public static void pairsofArray(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            for(int j=i+1;j<arr.length;j++) {
                System.out.print("("+arr[i]+","+arr[j]+") ");

            }
            System.out.println();
        }
        
    }

    public static void printSubarrays(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            for(int j=i;j<arr.length;j++) {
                for(int k=i ; k<=j ;k++) {
                    System.out.print(arr[k]+ " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        
    }
    
    //to find maximum sum in all sub arrays BRUTE FORCE CODE
    public static int maxInSubarrays(int arr[]) {
        int max = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++) {
            for(int j=i;j<arr.length;j++) {
                int sum=0;
                for(int k=i ; k<=j ;k++) {
                    sum = sum + arr[k];
                }
                if(sum>max) {
                    max = sum;
                }
            }
        }
        return max;
    }
    
    //to find maximum sum in all sub arrays using prefix method
    public static int maxInSubArr_PrefixMethod(int arr[]) {
        int max = Integer.MIN_VALUE;
        int prefix[] = new int[arr.length];
        //making the prefix array
        prefix[0] = arr[0];
        for(int i=1; i<prefix.length ; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }


        for(int i=0;i<arr.length;i++) {
            for(int j=i;j<arr.length;j++) {
                int sum=0;
                sum = (i==0) ? prefix[j] : prefix[j] - prefix[i-1];
                max = Math.max(sum,max);
            }
        }
        return max;
    }

    //IMPORTANT!!!
    public static int kadanes(int arr[]) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        if(areAllNegative(arr)){
            for(int i=0;i<arr.length;i++) {
                max = Math.max(arr[i],max);
            }
        }
        else{
            for(int i=0; i<arr.length; i++) {
                sum = sum +arr[i];
                if(sum<0) sum =0;
                max = Math.max(max,sum);
            }
        }
        return max;
    }

    public static boolean areAllNegative(int arr[]) {
        boolean isnegative = true;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]<0) isnegative = true;
            else         isnegative = false;
        }
        return isnegative;
    }




    public static void main(String[] args) {
        int arr[] = {1,-2,6,-1,3};
        System.out.println(maxInSubarrays(arr));
        System.out.println(maxInSubArr_PrefixMethod(arr));
        System.out.println(kadanes(arr));
    }
    
}
