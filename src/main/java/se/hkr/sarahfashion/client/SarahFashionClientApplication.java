package se.hkr.sarahfashion.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import se.hkr.sarahfashion.client.models.Customer;

import java.util.Collections;
import java.util.Scanner;

@SpringBootApplication
public class SarahFashionClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SarahFashionClientApplication.class, args);
    }

    enum Command {getAllCustomer, exit, invalid}

    private Command readCommand() {
        System.out.println("1. Get All Customers\n4. exit");
        Scanner scanner = new Scanner(System.in);
        int inputInt = scanner.nextInt();
        if (inputInt == 1) return Command.getAllCustomer;
        if (inputInt == 4) return Command.exit;
        return Command.invalid;
    }

    private void getAllCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
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

    @Override
    public void run(String... args) throws Exception {
        APP:
        while (true) {
            switch (readCommand()) {
                case getAllCustomer: {
                    getAllCustomer();
                    break;
                }
                case exit: {
                    break APP;
                }
                default: {
                    System.out.println("enter new command");
                }
            }
            System.out.println("still in");
        }
    }
}
