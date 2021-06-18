package com.example.a2021mobilecontent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a2021mobilecontent.Database;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.databinding.ActivityMainBinding;
import com.example.a2021mobilecontent.databinding.ActivityUserpageBinding;
import com.example.a2021mobilecontent.databinding.UseritemBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Userpage extends AppCompatActivity {
    ActivityUserpageBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Database d=new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        binding= ActivityUserpageBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        SharedPreferences sf = getSharedPreferences("Login", MODE_PRIVATE);
        String id = sf.getString("id", "error");
        SharedPreferences sellp = getSharedPreferences("time", MODE_PRIVATE);
        int seeping=sellp.getInt("time",0);

        TextView nametext=findViewById(R.id.name);
        TextView idtext=findViewById(R.id.idtext);
        TextView sleeptext=findViewById(R.id.sleeptext);
        TextView Caffeinetext=findViewById(R.id.caffeinetext);

        d.nama(id,nametext);

        sleeptext.setText(seeping+"시간");
        idtext.setText(id);
        binding.logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();

                editor.remove("id");
                editor.remove("pwe");
                editor.remove("email");
                editor.commit();

                Intent intent = new Intent(Userpage.this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");
        Caffeine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(Integer.class);
                Caffeinetext.setText(value+"");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



    }
}