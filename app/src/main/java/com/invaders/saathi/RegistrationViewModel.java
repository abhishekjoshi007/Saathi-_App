package com.invaders.saathi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationViewModel extends ViewModel {

    private LiveData<Customer> customerLiveData;

    public LiveData<Customer> getCustomerLiveData() {
        if (customerLiveData == null) {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null)
                return customerLiveData;
            customerLiveData = new CustomerRepository().getCustomerDetails(currentUser.getUid());
        }
        return customerLiveData;
    }
}
