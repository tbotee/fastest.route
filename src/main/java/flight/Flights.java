package flight;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;


@XmlRootElement(name = "flights")
@XmlAccessorType (XmlAccessType.FIELD)
public class Flights {
	
	@XmlElement(name = "flight")
    private List<Flight> flights = null;
 
    public List<Flight> getFlights() 
    {
        return flights;
    }
 
    public void setFlights(List<Flight> flights) 
    {
        this.flights = flights;
    }
    
    public List<Flight> getFlightsByAirline(String airlineId) 
    {
    	if (airlineId == "") {
    		return this.getFlights();
    	} else {
    		return (List<Flight>) this.flights.stream().filter(a -> a.getAirline().equals(airlineId)).collect(Collectors.toList());
    	}
    }
    
    public List<Flight> getFlightsFromDepartureToArrival(String departure, String arrival)
    {
    	List<Flight> result = new ArrayList<Flight>();
    	if (!StringUtils.isEmpty(departure) && !StringUtils.isEmpty(arrival))
    	{
    		result = (List<Flight>) this.flights.stream().filter(a -> a.getDeparture().equals(departure) && a.getArrival().equals(arrival)).collect(Collectors.toList());
    	}
    	return result;
    }
    
    public Flight getFlightFromDepartureToArrivalByAirline(String departure, String arrival, String airlineId)
    {
    	List<Flight> result = new ArrayList<Flight>();
    	if (!StringUtils.isEmpty(departure) && !StringUtils.isEmpty(arrival))
    	{
    		if (airlineId == "") {
    			result = (List<Flight>) this.flights.stream()
        				.filter(a -> 
        					a.getDeparture().equals(departure) && 
        					a.getArrival().equals(arrival))
        				.collect(Collectors.toList());
    		} else {
    			result = (List<Flight>) this.flights.stream()
    					.filter(a -> 
			    			a.getDeparture().equals(departure) && 
			    			a.getArrival().equals(arrival) && 
			    			a.getAirline().equals(airlineId))
    					.collect(Collectors.toList());
    		}
    	}
    	return result.get(0);
    }
    
    
}
