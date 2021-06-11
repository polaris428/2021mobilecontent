package com.example.a2021mobilecontent;

import com.example.a2021mobilecontent.fragment.infragment.GraphFragment_1;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {


    List<Entry> entries = new ArrayList<>();
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String getday = sdf.format(date);
    int today=Integer.parseInt(getday);
    public void clear(String id,int day){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");
        //if (day<today){


        Caffeine.setValue(0);




    }

    public Object day (){
        ArrayList stringPath=new ArrayList();
        String id="iou1056212";
        GraphFragment_1 g=new GraphFragment_1();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        return stringPath;



    }

    public ArrayList intake(ArrayList a){

        return  a;
    }

    public void test(){
        String id="iou1056212";
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference day = database.getReference("UserProfile").child(id).child("day");



    }

}
