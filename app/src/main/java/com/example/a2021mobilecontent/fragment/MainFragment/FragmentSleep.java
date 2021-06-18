package com.example.a2021mobilecontent.fragment.MainFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.databinding.FragmentCaffeineBinding;
import com.example.a2021mobilecontent.databinding.FragmentSleepBinding;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSleep#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSleep extends Fragment {
    private FragmentSleepBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int timesleeph=0;
    int timesleepm=0;
    int wakeuptimeh=0;
    int wakeuptimem=0;

    public FragmentSleep() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sleep_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSleep newInstance(String param1, String param2) {
        FragmentSleep fragment = new FragmentSleep();
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
        binding = FragmentSleepBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.TimePicker1.setHour(0);
        binding.TimePicker1.setMinute(0);
        binding.TimePicker2.setHour(0);
        binding.TimePicker2.setMinute(0);

        binding.TimePicker1.setOnTimeChangedListener(this::OnTimeChanged1);
        binding.TimePicker2.setOnTimeChangedListener(this::OnTimeChanged2);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time(timesleeph,timesleepm,wakeuptimeh,wakeuptimem);
            }
        });
        return view;
    }
    public void OnTimeChanged1(TimePicker view, int h, int m){
        timesleeph=h;
        timesleepm=m;
    }
    public void OnTimeChanged2(TimePicker view, int h, int m){
        wakeuptimeh=h;
        wakeuptimem=m;
    }
    public void time(int h1,int m1,int h2,int m2){
        int m3;
        int h3;
        if(m2<m1){
            h1--;
            m3=m2-m1;
            h3=h2-h1;
        }else {
            m3=m2-m1;
            h3=h2-h1;
        }
        if(h3<0){
            h3=h3*-1;
        }
        binding.tv.setText(h3+"시간: "+m3+"분");
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("time", Context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putInt("time",h3);

        // key,value 형식으로 저장
        editor.commit();
    }

}
