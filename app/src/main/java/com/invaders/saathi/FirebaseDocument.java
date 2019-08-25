package com.invaders.saathi;

import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import javax.annotation.Nullable;

public class FirebaseDocument extends LiveData<DocumentSnapshot> {

    private DocumentReference documentReference;
    private ListenerRegistration listenerRegistration;
    private EventListener<DocumentSnapshot> eventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            if (documentSnapshot != null && documentSnapshot.exists())
                setValue(documentSnapshot);
        }
    };

    public FirebaseDocument(DocumentReference documentReference) {
        this.documentReference = documentReference;
    }

    @Override
    protected void onActive() {
        listenerRegistration = documentReference.addSnapshotListener(eventListener);
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
    }
}
