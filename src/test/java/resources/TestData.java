package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.Location;
import pojo.Place;

public class TestData {
	Place p = new Place();
	public Place addPlcae(String name,String address, String language) {
		String[]types = {"spope", "tests"};
		List<String> a =  new ArrayList<String>();
		a.add("skcj");
		a.add("bssh");
		p.setAccuracy("50");
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("01234678");
		p.setTypes("a");
		Location l = new Location();
		l.setLat(30.256);
		l.setLng(50.25);
		p.setLocation(l);
		
		return p; 
	}
	public String deletePlace (String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
