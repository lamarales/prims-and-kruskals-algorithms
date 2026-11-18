/*
 * Description:
 */
package algorithms;
/**
 *
 * @author Luis Amarales
 */

import java.util.ArrayList;


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
    	
    	double [][] points = {
    			{1, 1},
    			{2, 2},
    			{4, 1},
    	};
    	double result = kruskal(points);
    	
    	System.out.printf("%.2f%n", result);
	}
	
}