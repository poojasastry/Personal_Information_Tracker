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

public class DegreeActivity extends AppCompatActivity {

    ArrayList<String> degreeList = new ArrayList<String>();

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree);

        fragmentManager = getSupportFragmentManager();

        try{
            InputStream allDegreesFile = getAssets().open("degrees");
            BufferedReader in = new BufferedReader(new InputStreamReader(allDegreesFile));
            String degree;
            while((degree = in.readLine())!=null)
            {
                degreeList.add(degree);
            }
        }
        catch (Exception e){
            Log.e("ps","read Error",e);
        }

        Bundle passSelectedDegree = new Bundle();
        passSelectedDegree.putStringArrayList("DEGREES",degreeList);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            SelectDegreeFragment degreeFragment = new SelectDegreeFragment();
            degreeFragment.setArguments(passSelectedDegree);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,degreeFragment,null);
            fragmentTransaction.commit();
        }
    }
}
