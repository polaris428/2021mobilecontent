package com.example.a2021mobilecontent;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataCaffeine {

    public  void add(String id,String img,String name,int amount,int count){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String scount= Integer.toString(count);
        DatabaseReference databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed");
        databaseReference.child("DrinkConsumed"+scount).child("Photo").setValue(img);
        databaseReference.child("DrinkConsumed"+scount).child("Name").setValue(name);
        databaseReference.child("DrinkConsumed"+scount).child("Amount").setValue(amount);


    }
    public  void clear(String id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed");
        databaseReference.removeValue();
    }
}
