package city;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cities")
@XmlAccessorType (XmlAccessType.FIELD)
public class Cities {
	
	@XmlElement(name = "city")
    private List<City> cities = null;
 
    public List<City> getCities() {
        return cities;
    }
 
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    public City getSmallestCity() {
    	return this.cities.stream().min((first, second) -> Double.compare(first.getPopulation(), second.getPopulation())).get();
    }
    
    public City getLargestCity() {
    	return this.cities.stream().max((first, second) -> Double.compare(first.getPopulation(), second.getPopulation())).get();
    }
    
    public List<City> getCitiesByPopulationAsc() {
    	return this.cities.stream().sorted(Comparator.comparingInt(City::getPopulation)).collect(Collectors.toList());
    }
    
    public String getCityNameById (String id) {
    	Optional<City> matchingObject = this.getCities().stream().
    		    filter(p -> p.getId().equals(id)).
    		    findFirst();
    	City airline = matchingObject.orElse(null);
    	return airline == null ? (String) "" :  airline.getName();
    }
    
}
