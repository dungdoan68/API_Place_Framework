Feature: Validating Place API's'

@AddPlace
Scenario Outline: Verify if place is being succesfully added by  using AddPlaceAPI
Given add place form payload using "<name>" "<address>" "<language>"
When user call "addPlace" with "POST" http request
Then the API call is success with status code 200
And Status in body is OK
And Verify Place_Id map with "<name>" using "getPlace"

Examples:	
	|name	|address		|language		|
	|AAB	|AAA adress		|AA language		|
	|BBB	|BBB adress		|BB language		|

@DeletePlace	
Scenario: Verify if place is being delete
Given call delete place from payload 
When user call "deletePlace" with "DELETE" http request
Then the API call is success with status code 200
And Status in body is OK