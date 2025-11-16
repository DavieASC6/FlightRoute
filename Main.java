package dijkstrasAlgo;
import com.opencsv.CSVReader;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	    Graph graph = new Graph();
	    String dataset = "src\\dijkstrasAlgo\\FlightRouteUSData.csv";
	    double maxFare = 0.0;
        double maxDist = 0.0;
        double maxPass = 0.0;
        double fareMult = 0.5;
        double distMult = 0.45;
        double passMult = 0.05;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter f, d, or p (Fare, Distance, or Passenger) "
        		+ "to select a priority for the route. Or press enter for default calculation.");
        String mode = scan.nextLine().trim().toLowerCase();
        if(!mode.isEmpty()) {
        	switch(mode) {
            case "f":
                fareMult = 0.65; 
                distMult = 0.3; 
                passMult = 0.05; 
                break;
            case "d":
                fareMult = 0.3;
                distMult = 0.65;
                passMult = 0.05;
                break;
            case "p":
                fareMult = 0.33;
                distMult = 0.33;
                passMult = 0.33;
                break;
            default:
                System.out.println("Invalid mode, using default weights.");
        	
        	}
        	
        }
	    System.out.print("Enter origin airport ID: ");
	    String origin = scan.nextLine().trim();
	    System.out.print("Enter destination airport ID: ");
	    String dest = scan.nextLine().trim();
        try (CSVReader reader = new CSVReader(new FileReader(dataset))) {
            String [] row;
            
            while ((row = reader.readNext()) != null) {
                if (row[0].startsWith("tbl")) continue;
                if (row.length < 13) continue;

                double fare = parseOrDefault(row[13], 0.0);
                double dist = parseOrDefault(row[11], 0.0);
                double pass = parseOrDefault(row[12], 0.0);

                if (fare > maxFare) maxFare = fare;
                if (dist > maxDist) maxDist = dist;
                if (pass > maxPass) maxPass = pass;
                
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
	    try (CSVReader reader = new CSVReader(new FileReader(dataset))) {
	    	String [] row;
	        
	        while ((row = reader.readNext()) != null) {
	        	if (row[0].startsWith("tbl")) continue;
                if (row.length < 13) continue; 
	            
	            String airportID1 = row[7];
	            String airportID2 = row[8];

	            double fare = parseOrDefault(row[13], 0.0);
	            double dist = parseOrDefault(row[11], 0.0);
	            double pass = parseOrDefault(row[12], 0.0);
	            double edgeValue = fareMult * (fare / maxFare) + 
	            		distMult * (dist / maxDist) +
	                    passMult * (pass / maxPass);
	            graph.addEdge(airportID1, airportID2, edgeValue);
	            
	        }


	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        
	    }
	    Dijkstra dijikstra = new Dijkstra(graph);
	    dijikstra.findShortestPath(origin, dest);
	    scan.close();
	    
	}
    private static double parseOrDefault(String factor, double defaultV) {
        if (factor == null || factor.trim().isEmpty()) return defaultV;
        try {
            return Double.parseDouble(factor.trim());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultV;
            
        }
        
    }
    
}

