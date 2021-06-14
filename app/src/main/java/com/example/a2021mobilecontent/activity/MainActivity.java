package com.example.a2021mobilecontent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.a2021mobilecontent.fragment.Fragment1;
import com.example.a2021mobilecontent.fragment.MainFragment.FragmentCaffeine;
import com.example.a2021mobilecontent.fragment.Fragment2;
import com.example.a2021mobilecontent.fragment.Fragment3;
import com.example.a2021mobilecontent.fragment.Fragment4;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager=getSupportFragmentManager();
    private FragmentCaffeine fragment1=new FragmentCaffeine();
    private BottomNavigationView mBottomNV;
    ActivityMainBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        SharedPreferences sf = getSharedPreferences("Login",MODE_PRIVATE);
        String id = sf.getString("id","");
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                binding.wellcom.setVisibility(View.GONE);
            }
        }, 3000); // 0.5초후
        DatabaseReference name= database.getReference("UserProfile").child(id).child("name");
        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String username = dataSnapshot.getValue(String.class);
                binding.wellcom.setText(username+"님 환영합니다");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment1()).commit();

        binding.bottomnavi.setItemSelected(R.id.item_fragment1,true);
       binding.bottomnavi.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
           @Override
           public void onItemSelected(int i) {
               switch (i){
                   case R.id.item_fragment1:
                       getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment1()).commit();
                       break;
                   case R.id.item_fragment2:
                       getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment2()).commit();
                       break;
                   case R.id.item_fragment3:
                       getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment3()).commit();
                       break;
                   case R.id.item_fragment4:
                       getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment4()).commit();
                       break;
               }
           }
       });
    }
}