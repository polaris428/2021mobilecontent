package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.databinding.ActivityJoinBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class JoinActivity extends AppCompatActivity {

    ActivityJoinBinding binding;
    String mail1;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        String name=binding.nameinput.getText().toString();


        binding.joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = binding.idinput.getText().toString().trim();
                final String pwe = binding.pawinput.getText().toString().trim();
                //final String name =binding.nameinput.getText().toString().trim();
                int idx = email.indexOf("@");

                mail1 = email.substring(0, idx);

                SharedPreferences sharedPreferences= getSharedPreferences("Login", MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("email",email);
                editor.putString("id",mail1);
                editor.putString("pwe",pwe);// key,value 형식으로 저장
                editor.commit();

                databaseReference.child("UserProfile").child(mail1).child("name").setValue(name);
                //databaseReference.child("UserProfile").child(mail1).child("mouney").setValue(50000);
                databaseReference.child("UserProfile").child(mail1).child("Caffeine").setValue(0);
                //final String name = name_join.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();
                firebaseAuth.createUserWithEmailAndPassword(email, pwe)
                        .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Date date = new Date(System.currentTimeMillis()); //날짜
                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(JoinActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });

            }
        });



    }
}