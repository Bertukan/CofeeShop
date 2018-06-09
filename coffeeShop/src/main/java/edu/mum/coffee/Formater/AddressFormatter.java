package edu.mum.coffee.Formater;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import edu.mum.coffee.domain.Address;

public class AddressFormatter implements Formatter<Address> {

	@Override
	public String print(Address object, Locale locale) {
       
          return String.format(locale, "%s, %s, %s, %s", object.getCity(), object.getCity(), object.getCountry(),object.getZipcode());
               
    }
	
	@Override
	public Address parse(String text, Locale locale) throws ParseException {
		 if (text != null) {
	            String[] parts = text.split(",");
	            
	                Address address = new Address();
	                address.setCity(parts[0].trim());
	                address.setState(parts[1].trim());
	                address.setCountry(parts[2].trim());
	                address.setZipcode(parts[3].trim());
	                return address;
	     
	        }
	        return null;
	    }
	}
	


