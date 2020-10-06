package se.hkr.sarahfashion.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import se.hkr.sarahfashion.client.models.Customer;

import java.util.Collections;

@SpringBootApplication
public class SarahFashionClientApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SarahFashionClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		org.springframework.http.HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		try{
			String resourceURL = "http://localhost:5006/customer/1111";
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<Customer> responseEntity = restTemplate.exchange(
					resourceURL,
					HttpMethod.GET,
					entity,
					Customer.class
			);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				System.out.println("OK");
				System.out.println(responseEntity);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
