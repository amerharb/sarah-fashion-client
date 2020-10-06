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

    enum Command {getAllCustomer, getCustomerBySsn, exit, invalid}

    private Command readCommand() {
        System.out.println("1. Get All Customers\n2. Get Customers by SSN\n4. exit");
        Scanner scanner = new Scanner(System.in);
        int inputInt = scanner.nextInt();
        if (inputInt == 1) return Command.getAllCustomer;
        if (inputInt == 2) return Command.getCustomerBySsn;
        if (inputInt == 4) return Command.exit;
        return Command.invalid;
    }

    private String readSsn() {
        System.out.println("Enter Customer SSN");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void getAllCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
            String resourceURL = "http://localhost:5006/customer/";
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(
                    resourceURL,
                    HttpMethod.GET,
                    entity,
                    Customer[].class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                if (responseEntity.getBody() == null){
                    System.out.println("No customer found");
                }else {
                    System.out.println("Customer are:");
                    for (Customer c: responseEntity.getBody()){
                        System.out.println(c);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void getCustomer(String ssn) {
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
            String resourceURL = "http://localhost:5006/customer/" + ssn;
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Customer> responseEntity = restTemplate.exchange(
                    resourceURL,
                    HttpMethod.GET,
                    entity,
                    Customer.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                if (responseEntity.getBody() == null){
                    System.out.println("No customer found");
                }else {
                    Customer c = responseEntity.getBody();
                    System.out.println("Customer is:" + c);
                }
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
                case getCustomerBySsn: {
                    String ssn = readSsn();
                    getCustomer(ssn);
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
