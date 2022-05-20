package activities;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class Activity1 {
	// Set Base URL
	String ROOT_URI = "https://petstore.swagger.io/v2/pet";
	String baseURI= "https://petstore.swagger.io/v2/pet/";

	@Test(priority=1)
	public void AddNewPet() {
		// Write the request body
		String reqBody = "{ \"id\": 85425,  \"name\": \"Tommy\", \"status\": \"alive\"}";

		Response response = given().contentType(ContentType.JSON) // Set headers
				.body(reqBody).when().post(ROOT_URI); // Send POST request

		// Print response of POST request
		String body = response.getBody().asPrettyString();
		System.out.println(body);
	}
	
	@Test(priority=2)
	public void GetNewPet() {
		
		   Response response = 
			        given().contentType(ContentType.JSON) // Set headers
			        .when().get(baseURI +85425); // Send GET request
			  
			    // Print response
			    System.out.println("Get Response :::"+response.asPrettyString());
			    // Assertions
			    response.then().body("status", equalTo("alive"));
			}
	
	@Test(priority=3)
	public void deletePet() {
		Response response = 
		        given().contentType(ContentType.JSON) // Set headers
		        .when().delete(ROOT_URI + "/85425"); // Send DELETE request
		    
		    response = 
		        given().contentType(ContentType.JSON) // Set headers
		        .when().get(ROOT_URI + "/85425"); // Send GET request
		    
		    // Print response
		    System.out.println("Get response after deletion::" +response.getBody().asPrettyString());
		    // Assertion
		    response.then().body("message", equalTo("Pet not found"));
		    
		}
	}

	
