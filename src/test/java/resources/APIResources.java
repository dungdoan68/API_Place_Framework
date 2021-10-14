package resources;

public enum APIResources {
	addPlace("/maps/api/place/add/json"),
	deletePlace("/maps/api/place/delete/json"),
	updatePlace("/maps/api/place/update/json"),
	getPlace("/maps/api/place/get/json");

	private String resource;
	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
