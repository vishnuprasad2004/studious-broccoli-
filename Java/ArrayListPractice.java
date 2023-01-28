import java.util.*;

public class ArrayListPractice {

    public static Random Rand = new Random(); 
    public static Scanner sc = new Scanner();

    public static int maximum(ArrayList<Integer> list) {
        int max = list.get(0);
        for(int i=1;i<list.size();i++) {
            max = Math.max(list.get(i),max);
        }
        return max;
    }

    public static ArrayList<Integer> randGenerate(int size,int limit) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<size; i++) {
            list.add(i,Rand.nextInt(limit));
        }
        return list;
    }

    public static ArrayList<Integer> inputGenerate(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<size; i++) {
            System.out.print("Enter the element:");
            int num = sc.nextInt();
            list.add(i,num);
            System.out.println();
        }
        return list;
    }



    public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx2);
        list.set(idx2,list.get(idx1));
        list.set(idx1, temp);
    }

    public static void main(String[] args) {
        
        ArrayList<Integer> list = randGenerate(10,10);
        
        System.out.println("List Size :"+list.size());
        System.out.println(list);
        swap(list, 2, 5);
        System.out.println(list);
        //System.out.println(Collections.sort(list));
        
    

    }
}