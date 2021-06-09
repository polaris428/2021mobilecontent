package com.example.a2021mobilecontent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LodingActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_activity);

        SharedPreferences sf = getSharedPreferences("Login",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String id = sf.getString("email","");
        String pwe = sf.getString("pwe","");
        if(id!=""&&pwe!=""){
            firebaseAuth = firebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(id, pwe)
                    .addOnCompleteListener(LodingActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {//성공했을때

                                Intent intent = new Intent(LodingActivity.this, MainActivity.class);
                                Database database=new Database();
                                database.test();

                                startActivity(intent);

                            } else {//실패했을때
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