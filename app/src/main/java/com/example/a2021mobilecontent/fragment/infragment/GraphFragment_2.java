package com.example.a2021mobilecontent.fragment.infragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021mobilecontent.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphFragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphFragment_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraphFragment_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphFragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphFragment_2 newInstance(String param1, String param2) {
        GraphFragment_2 fragment = new GraphFragment_2();
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
        ViewGroup GraphFragment_2 = (ViewGroup)inflater.inflate(R.layout.fragment_graph_2,container,false);

        // Inflate the layout for this fragment
        return GraphFragment_2;
    }
}