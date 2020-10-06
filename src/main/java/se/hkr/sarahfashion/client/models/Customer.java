package se.hkr.sarahfashion.client.models;

import java.util.ArrayList;
import java.util.Collections;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Customer {
    @JsonProperty("ssn")
    public String ssn;
    @JsonProperty("name")
    public String name;
    @JsonProperty("address")
    public String address;
    @JsonProperty("gender")
    public GenderEnum gender;
    @JsonProperty("orders")
    public final ArrayList<Order> orders = new ArrayList<>();

    public enum GenderEnum {male, female}

}
