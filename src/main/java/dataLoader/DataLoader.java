package dataLoader;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import airline.Airlines;
import city.Cities;
import flight.Flights;

public class DataLoader {

	public Cities loadCities()
	{
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(Cities.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    Unmarshaller jaxbUnmarshaller = null;
		try {
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    
	    try {
			return (Cities) jaxbUnmarshaller.unmarshal( new File("resources/cities.xml") );
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public Flights loadFlights()
	{
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(Flights.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    Unmarshaller jaxbUnmarshaller = null;
		try {
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	    try {
			return (Flights) jaxbUnmarshaller.unmarshal( new File("resources/flights.xml") );
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public Airlines loadAirlines()
	{
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(Airlines.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    Unmarshaller jaxbUnmarshaller = null;
		try {
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	     
	    try {
			return (Airlines) jaxbUnmarshaller.unmarshal( new File("resources/airlines.xml") );
		} catch (JAXBException e) {
			e.printStackTrace();
		}  
	    return null;
	}
	
}
