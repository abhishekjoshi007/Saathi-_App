package com.invaders.saathi;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerRepository {

    public CustomerRepository() {
    }

    public LiveData<Customer> getCustomerDetails(String customerId) {

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("customers").document(customerId);
        FirebaseDocument firebaseDocument = new FirebaseDocument(documentReference);
        LiveData<Customer> customerLiveData;
        customerLiveData = Transformations.map(firebaseDocument, new Function<DocumentSnapshot, Customer>() {
            @Override
            public Customer apply(DocumentSnapshot input) {
                return input.toObject(Customer.class);
            }
        });
        return customerLiveData;
    }
}
