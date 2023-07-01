/* Time taken: timed out after 40 min and still tried but my old solution got nowhere :( 
   I ended up finding a algorithm online and following it. 
 * Strategy used: Breadth First Search
 * Time Complexity: O(R*C) for Rows and Cols
 * Space Complexity: O(R*C) for Rows and Cols (the hash set)
 */

import java.util.*;

public class NumberOfIslands{

    public NumberOfIslands(){

    }

    class Vertex{
        int row;
        int col;
        public Vertex(int row, int col){
            this.row = row;
            this.col = col;
        }
        public String toString(){
            return "(" + row + ", " + col + ")";
        }
        public int getRow(){
            return row;
        }
        public int getCol(){
            return col;
        }
        @Override
        public boolean equals(Object obj){
            Vertex other = (Vertex) obj;
            if (this.row == other.getRow() && this.col == other.getCol()){
                return true;
            }
            return false;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }
    }

    public static void printMatrix(int[][] matrix){
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[0].length; col++){
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("");
        }
    }

    public int numberOfIslands(int[][] matrix){
        // first create an array to keep track of it a vertex was visited or not
        HashSet<Vertex> unvisited = new HashSet<>();
        // add all elements as univisted 
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[0].length; col++){
                unvisited.add(new Vertex(row, col));
            }
        }
        // maintain a queue to manage our breadth first search 
        LinkedList<Vertex> queue = new LinkedList<>();
        Vertex current, temp;
        int islands = 0;
        // start at one vertex and exhaust all adjacent vertices that are land (have a value of 1) 
        // restart until you have no more unvisited vertices 
        // keep a count of how many times you get a new unvisited vertex and return that count. this is the number of islands.
        while (!unvisited.isEmpty()){
            current = getUnvisited(unvisited);
            unvisited.remove(current);
            // if not land, then look for land
            while (current != null && matrix[current.row][current.col] == 0){
                unvisited.remove(current);
                current = getUnvisited(unvisited);
            }
            // add to the back of queue
            if (current != null){
                queue.add(current);
                islands++;
            } else {
                break;
            }
            while (!queue.isEmpty()){
                // get head of queue
                current = queue.poll();
                // visit the current vertex's neighbours 
                int row, col;
                for (int i = -1; i <= 1; i++){
                    row = current.row + i;
                    for (int k = -1; k <= 1; k++){
                        if (Math.abs(k) + Math.abs(i) > 1){
                            break;
                        }
                        col = current.col + k;
                        temp = new Vertex(row, col);
                        // if this neighbour is unvisited then visit it and remove it from the unvisited set
                        if (unvisited.contains(temp)){
                            unvisited.remove(temp);
                            System.out.println("curr: " + matrix[row][col] + ", unvisited: " + unvisited.size() + ", islands: " + islands);
                            // if that vertex is land, then we want to look for adjacent land connected to it -- add it to the queue
                            if (matrix[row][col] == 1){
                                queue.add(temp);
                            }
                        } 
                    }
                }
            }
            // our queue is empty! this means there was no more adjacent land to look at. so look for unvisited vertices
        } 
        return islands;
    }

    public static void printUnvisited(Set<Vertex> unvisited){
        Iterator<Vertex> itr = unvisited.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next().toString() + " ");
        }
        System.out.println("");
    }


    public static Vertex getUnvisited(Set<Vertex> unvisited){
        Iterator<Vertex> itr = unvisited.iterator();
        if (itr.hasNext()){
            return itr.next();
        } else {
            return null;
        }
    }

    public int solnNumOfIslands(int[][] matrix){
        int islands = 0;
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        Set<Vertex> visited = new HashSet();

        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                // if it is an island and has not been visited
                // then look for an island
                if (matrix[row][col] == 1 && !visited.contains(new Vertex(row, col))){
                    findIsland(matrix, visited, row, col, numRows, numCols);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void findIsland(int[][] matrix, Set<Vertex> visited, int row, int col, int numRows, int numCols){
        // add this cell to visited set 
        visited.add(new Vertex(row, col));

        // neighbours are left, right, top, down 
                          // {row, col}
        int[][] neighbours = {{0, -1}, {0, 1}, {-1,0}, {1,0}};
        for (int[] n : neighbours){
            // if row/col is within bounds, the neighbour is visited, and that cell is not visited
            // then look for it's nearby islands
            int r = row + n[0];
            int c = col + n[1];
            if (0 <= r && r < numRows && 0 <= c && c < numCols && matrix[r][c] == 1 && !visited.contains(new Vertex(r, c))){
                findIsland(matrix, visited, r, c, numRows, numCols);
            } 
        }
    }

    public static void main(String[] args){
        NumberOfIslands tester = new NumberOfIslands();
        int[][] matrix1 = {
            {1, 0, 1},
            {0, 1, 1}
        }; // 2 islands 
        System.out.println("Matrix 1:");
        printMatrix(matrix1);
        System.out.println("Islands: " + tester.solnNumOfIslands(matrix1));

        System.out.println("\nMatrix 2:");
        int[][] matrix2 = {
            {1, 0, 1, 1, 1}, 
            {1, 1, 0, 1, 1}, 
            {0, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 0}, 
            {0, 0, 0, 0, 0}}; // 3 islands 
        printMatrix(matrix2);
        System.out.println("Islands: " + tester.solnNumOfIslands(matrix2));

        System.out.println("\nMatrix 3:");
        int[][] matrix3 = {
            {1, 1, 0, 0, 0}, 
            {1, 1, 0, 0, 0}, 
            {0, 0, 1, 0, 0}, 
            {0, 0, 0, 1, 1}}; // 3 islands 
        printMatrix(matrix3);
        System.out.println("Islands: " + tester.solnNumOfIslands(matrix3));

        System.out.println("\nMatrix 4:");
        int[][] matrix4 = {
            {1, 1, 1, 1, 0}, 
            {1, 1, 0, 1, 0}, 
            {1, 1, 1, 0, 0}, 
            {0, 0, 0, 0, 0}}; // 1 island
        printMatrix(matrix4);
        System.out.println("Islands: " + tester.solnNumOfIslands(matrix4));
    }
}