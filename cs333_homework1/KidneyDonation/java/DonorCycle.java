import java.io.*;
import java.util.*;

public class DonorCycle{
	public static boolean search(int start, boolean[][] graph){
		boolean[] searched = new boolean[graph[0].length];
		for (boolean search: searched){ //O(N)
			search = false;
		}
		int current = start;
		searched[current] = true;
		for(int i = 0; i < graph[0].length; i++){ //O(N^2)
			ArrayList<Integer> neighbours = findUnsearchedNeighbours(graph,current, searched); //O(N)
			if(!neighbours.isEmpty()) { //O(N)
				for (int neighbour : neighbours) { //O(N)
					searched[neighbour] = true;
					current = neighbour;
				}
			}
		}

		for(int i = 0; i < graph[0].length; i++){ //O(N)
			if(searched[i] && graph[i][start]){
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Integer> findUnsearchedNeighbours(boolean[][] graph, int node,  boolean[] searched){ //O(N)
		ArrayList<Integer> neighbours = new ArrayList<Integer>();
		for(int i = 0; i < graph[node].length; i++){ //O(N)
			if (graph[node][i] && !searched[i]){ //O(1)
				neighbours.add(i); //Add operations to the end of an ArrayList is O(1)
			}
		}
		return neighbours;
	}

	public static boolean isInCycle(int[][] matchScores, int[] donorFriends, int query){ //O(MN) *** This might also be O(N^2) but we assume M > N
		boolean[][] graph = new boolean[matchScores[0].length][matchScores[0].length];
		for(int i = 0; i< donorFriends.length; i++){ //O(M)
			for(int j = 0; j < matchScores[0].length; j++){ //O(N)
				if(matchScores[i][j]>60){ //O(1)
					graph[donorFriends[i]][j] = true;
				}
				else{ //O(1)
					graph[donorFriends[i]][j] = false;
				}
			}
		}
		return search(query,graph); //O(N^2)
	}

	public static void main(String[] args){

		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int m = s.nextInt();

		String donorsLine = s.next();
		String[] donorsArray = donorsLine.split(",",0);
		int[] donorsFriends = new int[donorsArray.length];
		for(int i=0; i<m; i++){
			donorsFriends[i] = Integer.parseInt(donorsArray[i]);
		}

		int[][] matchScores = new int[m][n];
		for(int i=0; i<m; i++){
			String matchscoreLine = s.next();
			String[] matchscoreArray = matchscoreLine.split(",",0);
			for(int j=0; j<n; j++){
				matchScores[i][j] = Integer.parseInt(matchscoreArray[j]);
			}	
		}
		int query = s.nextInt();
		System.out.println(isInCycle(matchScores, donorsFriends, query));
	}


}
