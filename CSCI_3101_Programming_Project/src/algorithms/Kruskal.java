/*
 * Description:
 */
package algorithms;
/**
 *
 * @author Luis Amarales
 */

import java.util.ArrayList;
import java.util.*;

public class Kruskal {
	static 	class Edge {

	    int source;
	    int destination;
	    double weight;
	    
	    Edge(int source, int destination, double weight){
	    	this.source = source;
	    	this.destination = destination;
	    	this.weight = weight;
	    }
    }
	
	
	public static double kruskal(double[][] points) {
		
		ArrayList<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < points.length; i++) {
			for(int j = i +1; j < points.length; j++) {
				double distance = Math.sqrt(
						Math.pow((points[i][0] - points[j][0]),2 )+
						Math.pow((points[i][1] - points[j][1]),2 )
						);
				edges.add(new Edge(i, j, distance));
			}
		}
		edges.sort((a, b) -> Double.compare(a.weight, b.weight));
		int[] parent = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
		    parent[i] = i;
		}
	    double total = 0.0;
	    int edgesUsed = 0; 
	    
	    for (Edge edge : edges) {
	    	int rootSource = find(parent, edge.source);
	    	int rootDestination = find(parent, edge.destination);
	    	
	    	if (rootSource != rootDestination) {
	    		total = total + edge.weight;
	    		edgesUsed++;
	    		parent[rootSource] = rootDestination;
	    		if (edgesUsed == points.length - 1) {
	    		    break;
	    		}
	    	}
	    	
	    }
	    
		return total;
	}
	
	public static int find (int[] parent, int vertex) {
		if (parent[vertex] == vertex) {
			return vertex;
		}
		return find(parent, parent[vertex]);
	}
	
    public static void main(String[] args) {
    	Scanner kb = new Scanner (System.in);
    	System.out.println("How many points for Kruskal?:");
    	int n = kb.nextInt();
    	
    	double [][] points = new double[n][2];
    	
    	for (int i = 0; i < n; i++) {
    		
    		System.out.println("Enter X for point "+(i+1)+": ");
    		
    		points[i][0] = kb.nextDouble();
    		
    		System.out.print("Enter Y for point " + (i+1)+": ");
    		points[i][1] = kb.nextDouble();
    		
    	}
    	
    	
    	double result = kruskal(points);
    	
    	System.out.printf("Minimum spanning tree length: %.2f%n", result);
	kb.close();
    }
	
}