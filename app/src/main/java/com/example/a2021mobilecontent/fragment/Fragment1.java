package com.example.a2021mobilecontent.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021mobilecontent.Database;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.data.Usercaffeine;
import com.example.a2021mobilecontent.adaptr.UserAdapter;
import com.example.a2021mobilecontent.fragment.infragment.GraphFragment_1;
import com.example.a2021mobilecontent.activity.Caffeine_Popup;
import com.example.a2021mobilecontent.databinding.Fragment1Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    private Fragment1Binding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<Usercaffeine> arrayList;

    private DatabaseReference databaseReference;
    public  class sa{
        public  int value;
    }
    public  int value;
    public  String name;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        binding = Fragment1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedPreferences sf = this.getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String id = sf.getString("id","");
        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");


        binding.circularFillableLoaders.setProgress(100-value);

        
        binding.popbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Caffeine_Popup.class);
                startActivity(intent);

            }
        
        });
        Database da=new Database();
        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sc = getContext().getSharedPreferences("Count", Context.MODE_PRIVATE);  // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sc.edit();
                editor.putInt("count",0);
                editor.commit();

                da.clear(id,1,value);


            }
        });
        binding.test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caffeine.setValue(value+10);
            }
        });
        binding.test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caffeine.setValue(value-10);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Usercaffeine usercaffeine = snapshot.getValue(Usercaffeine.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    arrayList.add(usercaffeine); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침해야 반영이 됨
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("Fraglike", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
        adapter = new UserAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결
        Caffeine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(Integer.class);
                GraphFragment_1 g=new GraphFragment_1();
                Log.d("TAF",g.day1+"");
                binding.circularFillableLoaders.setProgress(100-(value/3));
                binding.intake.setText("일일 섭취량\n"+value+"/300mg");
                System.out.println(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        return view;

    }



}