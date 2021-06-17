package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.a2021mobilecontent.Database;
import com.example.a2021mobilecontent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LodingActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Database database = new Database();
    boolean day;
    int  today;
    int yesterday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_activity);

        SharedPreferences sf = getSharedPreferences("Login",MODE_PRIVATE);
        String email = sf.getString("email","");
        String pwe = sf.getString("pwe","");
        String id=sf.getString("id","");
        SharedPreferences sellp = getSharedPreferences("time",MODE_PRIVATE);
        int  ss = sellp.getInt("time",0);
        if(email!=""&&pwe!=""){
            firebaseAuth = firebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, pwe)
                    .addOnCompleteListener(LodingActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {//성공했을때
                                Log.d("asdf",1+"");

                                yesterday=database.getday(LodingActivity.this);
                                Log.d("adsf",yesterday+"");

                                day=database.nextday(yesterday);
                                if(day==true){
                                    database.clear(id,yesterday, 100,ss);
                                }
                                Log.d("1",3+"");
                                Intent intent = new Intent(LodingActivity.this, MainActivity.class);

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            } else {
                                Toast.makeText(LodingActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


        else{
            Intent intent = new Intent(LodingActivity.this, LoginActivity.class);
            startActivity(intent);
        }





    }



}