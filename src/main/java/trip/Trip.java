package trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import city.Cities;
import city.City;
import dijkstra.DijkstraAlgorithm;
import dijkstra.Edge;
import dijkstra.Graph;
import dijkstra.Vertex;
import flight.Flight;
import flight.Flights;

public class Trip {
	
	private List<Vertex> nodes;
	
	private List<Edge> edges;
	
	private List<Flight> flights = new ArrayList<Flight>();
	
	private City Departure;
	
	private City Arrival;
	
	private int ToatlTimeInminutes = 0;
	
	private String airlineId;
	
	private Map<Integer, City> cityMap = new HashMap<Integer, City>();
	
	public Trip(Cities cities, Flights flights, String airlineId) {
		this.setAirlineId(airlineId);
		this.setDeparture(cities.getSmallestCity());
		this.setArrival(cities.getLargestCity());
		createGraph(cities, flights.getFlightsByAirline(airlineId));
		calculateFastestRoute(cities, flights);
	}
	
	private void createGraph(Cities cities, List<Flight> flights) {
		setNodes(new ArrayList<Vertex>());
		setEdges(new ArrayList<Edge>());
		createNodes(cities);
		createLanes(flights);
	}
	
	private void calculateFastestRoute(Cities cities, Flights flights) {
      Graph graph = new Graph(nodes, edges);
      DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
      dijkstra.execute(nodes.get(0));
      LinkedList<Vertex> path = dijkstra.getPath(nodes.get(cities.getCities().size()-1));
      
      if (path != null && path.size() > 0) {
    	  fillTripData(path, flights);
      }
	}
	
	private void fillTripData(LinkedList<Vertex> path, Flights flights) {
		IntStream.range(0, path.size()).forEach(i -> {
  		  if (i + 1 < path.size()) {
  			  Flight flight = getFlight(path.get(i), path.get(i+1), flights);
  			  this.flights.add(flight);
  			  int tt = calculateTotal(i, path.size(), flight);
  			  this.setToatlTimeInminutes(tt);
  		  }
  	  });
	}
	
	private int calculateTotal(int currentCityIndex, int tripCount, Flight flight) {
		if (currentCityIndex + 2 == tripCount) {
			return this.getToatlTimeInminutes() + flight.getTime();
		} 
		int time = flight.getTime();
		int returnValue = (int) (Math.ceil((double)time/60) * 60 + this.getToatlTimeInminutes());
		return returnValue;
	}
	
	private Flight getFlight(Vertex departure, Vertex arrival, Flights flights) {
		return flights.getFlightFromDepartureToArrivalByAirline(departure.getName(), arrival.getName(), this.getAirlineId());
	}
	
	private void createLanes(List<Flight> flights) {
		IntStream.range(0, flights.size()).forEach(i -> {
			Flight flight = flights.get(i);
			addLane("Edge_" + i, 
					getCityIdFromMap(flight.getDeparture()), 
					getCityIdFromMap(flight.getArrival()), 
					flight.getDistance()
			);
		});
	}
	
	private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
		edges.add(lane);
	}
	
	private int getCityIdFromMap(String cityName) {
		return this.getCityMap()
				.entrySet()
				.stream()
				.filter(entry -> Objects.equals(entry.getValue().getId(), cityName))
				.map(Map.Entry::getKey)
				.findFirst()
				.orElse(0);
	}
	
	private void createNodes(Cities cities) {
		IntStream.range(0, cities.getCitiesByPopulationAsc().size()).forEach(i -> {
			City city = cities.getCitiesByPopulationAsc().get(i);
			Vertex location = new Vertex(city.getId(), city.getId());
			nodes.add(location);
			cityMap.put(i, city);
		});
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public City getDeparture() {
		return Departure;
	}

	public void setDeparture(City departure) {
		Departure = departure;
	}

	public City getArrival() {
		return Arrival;
	}

	public void setArrival(City arrival) {
		Arrival = arrival;
	}

	public int getToatlTimeInminutes() {
		return ToatlTimeInminutes;
	}

	public void setToatlTimeInminutes(int toatlTimeInminutes) {
		ToatlTimeInminutes = toatlTimeInminutes;
	}

	public List<Vertex> getNodes() {
		return nodes;
	}

	public void setNodes(List<Vertex> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public Map<Integer, City> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Integer, City> cityMap) {
		this.cityMap = cityMap;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	
}
