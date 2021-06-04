package com.example.a2021mobilecontent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.a2021mobilecontent.databinding.Fragment1Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    private Fragment1Binding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public  int value;
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
                //Caffeine_Popup dlg = new Caffeine_Popup(getContext());
                //dlg.setCanceledOnTouchOutside(true);
                //dlg.setCancelable(true);
                //dlg.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                //dlg.show();

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

        Caffeine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(Integer.class);
                binding.circularFillableLoaders.setProgress(100-value);
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