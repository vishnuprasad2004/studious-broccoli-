import java.util.AbstractList;
import java.util.Arrays;

class StaticList<T> extends AbstractList<T> {
    private T[] list;
    private int size = 0;
    private int top = 0;

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public StaticList() {
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
    }

    @SuppressWarnings("unchecked")
    public StaticList(int size) {
        this.list = (T[]) new Object[size];
        this.size = size;
    }

    public int size() {
        return size;
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if(index < size && index >= 0) return this.list[index];
        else throw new ArrayIndexOutOfBoundsException("Trying to access an element out of the set size");
    }

    @Override
    public boolean add(T item) {
        if (top + 1 == size) return false;
        this.list[top++] = item;
//        System.out.println(top);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    public T remove() {
        return this.list[top--];
    }

    @Override
    public boolean contains(Object item) {
//        for (int i = 0; i < top && i < size; i++) {
//            if(this.list[i].equals(item)) return true;
//        }
//        return false;
        return super.contains(item);
    }

    @Override
    public T set(int index, T newValue) throws RuntimeException {
        if(index < size && index >= 0 && index <= top) list[index] = newValue;
        else throw new RuntimeException("This method is only used to edit existing elements");
        return newValue;
    }


    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return Arrays.toString(list);
    }

    public void printList() {
        System.out.print("[");
        for(int i=0; i<top-1 && i<size; i++) {
            System.out.print(this.list[i].toString() +  ", ");
        }
        System.out.print(this.list[top-1].toString() + "]");
        System.out.println();
    }
}

public class Generics {

    static void main() {
        StaticList<Integer> arr = new StaticList<>(20);
        arr.add(10);
        arr.add(20);
        arr.set(3, 26);
        System.out.println(arr.toString());
        System.out.println(arr.contains(30));
        arr.printList();
    }
}
