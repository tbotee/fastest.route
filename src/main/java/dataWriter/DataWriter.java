package dataWriter;

import airline.Airlines;
import city.Cities;
import city.City;
import trip.Trip;

public class DataWriter {
	
	public void displayTripDetails(Trip trip, Cities cities, Airlines airlines) {
		dispayText(String.format("\t%s:", airlines.getAirlineNameById(trip.getAirlineId())));
		if (trip.getFlights().size() == 0) {
			dispayText(String.format("\t\t%s:", "Nincs útvonal"));
		} else {
			trip.getFlights().forEach(flight -> {
				dispayText(String.format(
						"\t\t%s -> %s: %s", 
						cities.getCityNameById(flight.getDeparture()), 
						cities.getCityNameById(flight.getArrival()), 
						getFormattedTime(flight.getTime())));
			});
			displayAll(getFormattedTime(trip.getToatlTimeInminutes()));	
		}
		dispayText("");
	}
	
	public void displayTripDetailsForAnyAirline(Trip trip, Cities cities, Airlines airlines) {
		dispayText("Bármely légitársasággal a legrövidebb út:");
		trip.getFlights().forEach(flight -> {
			dispayText(String.format(
					"\t\t%s: %s -> %s: %s", 
					airlines.getAirlineNameById(flight.getAirline()),
					cities.getCityNameById(flight.getDeparture()), 
					cities.getCityNameById(flight.getArrival()), 
					getFormattedTime(flight.getTime())));
		});
		displayAll(getFormattedTime(trip.getToatlTimeInminutes()));	
	}
	
	private void displayAll(String formatedTime) {
		dispayText(String.format("\t\tÖsszesen: %s", formatedTime));	
	}
	
	public void displaySmallestCity(City city) {
		dispayText(String.format(
				"Legkisebb város: %s, %d lakó", 
				city.getName(), 
				city.getPopulation()));
	}
	
	public void displayLargestCity(City city) {
		dispayText(String.format(
				"Legnagyobb város: %s, %d lakó.", 
				city.getName(), 
				city.getPopulation()));
	}
	
	public void dispayText(String text) {
		System.out.println(text);
	}
	
	private String getFormattedTime(int timeInSeconds) {
		if (timeInSeconds < 60) {
			return String.format("%d perc", timeInSeconds);
		}
		int hour = (int) (timeInSeconds/60);
		int minutes = timeInSeconds - (hour * 60);
		return String.format("%d óra %d perc", hour, minutes);
	}
	
}
