package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a2021mobilecontent.Database;
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
    String id;
    FirebaseAuth firebaseAuth;
    Database database=new Database();
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


        String name=binding.nameinput.getText().toString();

        Database database=new Database();
        binding.joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = binding.idinput.getText().toString().trim();
                final String pwe = binding.pawinput1.getText().toString().trim();
                //final String name =binding.nameinput.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email, pwe)
                        .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    int idx = email.indexOf("@");
                                    id = email.substring(0, idx);
                                    database.inputuser(id,name);
                                    database.login(JoinActivity.this,email,id,pwe);
                                    database.saveday(JoinActivity.this);


                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
