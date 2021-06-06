package com.example.a2021mobilecontent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Clear {

    void clear(String id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");




    }


}
