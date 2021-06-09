package com.example.a2021mobilecontent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphFragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphFragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ArrayList day1 = new ArrayList();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraphFragment_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphFragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphFragment_1 newInstance(String param1, String param2) {
        GraphFragment_1 fragment = new GraphFragment_1();
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
    private LineChart lineChart;
    Context ct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup Grapfragment1 = (ViewGroup)inflater.inflate(R.layout.fragment_graph_1,container,false);
        ViewPager vp =Grapfragment1.findViewById(R.id.viewpager);
        lineChart = (LineChart)Grapfragment1.findViewById(R.id.chart);
        ct = container.getContext();



        List<Entry> entries = new ArrayList<>();
        ArrayList<Integer> a=new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference arr = database.getReference("UserProfile").child("iou1056212").child("arr");

        arr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count=0;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    int url = ds.getValue(Integer.class);

                    a.add(url);

                    Log.d("adsf",a+"");
                    if(a.size()==5){
                        for(int i : a)
                        {

                            count++;
                            entries.add(new Entry(count,i));
                            Log.d("adsf",i+"");
                            LineDataSet lineDataSet = new LineDataSet(entries, "카페인 마신량");
                            lineDataSet.setLineWidth(2);
                            lineDataSet.setCircleRadius(6);
                            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet.setCircleColorHole(Color.BLUE);
                            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet.setDrawCircleHole(true);
                            lineDataSet.setDrawCircles(true);
                            lineDataSet.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet.setDrawHighlightIndicators(false);
                            lineDataSet.setDrawValues(false);

                            LineData lineData = new LineData(lineDataSet);
                            lineChart.setData(lineData);

                            XAxis xAxis = lineChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextColor(Color.BLACK);
                            xAxis.enableGridDashedLine(8, 24, 0);

                            YAxis yLAxis = lineChart.getAxisLeft();
                            yLAxis.setTextColor(Color.BLACK);

                            YAxis yRAxis = lineChart.getAxisRight();
                            yRAxis.setDrawLabels(false);
                            yRAxis.setDrawAxisLine(false);
                            yRAxis.setDrawGridLines(false);

                            Description description = new Description();
                            description.setText("");

                            lineChart.setDoubleTapToZoomEnabled(false);
                            lineChart.setDrawGridBackground(false);
                            lineChart.setDescription(description);
                            lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
                            lineChart.invalidate();

                            MyMarkerView marker = new MyMarkerView(ct,R.layout.activity_my_marker_view);
                            marker.setChartView(lineChart);
                            lineChart.setMarker(marker);

                        }

                    }






                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        return Grapfragment1;
    }



    @Override
    public void onDestroy() {

        super.onDestroy();

    }
}