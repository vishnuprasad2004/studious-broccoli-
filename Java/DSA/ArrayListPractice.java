import java.util.*;

public class ArrayListPractice {

    public static Random Rand = new Random(); 
    public static Scanner sc = new Scanner(System.in);

    public static ArrayList<Integer> randGenerate(int size,int limit) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<size; i++) {
            list.add(Rand.nextInt(limit));
        }
        return (list);
    }

    public static ArrayList<Integer> inputGenerate(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<size; i++) {
            System.out.print("Enter the element:");
            int num = sc.nextInt();
            list.add(num);
            
        }
        return list;
    }

    public static int maximum(ArrayList<Integer> list) {
        int max = list.get(0);
        for(int i=1;i<list.size();i++) {
            max = Math.max(list.get(i),max);
        }
        return max;
    }

    public static int mostWater(ArrayList<Integer> list) { 
        int maxWater = 0;
        int lp =0;
        int rp = list.size()-1;
        while( lp < rp ) {
            int minHeight = Math.min(list.get(rp),list.get(lp));
            int waterArea = (rp-lp)*minHeight;
            maxWater = Math.max(maxWater,waterArea);
            if(list.get(lp)<list.get(rp) ) {
                lp++;
            }else{
                rp--;
            }
        }
        return maxWater;
    }

    public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx2);
        list.set(idx2,list.get(idx1));
        list.set(idx1, temp);
    }

    public static int mostWaterBruteForce(ArrayList<Integer> list) {
        //Brute Force  O(n^2)
        int maxWater = 0;
        for(int i=0; i<list.size(); i++) {
            for(int j=i+1; j<list.size(); j++ ) {
                int minHeight = Math.min(list.get(i),list.get(j));
                int waterArea = (j-i) * minHeight;
                maxWater = Math.max(maxWater,waterArea);

            }
        }
        return maxWater;
    }

    public static void pairSum1(ArrayList<Integer> list,int target) {
        int lp = 0;
        int rp = list.size()-1;
        while(lp<rp) {
            if(list.get(lp) + list.get(rp) == target) {
                System.out.println("(" + list.get(lp) + "," + list.get(rp) + ")");
            }else if(list.get(lp) + list.get(rp) < target) {
                lp++;
            }else {
                rp--;
            }
        }
        
    }

    public static void pairSum2(ArrayList<Integer> list,int target) {
        int n = list.size();
        int bp=-1;
        for(int i=0;i<n;i++) {
            if(list.get(i) > list.get(i+1)){
                bp = i;
                break;
            }
        }
        int lp = bp+1;
        int rp = bp;
        while(lp!=rp) {
            if(list.get(lp) + list.get(rp) == target) {
                System.out.println("(" + list.get(lp) + "," + list.get(rp) + ")");
                return;
            }else if(list.get(lp) + list.get(rp) < target) {
                lp = (lp+1)%n;
            }else {
                rp = (rp+n-1)%n;
            }
        }
        
    }
    

    public static void main(String[] args) {
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(15);
        list.add(6);
        list.add(8);
        list.add(9);
        list.add(10);

        System.out.println(list);
        pairSum2(list, 16);


      
        
        
    

    }
}