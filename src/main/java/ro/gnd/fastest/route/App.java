package ro.gnd.fastest.route;

import airline.Airlines;
import city.Cities;
import dataLoader.DataLoader;
import dataWriter.DataWriter;
import flight.Flights;
import trip.Trip;

public class App 
{
    
	public static void main( String[] args )
    {
		DataLoader dl = new DataLoader();
		DataWriter dw = new DataWriter();
		
		Flights flights = dl.loadFlights();
		Airlines airlines = dl.loadAirlines();
		Cities cities = dl.loadCities();

		dw.displaySmallestCity(cities.getSmallestCity());
		dw.displaySmallestCity(cities.getLargestCity());
		dw.dispayText("\nA legrövidebb út:");
		
        airlines.getAirlines().forEach(airline -> {
        	Trip trip = new Trip(cities, flights, airline.getId());
        	dw.displayTripDetails(trip, cities, airlines);
        });
        
        Trip trip = new Trip(cities, flights, "");
        dw.displayTripDetailsForAnyAirline(trip, cities, airlines);	
    	
    }

}
