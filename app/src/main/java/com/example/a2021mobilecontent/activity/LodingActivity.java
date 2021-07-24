package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2021mobilecontent.Database;
import com.example.a2021mobilecontent.NetworkStatus;
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
    int today;
    int yesterday;
    ImageView logo1;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_activity);

        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if (status == NetworkStatus.TYPE_MOBILE) {
            start();
        } else if (status == NetworkStatus.TYPE_WIFI) {
            start();
        } else {
            showDialog();


        }

    }
     void start(){
         SharedPreferences sf = getSharedPreferences("Login", MODE_PRIVATE);
         String email = sf.getString("email", "");
         String pwe = sf.getString("pwe", "");
         String id = sf.getString("id", "");
         SharedPreferences sellp = getSharedPreferences("time", MODE_PRIVATE);
         int ss = sellp.getInt("time", 0);


         if (email != "" && pwe != "") {
             firebaseAuth = firebaseAuth.getInstance();
             firebaseAuth.signInWithEmailAndPassword(email, pwe)
                     .addOnCompleteListener(LodingActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {//성공했을때
                                 yesterday = database.getday(LodingActivity.this);
                                 day = database.nextday(yesterday);


                                 if (day == true) {
                                     database.saveday(LodingActivity.this);
                                     SharedPreferences sf = getSharedPreferences("Caffeine", MODE_PRIVATE);
                                     int caffeine = sf.getInt("caffeine", 0);
                                     database.clear(id, LodingActivity.this, caffeine, ss);



                                 }
                                 Log.d("1", 3 + "");
                                 Intent intent = new Intent(LodingActivity.this, MainActivity.class);

                                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);
                             } else {
                                 Toast.makeText(LodingActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(LodingActivity.this, LoginActivity.class);
                                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);
                             }
                         }
                     });

         } else {
             Handler mHandler = new Handler();
             mHandler.postDelayed(new Runnable()  {
                 public void run() {
                     Intent intent = new Intent(LodingActivity.this, LoginActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                 }
             }, 3000); // 0.5초후

         }



     }
    void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(LodingActivity.this).setTitle("네트워크 에러").setMessage("인터넷 연결을 확인해주세요").setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.setCanceledOnTouchOutside(false);
        msgDlg.setCancelable(false);
        msgDlg.show();
    }


}