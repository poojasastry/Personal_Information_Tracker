package com.example.pooja.assignment2.personalinformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectMajorFragment extends ListFragment implements View.OnClickListener {

    ArrayList<String> allMajorsList = new ArrayList<String>();
    String degreeName,majorName;

    public SelectMajorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_major, container, false);
        Button done = view.findViewById(R.id.done);
        done.setOnClickListener(this);
        Button cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lv = getListView();
        if (getArguments() != null) {
            allMajorsList = getArguments().getStringArrayList("MAJORS");
            degreeName = getArguments().getString("DEGREE_NAME");
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_single_choice, allMajorsList);
            setListAdapter(adapter);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    majorName = (String) adapterView.getItemAtPosition(i);
                }
            });
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
                if(majorName == null){
                    Toast.makeText(getActivity(), R.string.majorEmptyError, Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), PersonActivity.class);
                    NavUtils.navigateUpTo(getActivity(), intent);
                    intent.putExtra("FINAL_MAJOR", degreeName + " - " + majorName);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.cancel:
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
