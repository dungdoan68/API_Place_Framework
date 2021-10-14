package stepDifinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Assert;

import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class Steps extends Utils {
	static Response response;
	static String place_id;
	TestData data = new TestData();
	APIResources apiRe ;
	@Given("add place form payload using {string} {string} {string}")
	public void add_place_form_payload_using(String name,String address, String language) throws FileNotFoundException {
		reqSpec = given().spec(requestSpec())
				.body(data.addPlcae(name, address, language));
		
	}
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String httpRequestMethod) {
		
		apiRe = APIResources.valueOf(resource);
		if(httpRequestMethod.equalsIgnoreCase("POST")) {
			response = reqSpec.request(Method.POST,apiRe.getResource());
		}
		else if (httpRequestMethod.equalsIgnoreCase("GET")) {
			response = reqSpec.request(Method.GET,apiRe.getResource());
			
		}
		else if (httpRequestMethod.equalsIgnoreCase("DELETE")) {
			response = reqSpec.request(Method.DELETE,apiRe.getResource());
			System.out.println(response.body().asPrettyString());
			
		}
		else if (httpRequestMethod.equalsIgnoreCase("UPDATE")) {
			response = reqSpec.request(Method.PUT,apiRe.getResource());
			
		}
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int expectedCode) {
		
	Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}

	@Then("Status in body is OK")
	public void status_in_body_is_ok() {
		assertEquals(getJspath(response, "status"), "OK");
	}
	
	@Then("Verify Place_Id map with {string} using {string}")
	public void verify_Place_Id_map_with_using(String expectedName, String resource) throws FileNotFoundException {
		place_id = getJspath(response, "place_id");
		apiRe = APIResources.valueOf(resource);
		reqSpec = given().spec(requestSpec()).queryParam("place_id",place_id);
				//.request(Method.GET,apiRe.getResource());
		user_call_with_http_request(resource, "GET");
		String actualName = getJspath(response, "name");
				assertEquals(actualName,expectedName);
				System.out.println(getJspath(response, "name"));
	}
	
	@Given("call delete place from payload")
	public void call_delete_place_from_payload() throws FileNotFoundException {
		System.out.println("Delete: "+place_id);
		reqSpec = given().spec(requestSpec()).body(data.deletePlace(place_id));
		
	
	}
}
