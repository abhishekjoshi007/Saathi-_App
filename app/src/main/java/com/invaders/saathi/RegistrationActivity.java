package com.invaders.saathi;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity {

    public static final int EDIT_MODE = 0;
    public static final int REGISTER_MODE = 1;
    public static final String MODE_KEY = "mode_key";

    private Customer customer;
    private TextInputEditText firstNameEditText;
    private TextInputEditText lastNameEditText;
    private TextView phoneNumberEditText;
    private TextInputEditText emailIdEditText;
    private RadioGroup gender;
    private TextView dateOfBirthTextView;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        int mode = getIntent().getIntExtra(MODE_KEY, 1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null && mode == EDIT_MODE)
            getSupportActionBar().setTitle("Edit Profile");
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        phoneNumberEditText = findViewById(R.id.phone_number);
        emailIdEditText = findViewById(R.id.email_id);
        gender = findViewById(R.id.gender);
        dateOfBirthTextView = findViewById(R.id.date_of_birth);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                customer.setDateOfBirth(new SimpleDateFormat("dd MMM yyyy", Locale.US).format(calendar.getTime()));
                dateOfBirthTextView.setText(customer.getDateOfBirth());
            }
        };
        dateOfBirthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegistrationActivity.this, dateSetListener, 1999, 0, 1).show();
            }
        });
        RegistrationViewModel registrationViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        registrationViewModel.getCustomerLiveData().observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                if (customer == null) {
                    finish();
                    return;
                }
                RegistrationActivity.this.customer = customer;
                if (customer.getGender() != null)
                    switch (customer.getGender()) {
                        case MALE:
                            gender.check(R.id.gender_male);
                            break;
                        case FEMALE:
                            gender.check(R.id.gender_female);
                            break;
                        case OTHERS:
                            gender.check(R.id.gender_others);
                            break;
                    }
                firstNameEditText.setText(customer.getFirstName());
                lastNameEditText.setText(customer.getLastName());
                phoneNumberEditText.setText(String.valueOf(Utility.getPhoneNumberFromString(customer.getPrimaryPhoneNumber())));
                emailIdEditText.setText(customer.getEmailId());
                dateOfBirthTextView.setText(customer.getDateOfBirth());
                findViewById(R.id.submit_button).setEnabled(true);
            }
        });
    }

    public void onClick(final View view) {
        if (view.getId() == R.id.submit_button) {
            String firstName = "";
            String lastName = "";
            String emailId = "";
            if (firstNameEditText.getText() != null)
                firstName = firstNameEditText.getText().toString();
            if (firstName.length() < 2) {
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (lastNameEditText.getText() != null) {
                lastName = lastNameEditText.getText().toString();
            }

            if (lastName.length() == 1) {
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (gender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
                return;
            }
            switch (gender.getCheckedRadioButtonId()) {
                case R.id.gender_male:
                    customer.setGender(Gender.MALE);
                    break;
                case R.id.gender_female:
                    customer.setGender(Gender.FEMALE);
                    break;
                case R.id.gender_others:
                    customer.setGender(Gender.OTHERS);
            }
            if (dateOfBirthTextView.getText() != null) {
                customer.setDateOfBirth(dateOfBirthTextView.getText().toString());
            }

            if (emailIdEditText.getText() != null) {
                if (customer.getEmailId() != null)
                    if (customer.getEmailId().length() < 1)
                        emailId = emailIdEditText.getText().toString();
                    else emailId = customer.getEmailId();
                else emailId = emailIdEditText.getText().toString();
            }
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmailId(emailId);
            customer.setFullName(firstName + " " + lastName);
            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(customer.getFullName())
                    .build();
            Toast.makeText(this, "Updating profile...", Toast.LENGTH_SHORT).show();
            view.setEnabled(false);
            Task<Void> updateUserDataTask = FirebaseFirestore.getInstance()
                    .collection("customers")
                    .document(customer.getCustomerId())
                    .set(customer, SetOptions.merge());

            Task<Void> updateUserProfile = FirebaseAuth.getInstance().getCurrentUser().updateProfile(changeRequest);
            Task<Void> allTask = Tasks.whenAll(updateUserDataTask, updateUserProfile);
            allTask.addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(RegistrationActivity.this, "Profile Updated. Cheers!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
