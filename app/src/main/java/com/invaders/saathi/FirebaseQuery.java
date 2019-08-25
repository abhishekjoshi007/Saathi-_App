package com.invaders.saathi;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseQuery extends LiveData<QuerySnapshot> {

    private Query query;
    private OnCompleteListener<QuerySnapshot> listener = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful())
                setValue(task.getResult());
        }
    };

    public FirebaseQuery(Query query) {
        this.query = query;
    }

    @Override
    protected void onActive() {
        query.get().addOnCompleteListener(listener);
    }
}