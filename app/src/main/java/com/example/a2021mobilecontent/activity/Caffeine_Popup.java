package com.example.a2021mobilecontent.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2021mobilecontent.data.Caffeine;
import com.example.a2021mobilecontent.adaptr.CustomAdapter;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.databinding.ActivityCaffeinePopupBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Caffeine_Popup extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Caffeine> arrayList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ActivityCaffeinePopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_popup);
        binding= ActivityCaffeinePopupBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        recyclerView = findViewById(R.id.recyclerView); // 아디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



    }

    public void database(){
        binding.svLayout.setVisibility(View.GONE);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Caffeine caffeine = snapshot.getValue(Caffeine.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    arrayList.add(caffeine); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결
        Log.d("adsf","2");
    }
    public void Kinds(View view){
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance();
        switch (view.getId()) {
            case R.id.Can:
                databaseReference = database.getReference("Caffeine");
                database();
                Log.d("adsf","1");
                break;
            case R.id.Starbucks:
                databaseReference = database.getReference("Starbucks");
                database();
                break;
            case R.id.Ediya:
                databaseReference = database.getReference("EDIYA");
                database();
                break;
            case R.id.Angelinus:
                databaseReference = database.getReference("CoffeeBin");
                database();
                break;




        }

    }

}



