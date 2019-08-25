package com.invaders.saathi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                List<AuthUI.IdpConfig> providers = Collections.singletonList(
                        new AuthUI.IdpConfig.PhoneBuilder().build()
                );
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        110);
                findViewById(R.id.login_button).setEnabled(false);
                break;
            case R.id.skip_button:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        findViewById(R.id.login_button).setEnabled(true);
        if (requestCode == 110 && data != null) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK && response != null) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null)
                    return;
                if (response.isNewUser()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(user.getUid());
                    customer.setPrimaryPhoneNumber(user.getPhoneNumber());
                    FirebaseFirestore
                            .getInstance()
                            .collection("customers")
                            .document(customer.getCustomerId())
                            .set(customer)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                                }
                            });
                } else if (user.getDisplayName() != null && user.getDisplayName().length() == 0) {
                    startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }
    }
}
