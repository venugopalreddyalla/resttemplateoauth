package com.cognine.resttemplateoauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTemplateSampleController {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	private String endpoint = "http://www.healthos.co/api/v1/autocomplete/medicines/brands/";

	@GetMapping("/getMedicine/{medicineName}")
	public String getMedicine(@PathVariable("medicineName") String medicineName) throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity(endpoint + medicineName, String.class);
		return entity.getBody();
	}
	
	@GetMapping("/getGender")
	public String getGender() throws Exception {
		//return restTemplate.getAccessToken().getValue();
		ResponseEntity<String> entity = restTemplate.getForEntity("https://localhost:8998/assessmentapp/admin/getTypes/GENDER", String.class);
		return entity.getBody();
	}

}
