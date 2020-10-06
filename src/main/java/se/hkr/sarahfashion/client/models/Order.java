package se.hkr.sarahfashion.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty("id")
    public long id;
    @JsonProperty("dateOfOrder")
    public String dateOfOrder; // format of "YY-MM-DD"
    @JsonProperty("totalPrice")
    public double totalPrice;
    @JsonProperty("status")
    public PaymentStatus status;

    public enum PaymentStatus {paid, notPaid}
}
