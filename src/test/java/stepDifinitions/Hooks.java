package stepDifinitions;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before
	public void beforScenario() throws FileNotFoundException{
		Steps s = new Steps();
		if(Steps.place_id==null) {
			s.add_place_form_payload_using("AHS","AHS Address","AHS Language");
			s.user_call_with_http_request("addPlace","POST");
			s.verify_Place_Id_map_with_using("AHS", "getPlace");
		}
		
	}
}
