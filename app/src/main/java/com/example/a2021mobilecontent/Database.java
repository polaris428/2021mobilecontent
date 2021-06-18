package com.example.a2021mobilecontent;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.example.a2021mobilecontent.activity.LodingActivity;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Database {
    int mm;
    int a;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public void clear(String id,int day,int caffeine,int houre){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");

            Caffeine.setValue(0);
            daycount(caffeine, id, houre);
            DatabaseReference databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed");
            databaseReference.removeValue();


            a = 1;
        }


    public void nama(String id, TextView textView){

        DatabaseReference name= database.getReference("UserProfile").child(id).child("name");
        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String username = dataSnapshot.getValue(String.class);
                textView.setText(username);
                Log.d("이름은",username);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    public  void daycount(int caffeine,String id,int houre){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dc = database.getReference("UserProfile").child(id).child("day").child("0");

            dc.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    mm = dataSnapshot.getValue(Integer.class);
                    if(a==1){
                        a=0;
                        input(id,mm,caffeine,houre);

                    }



                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });


        }



    public void input(String id,int count ,int caffeine,int hour){
        String count1=count+"";
        Log.d("count",count1);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat day = new SimpleDateFormat("dd");
        String getday = day.format(date);
        int today=Integer.parseInt(getday);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference days = database.getReference("UserProfile").child(id).child("day").child(count1);
        days.setValue(today-1);//수정필요요ㅛ요요용요
        DatabaseReference arr = database.getReference("UserProfile").child(id).child("arr").child(count1);
        arr.setValue(caffeine);
        DatabaseReference time = database.getReference("UserProfile").child(id).child("time").child(count1);
        time.setValue(hour);
        DatabaseReference dc = database.getReference("UserProfile").child(id).child("day").child("0");


        if(count==7){
            dc.setValue(1);


        }else {

            dc.setValue(++count);
        }


    }

    public void  inputuser(String id,String name){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("UserProfile").child(id).child("id").setValue(id);
        databaseReference.child("UserProfile").child(id).child("name").setValue(name);
        databaseReference.child("UserProfile").child(id).child("Caffeine").setValue(0);
        ArrayList<Integer> num = new ArrayList<Integer>();
        for (int i=0;i<8;i++){
            num.add(0);
        }
        databaseReference.child("UserProfile").child(id).child("day").setValue(num);
        databaseReference.child("UserProfile").child(id).child("arr").setValue(num);
        databaseReference.child("UserProfile").child(id).child("time").setValue(num);
        databaseReference.child("UserProfile").child(id).child("day").child("0").setValue(1);
        databaseReference.child("UserProfile").child(id).child("day").child("1").setValue(todaysever()-1);
        for(int i=0;i<8;i++){
            databaseReference.child("UserProfile").child(id).child("day").child(i+2+"").setValue(todaysever()+i);
        }
    }


    public  void login(Context context ,String email,String id,String pwe){
        SharedPreferences sharedPreferences= context.getSharedPreferences("Login",context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putString("id",id);
        editor.putString("pwe",pwe);
        editor.putString("email",email);
        editor.commit();


    }

    public void saveday(Context context){
        //날짜 저장하는 함수
        List<Entry> entries = new ArrayList<>();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String getday = sdf.format(date);
        int today=Integer.parseInt(getday);
        SharedPreferences sharedPreferences= context.getSharedPreferences("Day",context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putInt("id",today);
        editor.commit();

    }

    public int getday(Context context){
        int day=today();
        //저장된 날짜를 가져오는 함수
        SharedPreferences sf = context.getSharedPreferences("Day",context.MODE_PRIVATE);
        int getday = sf.getInt("day",todaysever()-1);


        return getday;


    }
    public int today(){
        //오늘 날짜 구하는 함수
        List<Entry> entries = new ArrayList<>();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String getday = sdf.format(date);
        int today=Integer.parseInt(getday);

        return  today;

    }
    public int todaysever(){
        //오늘 날짜 구하는 함수
        List<Entry> entries = new ArrayList<>();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String getday = sdf.format(date);
        int today=Integer.parseInt(getday);

        return  today;

    }
    public boolean nextday(int day){

        //int day=전날

        int today=today();
        if(today>=day){
            return  true;
        }else {
            return  false;
        }



    }

}
