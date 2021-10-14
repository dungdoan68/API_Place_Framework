package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	public static ResponseSpecification repSpec;
	public RequestSpecification requestSpec() throws FileNotFoundException {
	
		if(reqSpec==null) {
			PrintStream stream = new PrintStream(new FileOutputStream("logRequest.txt"));
			PrintStream streamResp = new PrintStream(new FileOutputStream("logResp.txt"));
			reqSpec = new RequestSpecBuilder()
					.setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key","qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(streamResp))
					.setContentType(ContentType.JSON)
					.build();
			return reqSpec;
		}
		
		return reqSpec;
	}
	
	public ResponseSpecification responseSpec() {
		repSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		return repSpec;
	}
	
	public Object getProperties(String keyValue) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(new File(""));
		p.load(fis);
		return p.get(keyValue);
	}
	
	public static String getJspath(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();
	}
}
