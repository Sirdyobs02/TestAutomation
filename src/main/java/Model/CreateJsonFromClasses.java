package Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateJsonFromClasses {
	
	public String return_json_object(String name,String username, String email, String lat,String lang,String street, String suite,
			String city, String zipcode, String phone, String website) throws JsonProcessingException {
		User user = new User();	
		user.setId("");
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		
		Geo geo = new Geo();
		geo.setLat(lat);
		geo.setLng(lang);
		
		Address address = new Address();
		address.setStreet(street);
		address.setSuite(suite);
		address.setCity(city);
		address.setZipcode(zipcode);
		address.setGeo(geo);
		user.setAddress(address);
		user.setPhone(phone);
		user.setWebsite(website);
		
		Company company = new Company();
		company.setName("Inspired Testing");
		company.setCatchPhrase("We going out of our way for you");
		company.setBs("good to know");
		user.setCompany(company);
		
		ObjectMapper mapper = new ObjectMapper();
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		
	}
	
	
	

}
