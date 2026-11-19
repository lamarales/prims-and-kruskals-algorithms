/*
 * Description:
 */
package algorithms;
/**
 * @author Luis Amarales
 */
import java.util.*;
public class Prim {
	
	public static double prim(double[][] points) {
		int n = points.length;
		boolean[] visited = new boolean[n];
		double[] minDistance = new double[n];
		Arrays.fill(minDistance, Double.POSITIVE_INFINITY);
		minDistance[0] = 0;
		double total = 0.0;
		
		//loops through all vertices
		for (int i=0; i<n; i++){
			int u = -1;
			double smallest = Double.POSITIVE_INFINITY;
			//find closest unused vertex
			for (int j = 0; j<n; j++) {
				if (!visited[j] && minDistance[j] < smallest) {
					smallest = minDistance[j];
					u = j;
				}
			}
			
			visited[u] = true;
			total = total + smallest;
			
			for (int j = 0; j < n; j++) {
				
				if (!visited[j]) {
					//calculates euclidean distance 
					double newDistance = Math.sqrt(
							Math.pow((points[u][0] - points[j][0]),2 )+
							Math.pow((points[u][1] - points[j][1]),2 )
							);
					if (newDistance < minDistance[j]) {
						minDistance[j] = newDistance;
					}
				}
			   
			}
			
		}
		
		return total;
	}
	

public static void main(String[] args) {

    Scanner kb = new Scanner(System.in);
    kb.useLocale(Locale.US);

    // test cases
    int testCases = kb.nextInt();

    // Loops through each test case
    for (int t = 0; t < testCases; t++) {

        //number of points
        int n = kb.nextInt();

        double[][] points = new double[n][2];

        // Stores X and Y
        for (int i = 0; i < n; i++) {
            points[i][0] = kb.nextDouble();
            points[i][1] = kb.nextDouble();
        }

        // Calculates the minimum spanning tree
        double result = prim(points);

        // Separates consecutive test cases
        if (t > 0) {
            System.out.println();
        }

        // Prints only MST length
        System.out.printf(Locale.US, "%.2f%n", result);
    }

    kb.close();
}

}