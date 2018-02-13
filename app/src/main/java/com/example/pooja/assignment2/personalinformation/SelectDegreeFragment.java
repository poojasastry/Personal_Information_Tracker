package com.example.pooja.assignment2.personalinformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class SelectDegreeFragment extends ListFragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    ArrayList<String> allDegreesList = new ArrayList<String>();

    public SelectDegreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            allDegreesList = getArguments().getStringArrayList("DEGREES");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_degree, container, false);
        Button cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,allDegreesList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.i("ps","position of click" + position);
        String degreeName = (String)parent.getItemAtPosition(position);
        Log.i("ps", "text of click" + degreeName);
        Intent intent = new Intent(getActivity(),MajorActivity.class);
        intent.putExtra("SELECTED_DEGREE",degreeName);
        intent.putExtra("SELECTED_POSITION",position);
        startActivity(intent);
    }

    public void onClick(View v) {
        getActivity().finish();
    }
}
