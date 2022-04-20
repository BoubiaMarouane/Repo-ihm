package edu.polytech.repo_ihm.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Recette {

    FirebaseDatabase database = DatabaseInstance.getInstance();
    DatabaseReference ref = database.getReference();
}
