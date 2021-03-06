package com.example.a2021mobilecontent.fragment.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021mobilecontent.Database;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.adaptr.RecyclerDecoration;
import com.example.a2021mobilecontent.data.Usercaffeine;
import com.example.a2021mobilecontent.adaptr.UserAdapter;
import com.example.a2021mobilecontent.databinding.FragmentCaffeineBinding;
import com.example.a2021mobilecontent.fragment.GraphFragment.GraphFragment_1;
import com.example.a2021mobilecontent.activity.Caffeine_Popup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCaffeine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCaffeine extends Fragment {
    private  FragmentCaffeineBinding binding;

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

    public FragmentCaffeine() {
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
    public static FragmentCaffeine newInstance(String param1, String param2) {
        FragmentCaffeine fragment = new FragmentCaffeine();
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
    public int random(int max,int min){
        Random random = new Random();
        int randomNum = random.nextInt(max - min + 1) + min;
        return  randomNum;
    }
    Database da=new Database();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCaffeineBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        SharedPreferences sf = this.getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String id = sf.getString("id","");
        
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("DAY", Context.MODE_PRIVATE);    // test ????????? ???????????? ??????
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences??? ????????? editor??? ??????
        //editor.putInt("day",newday);



        DatabaseReference Caffeine = database.getReference("UserProfile").child(id).child("Caffeine");
        int randomNum =random(10,0);

        binding.circularFillableLoaders.setProgress(100-value);
        binding.msg.setText(getString(R.string.c+randomNum));
        binding.popbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Caffeine_Popup.class);
                startActivity(intent);

            }
        
        });
        Database da=new Database();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // ?????????????????? ???????????? ??????
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User ????????? ?????? ????????? ????????? (??????????????????)
        database = FirebaseDatabase.getInstance(); // ?????????????????? ?????????????????? ??????
        databaseReference = database.getReference("UserProfile").child(id).child("DrinkConsumed"); // DB ????????? ??????
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // ?????????????????? ????????????????????? ???????????? ???????????? ???
                arrayList.clear(); // ?????? ?????????????????? ?????????????????? ?????????
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // ??????????????? ????????? List??? ????????????
                    Usercaffeine usercaffeine = snapshot.getValue(Usercaffeine.class); // ??????????????? User ????????? ???????????? ?????????.
                    arrayList.add(usercaffeine); // ?????? ??????????????? ?????????????????? ?????? ????????????????????? ?????? ??????
                }
                adapter.notifyDataSetChanged(); // ????????? ?????? ??? ?????????????????? ????????? ???

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // ????????? ??????????????? ?????? ?????? ???
                Log.e("Fraglike", String.valueOf(databaseError.toException())); // ????????? ??????
            }
        });

        adapter = new UserAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter); // ????????????????????? ????????? ??????

        RecyclerDecoration spaceDecoration = new RecyclerDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);
        Caffeine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(Integer.class);
                SharedPreferences sharedPreferences= getContext().getSharedPreferences("Caffeine",getContext().MODE_PRIVATE);    // test ????????? ???????????? ??????
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences??? ????????? editor??? ??????
                editor.putInt("caffeine",value);
                editor.commit();
                binding.circularFillableLoaders.setProgress(100-(value/3));
                binding.intake.setText("?????? ?????????\n"+value+"/300mg");




                if(value==0){
                    binding.recyclerView.setVisibility(View.GONE);
                }else {
                    binding.recyclerView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        return view;

    }



}