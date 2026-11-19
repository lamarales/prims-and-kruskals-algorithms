/*
 * Description:
 * This Java program uses Kruskal’s algorithm to find the Minimum Spanning 
 * Tree (MST) by connecting points using the shortest possible total distance 
 * without creating cycles. It also validates user input and handles invalid 
 * entries.
 */
package algorithms;
/**
 *
 * @author Luis Amarales
 */

import java.util.*;

public class Kruskal {
	static 	class Edge {

	    int source;
	    int destination;
	    double weight;
	    //Creates an Edge object with source destination and weight
	    Edge(int source, int destination, double weight){
	    	this.source = source;
	    	this.destination = destination;
	    	this.weight = weight;
	    }
    }
	
	
	public static double kruskal(double[][] points) {
		
		ArrayList<Edge> edges = new ArrayList<>();
		//Iterates through all points and gets the distance of every unique pair of points
		for (int i = 0; i < points.length; i++) {
			for(int j = i +1; j < points.length; j++) {
				double distance = Math.sqrt(
						Math.pow((points[i][0] - points[j][0]),2 )+
						Math.pow((points[i][1] - points[j][1]),2 )
						);
				edges.add(new Edge(i, j, distance));
			}
		}
		//Sorts edges by weight from smallest to largest
		edges.sort((a, b) -> Double.compare(a.weight, b.weight));
		int[] parent = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
		    parent[i] = i;
		}
	    double total = 0.0;
	    int edgesUsed = 0; 
	    //Adds edges to the MST only if they do not create a circle
	    for (Edge edge : edges) {
	    	int rootSource = find(parent, edge.source);
	    	int rootDestination = find(parent, edge.destination);
	    	//Checks if roots are different
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
	//Recursively finds the root of the set containing a vertex
	public static int find (int[] parent, int vertex) {
		if (parent[vertex] == vertex) {
			return vertex;
		}
		return find(parent, parent[vertex]);
	}
	
	public static void main(String[] args) {
		//Creates scanner for user input
	    Scanner kb = new Scanner(System.in);

	    boolean validInput = false;
	    int n = 0;

	    // Validate number of points
	    while (!validInput) {
	        try {
	            System.out.print("How many points for Kruskal?: ");
	            n = kb.nextInt();

	            if (n > 0 && n <= 100) {
	                validInput = true;
	            } else {
	                System.out.println("Please enter an integer between 1 and 100.");
	            }

	        } catch (InputMismatchException e) {
	            System.out.println("Please enter an integer.");
	            kb.nextLine();
	        }
	    }

	    double[][] points = new double[n][2];

	    // Get X and Y coordinates
	    for (int i = 0; i < n; i++) {

	        // Validate X coordinate
	        validInput = false;

	        while (!validInput) {
	            try {
	                System.out.print("Enter X for point " + (i + 1) + ": ");
	                points[i][0] = kb.nextDouble();

	                validInput = true;

	            } catch (InputMismatchException e) {
	                System.out.println("Please enter a valid number for X.");
	                kb.nextLine();
	            }
	        }

	        // Validate Y coordinate
	        validInput = false;

	        while (!validInput) {
	            try {
	                System.out.print("Enter Y for point " + (i + 1) + ": ");
	                points[i][1] = kb.nextDouble();

	                validInput = true;

	            } catch (InputMismatchException e) {
	                System.out.println("Please enter a valid number for Y.");
	                kb.nextLine();
	            }
	        }
	    }
	    double result = kruskal(points);

	    System.out.printf("Minimum spanning tree length: %.2f%n", result);

	    kb.close();
	}
}
