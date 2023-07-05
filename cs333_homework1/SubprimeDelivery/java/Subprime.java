import java.io.*;
import java.util.*;

public class Subprime{

    public static boolean allVisited(boolean[] visited){ //O(C)
        for(boolean visit: visited){ // O(C)
            if(!visit){
                return false;
            }
        }
        return true;
    }

    public static int findMinDistanceCity(ArrayList<Double> distances, boolean[] visited){ //O(C)
        double min = Double.POSITIVE_INFINITY;
        int min_city = -1;
        for(int i = 0; i < distances.size(); i++){ //O(C)
            if (!visited[i] && distances.get(i) < min){ //O(1)
                min = distances.get(i);
                min_city = i;
            }
        }
        if (min == Double.POSITIVE_INFINITY){
            min_city = -1;
        }
        return min_city;
    }

    public static ArrayList<Integer> findPath(ArrayList<Integer> prevCities, int dest ){ //O(C)
        ArrayList<Integer> citiesToPrint = new ArrayList<Integer>();
        citiesToPrint.add(dest); //O(1)
        int current = dest;
        while(prevCities.get(current) != -1){ //O(C)
            citiesToPrint.add(prevCities.get(current));
            current = prevCities.get(current);
        }
        return citiesToPrint;
    }



    public static void subprime_path(ArrayList<ArrayList<Pair>> capacities, ArrayList<ArrayList<Pair>> loads, int start, int end){ //O(CT)

        ArrayList<Double> distances = new ArrayList<Double>(loads.size());
        ArrayList<Integer> prevCities = new ArrayList<Integer>(loads.size());
        boolean[] visited = new boolean[capacities.size()];
        for (boolean node: visited) { //O(C)
            node = false;
        }
        for (int i = 0; i < capacities.size(); i++){ //O(T)
            distances.add(Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < capacities.size(); i++){ //O(T)
            prevCities.add(-1);
        }
        visited[start] = true;
        int current = start;
        distances.set(start,0.0);

        while(!allVisited(visited)){ //O(CT)
            int min_city = findMinDistanceCity(distances,visited); //O(C)
            double min;
            if(min_city == -1){
                min = Double.POSITIVE_INFINITY;
            }
            else{
                min = distances.get(min_city);
            }

            for(int i = 0; i < loads.get(current).size(); i++){ //O(T)
                boolean full = loads.get(current).get(i).second >= capacities.get(current).get(i).second; //O(1)
                if (!full && !visited[capacities.get(current).get(i).first]){ //O(1)
                    double pathCost = loads.get(current).get(i).second/(double)capacities.get(current).get(i).second + distances.get(current); //O(1)
                    if(pathCost < distances.get(loads.get(current).get(i).first)){ //O(1)
                        distances.set(loads.get(current).get(i).first,pathCost); //O(1)
                        prevCities.set(loads.get(current).get(i).first,current); //O(1)
                    }
                    if (min > pathCost){ //O(1)
                        min = pathCost;
                        min_city = loads.get(current).get(i).first; //O(1)
                    }
                }
            }
            if (min_city != -1){ //O(1)
                current = min_city;
                visited[current] = true;
            }
        }
        ArrayList<Integer> finalPath = findPath(prevCities,end); //O(C)
        for(int i = finalPath.size()-1; i >= 0; i--){ //O(C)
            System.out.println(finalPath.get(i));
        }
    }



    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        int c = s.nextInt();
        ArrayList<ArrayList<Pair>> capacities = new ArrayList<ArrayList<Pair>>();
        ArrayList<ArrayList<Pair>> loads = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i < c; i++){
            capacities.add(new ArrayList<Pair>());
            String nextline = s.next();
            if (nextline.startsWith(".")){continue;}
            String[] pairs = nextline.split(";",0);
            for (String p : pairs){
                String[] pair = p.split(",",0);
                int destination = Integer.parseInt(pair[0]);
                int weight = Integer.parseInt(pair[1]);
                capacities.get(i).add(new Pair(destination,weight));
            }
        }
        for (int i = 0; i < c; i++){
            loads.add(new ArrayList<Pair>());
            String nextline = s.next();
            if (nextline.startsWith(".")){continue;}
            String[] pairs = nextline.split(";",0);
            for (String p : pairs){
                String[] pair = p.split(",",0);
                int destination = Integer.parseInt(pair[0]);
                int weight = Integer.parseInt(pair[1]);
                loads.get(i).add(new Pair(destination,weight));
            }
        }
        int start = s.nextInt();
        int end = s.nextInt();
        subprime_path(capacities, loads, start, end);
    }

}

class Pair implements Comparable<Pair>{
    public int first;
    public int second;
    public Pair(int a1, int a2) {
        first = a1;
        second = a2;
    }

    public boolean equals(Pair other){
        return (this.first == other.first) && (this.second == other.second);
    }

    public int hashCode(){
        return this.first ^ this.second;
    }

    public int compareTo(Pair other){
        if (this.first != other.first){
            return this.first - other.first;
        }
        return this.second - other.second;
    }

    public String toString(){
        return "(" + this.first + "," + this.second + ")";
    }
}