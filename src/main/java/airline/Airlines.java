package airline;

import java.util.List;
import java.util.Optional;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "airlines")
@XmlAccessorType (XmlAccessType.FIELD)
public class Airlines {

	@XmlElement(name = "airline")
    private List<Airline> airlines = null;
 
    public List<Airline> getAirlines() {
        return airlines;
    }
 
    public void setAirlines(List<Airline> airlines) {
        this.airlines = airlines;
    }
    
    public String getAirlineNameById (String id) {
    	Optional<Airline> matchingObject = this.getAirlines().stream().
    		    filter(p -> p.getId().equals(id)).
    		    findFirst();
    	Airline airline = matchingObject.orElse(null);
    	return airline == null ? (String) "" :  airline.getName();
    }
	
}
