/* A pair class to assist with building an Adjacency List. */
public class Pair{
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}