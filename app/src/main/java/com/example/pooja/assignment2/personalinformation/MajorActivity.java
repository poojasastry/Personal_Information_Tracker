package com.example.pooja.assignment2.personalinformation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MajorActivity extends AppCompatActivity {

    ArrayList<String> majorsList = new ArrayList<String>();

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        Bundle passedData = getIntent().getExtras();
        String selectedDegreeName = passedData.getString("SELECTED_DEGREE");
        Log.i("ps", "text of click" + selectedDegreeName);
        int selectedPosition = passedData.getInt("SELECTED_POSITION");

        switch(selectedPosition) {
            case 0:
                getMajors("doctor_of_philosophy");
                break;
            case 1:
                getMajors("doctor_of_education");
                break;
            case 2:
                getMajors("master_of_arts");
                break;
            case 3:
                getMajors("master_of_science");
                break;
            case 4:
                getMajors("master_of_fine_arts");
                break;
            case 5:
                getMajors("professional_masters_degrees");
                break;
            default:
                break;
        }

        fragmentManager = getSupportFragmentManager();

        Bundle passMajorsList = new Bundle();
        passMajorsList.putStringArrayList("MAJORS",majorsList);
        passMajorsList.putString("DEGREE_NAME",selectedDegreeName);
        if(findViewById(R.id.major_fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            SelectMajorFragment majorFragment = new SelectMajorFragment();
            majorFragment.setArguments(passMajorsList);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.major_fragment_container,majorFragment,null);
            fragmentTransaction.commit();
        }
    }

    public void getMajors(String degreeFile) {
        try {
            InputStream majorsListFile = getAssets().open(degreeFile);
            BufferedReader in = new BufferedReader(new InputStreamReader(majorsListFile));
            String eachMajor;
            while ((eachMajor = in.readLine()) != null) {
                majorsList.add(eachMajor);
            }
        } catch (Exception e) {
            Log.e("ps", "read Error", e);
        }
    }
}
