// technique: not sure to be honest
// time complexity: (n^2)
// space complexity: O(n)
import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {
    public static void main (String[] args){
        Pair a = new Pair(2, 3);
        Pair b = new Pair(4, 8);
        Pair c = new Pair(1, 2);
        Pair d = new Pair(5, 7);
        Pair e = new Pair(9, 12);
        ArrayList<Pair> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        System.out.print("Pairs: ");
        for (Pair pair : list) {
            System.out.print(pair.toString() + " ");
        }
        System.out.println("");
        ArrayList<Pair> output = mergeIntervals(list);
        System.out.print("Output: ");
        for (Pair pair : output) {
            System.out.print(pair.toString() + " ");
        }
        System.out.println("");

        a = new Pair(5, 8);
        b = new Pair(6, 10);
        c = new Pair(2, 4);
        d = new Pair(3, 6);

        list.clear();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        System.out.print("Pairs: ");
        for (Pair pair : list) {
            System.out.print(pair.toString() + " ");
        }
        System.out.println("");
        output = mergeIntervals(list);
        System.out.print("Output: ");
        for (Pair pair : output) {
            System.out.print(pair.toString() + " ");
        }
        System.out.println("");

        /*
        (third case -- yet to be tested)
        Input: [(10, 12), (5, 6), (7, 9), (1, 3)]
        Output: [(10, 12), (5, 6), (7, 9), (1, 3)]
         */
    }
    public static ArrayList<Pair> mergeIntervals(ArrayList<Pair> list){
        if (list.size() == 1) {
            return list;
        }
        int i = 0, j = i + 1;
        //System.out.println("size: " + list.size());
        while(i < list.size()) {
            while(j < list.size()) {
                if (list.get(i).intersects(list.get(j))) {
                    //System.out.println("in");
                    list.add(combine(list.get(i), list.get(j)));
                    list.remove(i);
                    list.remove(j);
                }
                else{
                    j++;
                }
            }
            i++;
            j = i + 1;
        }
        return list;
    }

    private static Pair combine(Pair a, Pair b) {
        int x = Math.min(a.x, b.x);
        int y = Math.max(a.y, b. y);
        return new Pair(x, y);
    }
}

class Pair {
    public int x;
    public int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean intersects(Pair other){
        if (this.x >= other.x && this.x <= other.y) {
            return true;
        }
        if (this.y >= other.x && this.y <= other.y) {
            return true;
        }
        return false;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}

// time taken: timed out! the second case is not working. not sure if I am on the right track