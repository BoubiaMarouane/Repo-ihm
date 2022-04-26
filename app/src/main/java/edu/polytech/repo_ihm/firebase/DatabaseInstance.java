package edu.polytech.repo_ihm.firebase;

import com.google.firebase.database.FirebaseDatabase;

// One instance of the database is enough, hence the Singleton
public class DatabaseInstance {

    private static final FirebaseDatabase fbdatabase = FirebaseDatabase.getInstance("https://ihm-app-f0cb2-default-rtdb.europe-west1.firebasedatabase.app/");

    private DatabaseInstance() {}

    public static FirebaseDatabase getInstance() {
        return fbdatabase;
    }
}
