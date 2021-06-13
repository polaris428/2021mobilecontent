package com.example.a2021mobilecontent;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    int mm;
    int a;
    List<Entry> entries = new ArrayList<>();
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
    String getday = sdf.format(date);
    int today=Integer.parseInt(getday);

    public void clear(String id,int day,int caffeine){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");
        //if (day<today){

        Caffeine.setValue(0);
        daycount(caffeine);

        DatabaseReference databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed");
        databaseReference.removeValue();
        a=1;
    }


    public  void daycount(int caffeine){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dc = database.getReference("UserProfile").child("iou1056212").child("day").child("0");

            dc.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    mm = dataSnapshot.getValue(Integer.class);
                    if(a==1){
                        a=0;
                        input(mm,caffeine);

                    }




                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });


        }




    public void input(int count ,int caffeine){
        String count1=count+"";
        Log.d("count",count1);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String getday = sdf.format(date);
        int today=Integer.parseInt(getday);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference day = database.getReference("UserProfile").child("iou1056212").child("day").child(count1);
        day.setValue(today);
        DatabaseReference arr = database.getReference("UserProfile").child("iou1056212").child("arr").child(count1);
        arr.setValue(caffeine);
        DatabaseReference dc = database.getReference("UserProfile").child("iou1056212").child("day").child("0");
        if(count==7){
            dc.setValue(1);
            for(int i=1;i<6;i++){
                String rank=i+"";
                String rank1=i+1+"";
                DatabaseReference a = database.getReference("UserProfile").child("iou1056212").child("day").child(rank);
                DatabaseReference b = database.getReference("UserProfile").child("iou1056212").child("day").child(rank1);

            }


        }else {

            dc.setValue(++count);
        }

    }
}
