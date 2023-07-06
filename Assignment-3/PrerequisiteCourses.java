/* Spec: Given a list of courses that a student needs to take to complete their major 
 * and a map of courses to their prerequisites, return a valid order for them to take 
 * their courses assuming they only take one course for their major at once.
 * Time taken: an hour
 * Data structure/stategy: Topological sort
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
 import java.util.*;
 public class PrerequisiteCourses{
    public static void main(String[] args){
        String[] classesA = {"A", "B", "C", "D", "E"};
        Map<String, String[]> mapA = new HashMap<>();
        String[] arrayA1 = {"B", "C"};
        mapA.put("D", arrayA1);
        String[] arrayA2 = {"A"};
        mapA.put("B", arrayA2);
        mapA.put("C", arrayA2);
        for (String item : mapA.keySet()){
            System.out.println(item + ": " + Arrays.toString(mapA.get(item)));
        }
        System.out.println("");
        System.out.println("Ordered List: " + Arrays.toString(preReqCourses(classesA, mapA)) + "\n");

        String[] classesB = {"A", "B", "C", "D", "E", "F"};
        Map<String, String[]> mapB = new HashMap<>();
        String[] arrayB1 = {"B", "D"};
        mapB.put("F", arrayB1);
        String[] arrayB2 = {"C", "E"};
        mapB.put("D", arrayB2);
        String[] arrayB3 = {"A"};
        mapB.put("B", arrayB3);
        mapB.put("C", arrayB3);
        for (String item : mapB.keySet()){
            System.out.println(item + ": " + Arrays.toString(mapB.get(item)));
        }
        System.out.println("");
        System.out.println("Ordered List: " + Arrays.toString(preReqCourses(classesB, mapB)) + "\n");

        String[] classesC = {"Intro to Programming", "Data Structures", "Advanced Algorithms", "Operating Systems", "Databases"};
        Map<String, String[]> mapC = new HashMap<>();
        String[] arrayC1 = {"Intro to Programming"};
        mapC.put("Data Structures", arrayC1);
        String[] arrayC2 = {"Data Structures"};
        // aka, Advanced Algorithms has a prerequisite of Data Structures 
        mapC.put("Advanced Algorithms", arrayC2);
        String[] arrayC3 = {"Advanced Algorithms"};
        mapC.put("Operating Systems", arrayC3);
        mapC.put("Databases", arrayC3);

        for (String item : mapC.keySet()){
            System.out.println(item + ": " + Arrays.toString(mapC.get(item)));
        }
        System.out.println("");
        System.out.println("Ordered List: " + Arrays.toString(preReqCourses(classesC, mapC)) + "\n");

        String[] classesD = {"Intro to Writing", "Contemporary Literature", "Ancient Literature", "Comparative Literature", "Plays & Screenplays"};
        Map<String, String[]> mapD = new HashMap<>();
        String[] arrayD1 = {"Intro to Writing"};
        mapD.put("Contemporary Literature", arrayD1);
        mapD.put("Ancient Literature", arrayD1);
        String[] arrayD2 = {"Ancient Literature", "Contemporary Literature"};
        mapD.put("Comparative Literature", arrayD2);
        mapD.put("Plays & Screenplays", arrayD1);

        for (String item : mapD.keySet()){
            System.out.println(item + ": " + Arrays.toString(mapD.get(item)));
        }
        System.out.println("");
        System.out.println("Ordered List: " + Arrays.toString(preReqCourses(classesD, mapD)) + "\n");
    }

    // assumes you input a non-cyclic, directed map
    public static String[] preReqCourses(String[] classes, Map<String, String[]> prereqs){
        // final list to return 
        String[] ordered = new String[classes.length];
        // map of every class and its indegree
        Map<String, Integer> indegree = new HashMap<>();
        // map of every class and the courses that depend on it 
        Map<String, ArrayList<String>> classList = new HashMap<>();
        // O(V)
        for (String c : classes){
            indegree.put(c, 0);
            classList.put(c, new ArrayList<>());
        }
        // O(V + E) to update all indegrees and classLists
        for (String item : prereqs.keySet()){
            String[] reliesOn = prereqs.get(item);
            indegree.replace(item, reliesOn.length);
            for (String curr : reliesOn){
                ArrayList<String> temp = classList.get(curr);
                temp.add(item);
                classList.replace(curr, temp);
            }
        }
        // make a queue of all courses with 0 prerecs 
        LinkedList<String> queue = new LinkedList<>();
        // O(V)
        for (String item : classes){
            // System.out.println("item: " + item + ", get(item): " + indegree.get(item));
            if (indegree.get(item) == 0){
                // add to end 
                queue.add(item);
                // System.out.println("zero indeg: " + item);
            }
        }
        String current;
        int ix = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            ordered[ix++] = current;
            // O(V + E)
            for (String tempClass : classList.get(current)){
                indegree.replace(tempClass, indegree.get(tempClass) - 1);
                if (indegree.get(tempClass) == 0) {
                    queue.add(tempClass);
                }
            }
        }
        return ordered;
    }
 }