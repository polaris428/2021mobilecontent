package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    boolean emailtrue=false;
    boolean pawtrue=false;

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

        binding.idinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emile=binding.idinput.getText().toString();
                if(emile.indexOf("@")!=-1){
                    binding.idtext.setVisibility(View.INVISIBLE);
                    emailtrue=true;
                }else{
                    binding.idtext.setVisibility(View.VISIBLE);
                    emailtrue=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.pawinput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p=binding.pawinput1.getText().toString();
                String p1= binding.pawinput2.getText().toString();
                if(p.equals(p1)){
                    binding.pwetext.setVisibility(View.INVISIBLE);
                    pawtrue=true;
                    Log.d("문자",p+p1);
                }else{
                    binding.pwetext.setVisibility(View.VISIBLE);
                    pawtrue=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.nameinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p=binding.pawinput1.getText().toString();
                String p1= binding.pawinput2.getText().toString();
                String emile=binding.idinput.getText().toString();
                String name=binding.nameinput.getText().toString();
                if(p!=null&&p1!=null&&emile!=null&&name!=null){
                    binding.joinbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Database database=new Database();
            binding.joinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pawtrue==true&&emailtrue==true) {
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
                                            database.inputuser(id, name);
                                            database.login(JoinActivity.this, email, id, pwe);
                                            database.saveday(JoinActivity.this);


                                            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(JoinActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(JoinActivity.this,"형식을 확인해주세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }





}
