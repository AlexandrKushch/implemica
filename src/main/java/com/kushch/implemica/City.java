package com.kushch.implemica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class City {
	private String name;	// city name
	private Map<Integer, Integer> nr_cost;		// nr - index of a city connected to current city
												// cost - the transportation cost
	
	public City(String name, Map<Integer, Integer> nr_cost) {
		super();
		this.name = name;
		this.nr_cost = nr_cost;
	}

	public static void main(String[] args) throws FileNotFoundException {
		List<City> cities = new ArrayList<City>();		// List of cities
		
		int s = 0;	// the number of tests <= 10
		int n = 0;	// the number of cities <= 1000
		int r = 0;	// the number of paths to find <= 100
		String source;	// NAME1
		String destination;	// NAME2
		
		System.setIn(new FileInputStream("paths.txt"));		// For tests, could be commented/deleted
		Scanner in = new Scanner(System.in);		// For scanning input information
		
		while(s <= 0 || s > 10) {		// While user don't input correct number, program won't continue 
			System.out.print("Tests(<= 10): ");
			s = in.nextInt();
		}
		
		for(int si = 0; si < s; si++) {	// Program repeats for every test
			while(n <= 0 || n > 10000) {
				System.out.print("Cities(<= 10 000): ");
				n = in.nextInt();
			}
			
			for(int i = 0; i < n; i++) {	// For every city we read information from user
				System.out.print("Name city: ");
				in.nextLine();		// Double reading cause of previously was "in.nextInt()"
				String name = in.nextLine();
				
				System.out.print("Number of Neighbors: ");
				int p = in.nextInt();	// the number of neighbors of city NAME
	
				Map<Integer, Integer> nr_cost = new HashMap<Integer, Integer>();
				
				for(int j = 0; j < p; j++) {	// Reading info about neighbors 
					System.out.print("nr: ");
					int nr = in.nextInt();
					
					System.out.print("cost: ");
					int cost = in.nextInt();
					
					nr_cost.put(nr, cost);
				}
				
				cities.add(new City(name, nr_cost));	// Adding the city(name, info about neighbors) in list 
			}
			
			while(r <= 0 || r > 100) {
				System.out.print("The number of paths(<= 100): ");
				r = in.nextInt();
			}
			in.nextLine();
			
			// Reading source & destination. Calculating the path of minimum cost
			for(int i = 0; i < r; i++) {
				System.out.print("Source: ");
				source = in.nextLine();
				
				System.out.print("Destination: ");
				destination = in.nextLine();
				
				System.out.println();
				System.out.println(MinPath(source, destination, cities));	// Printing result of calculating
			}
			
			in.close();
		}
	}
	
	public static int MinPath(String source, String destination, List<City> cities) {
		int index_source = FindCity(source, cities);
		int index_destination = FindCity(destination, cities);
		
		// if user want to find path from warszawa to gdansk
		if(index_source > index_destination) {
			index_source += index_destination;
			index_destination = index_source - index_destination;
			index_source = index_source - index_destination;
		}
		
		// By Dijkstra's algorithm. Array of min paths between cities.
		int u[] = new int[cities.size() - index_source];
		u[0] = 0;
		
		for(int i = index_source, ui = 1; i < index_destination; i++, ui++) {
			List<Integer> paths = new ArrayList<Integer>();	// All available paths from which we will find minimum cost.
			
			for(int j = index_destination; j >= 0; j--) {	// We will go from the end to start.
				// If current city has road to destination we'll remember cost. If not then we'll look for roads through other cities.
				if(cities.get(i).nr_cost.get(j + 1) == null) {
					continue;
				} else {	// To current cost we're adding costs which calculated before.
					paths.add(u[ui - 1] + cities.get(i).nr_cost.get(j + 1));
				}
			}
						
			// Finding minimum.
			int min = Integer.MAX_VALUE;	
			for(Integer p : paths) {
				if(p < min) {
					min = p;
				}
			}
			
			u[ui] = min;
		}
		
		return u[u.length - 1];
	}
	
	public static int FindCity(String name, List<City> cities) {
		for(int i = 0; i < cities.size(); i++) {	// Just walking by list till we find city which we searching for.
			if(cities.get(i).name.equals(name)) {
				return i;	// And return his index.
			}
		}
		return 0;
	}
}
