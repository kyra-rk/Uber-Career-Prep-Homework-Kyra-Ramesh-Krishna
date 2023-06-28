/* A QPair class to assist with building a Priority Queue. */
public class QPair{
    public String x;
    public int weight;

    public QPair(String x, int weight) {
        this.x = x;
        this.weight = weight;
    }

    public String toString(){
        return "(" + x + ", " + weight + ")";
    }

    public String getString(){
        return x;
    }

    public int getWeight(){
        return weight;
    }
}