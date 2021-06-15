package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a2021mobilecontent.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    public static Context context_main;
    private  ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SharedPreferences sf = getSharedPreferences("Login",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String id = sf.getString("email","");
        String pwe = sf.getString("pwe","");
        binding.idinput.setText(id);
        binding.pweinput.setText(pwe);



        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.idinput.getText().toString().trim();
                if(email.contains("@")==true){
                    int idx = email.indexOf("@");
                    String mail1 = email.substring(0, idx);
                    String pwd = binding.pweinput.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {//성공했을때
                                        SharedPreferences sharedPreferences= getSharedPreferences("Login", MODE_PRIVATE);    // test 이름의 기본모드 설정
                                        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                                        editor.putString("id",mail1);
                                        editor.putString("pwe",pwd);
                                        editor.putString("email",email);
                                        // key,value 형식으로 저장
                                        editor.commit();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else {
                    Toast.makeText(LoginActivity.this, "이메일 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
                }
                }


        });

        binding.joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}
