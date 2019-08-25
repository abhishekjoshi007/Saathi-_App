package com.invaders.saathi;

import androidx.annotation.Keep;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;

@Keep
public class Customer extends User {

    @ServerTimestamp
    private Timestamp timestamp;
    private String customerId;
    private boolean available;
    private boolean firstOrderDiscountUsed;
    @ServerTimestamp
    private Timestamp customerRegistrationTime;
    private ArrayList<DocumentReference> orderDocumentReferences;

    public Customer() {
        super();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Timestamp getCustomerRegistrationTime() {
        return customerRegistrationTime;
    }

    public void setCustomerRegistrationTime(Timestamp customerRegistrationTime) {
        this.customerRegistrationTime = customerRegistrationTime;
    }

    public boolean getFirstOrderDiscountUsed() {
        return firstOrderDiscountUsed;
    }

    public void setFirstOrderDiscountUsed(boolean firstOrderDiscountUsed) {
        this.firstOrderDiscountUsed = firstOrderDiscountUsed;
    }

    public ArrayList<DocumentReference> getOrderDocumentReferences() {
        return orderDocumentReferences;
    }

    public void setOrderDocumentReferences(ArrayList<DocumentReference> orderDocumentReferences) {
        this.orderDocumentReferences = orderDocumentReferences;
    }
}
