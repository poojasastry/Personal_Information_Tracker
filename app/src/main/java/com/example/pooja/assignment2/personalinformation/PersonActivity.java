package com.example.pooja.assignment2.personalinformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends AppCompatActivity {
    EditText firstName,lastName,age,email,phone;
    TextView major;
    Button setMajor,submit,clear;
    String getFirstName,getLastName,getAge,getEmail,getPhone,getMajor,storedFirstName,storedLastName,storedAge,storedEmail,storedPhone,storedMajor;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String PREFERENCES = "PersonFile";
    public static final String FNAME ="firstNameKey";
    public static final String LNAME = "lastNameKey";
    public static final String AGE ="ageKey";
    public static final String EMAIL ="emailKey";
    public static final String PHONE ="phoneKey";
    public static final String MAJOR ="majorKey";

    String nameRegex = "[a-zA-Z]+",ageRegex = "^[1-9][0-9]?$|^100$",emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",phoneRegex = "^[+]?[0-9]{10,13}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        firstName = this.findViewById(R.id.firstName);
        lastName = this.findViewById(R.id.lastName);
        age = this.findViewById(R.id.age);
        email = this.findViewById(R.id.email);
        phone = this.findViewById(R.id.phone);
        major = this.findViewById(R.id.major);
        setMajor = this.findViewById(R.id.setMajor);
        submit = this.findViewById(R.id.submit);
        clear = this.findViewById(R.id.clear);

        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        Bundle majorData = getIntent().getExtras();
        if (majorData != null) {
            String majorFetched = majorData.getString("FINAL_MAJOR");
            Log.i("ps", "Selected Major Fetched" + majorFetched);
            final TextView majorPreview = findViewById(R.id.major);
            majorPreview.setText(majorFetched);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstName.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.firstNameEmptyError, Toast.LENGTH_LONG).show();
                } else if (!firstName.getText().toString().matches(nameRegex)) {
                    Toast.makeText(getApplicationContext(), R.string.firstNameWrongError, Toast.LENGTH_LONG).show();
                } else if (lastName.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.lastNameEmptyError, Toast.LENGTH_LONG).show();
                } else if (!lastName.getText().toString().matches(nameRegex)) {
                    Toast.makeText(getApplicationContext(), R.string.lastNameWrongError, Toast.LENGTH_LONG).show();
                } else if (age.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.ageEmptyError, Toast.LENGTH_LONG).show();
                } else if (!age.getText().toString().matches(ageRegex)) {
                    Toast.makeText(getApplicationContext(), R.string.ageWrongError, Toast.LENGTH_LONG).show();
                } else if (email.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.emailEmptyError, Toast.LENGTH_LONG).show();
                } else if (!email.getText().toString().matches(emailRegex)) {
                    Toast.makeText(getApplicationContext(), R.string.emailWrongError, Toast.LENGTH_LONG).show();
                } else if (phone.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.phoneEmptyError, Toast.LENGTH_LONG).show();
                } else if (!phone.getText().toString().matches(phoneRegex)) {
                    Toast.makeText(getApplicationContext(), R.string.phoneWrongError, Toast.LENGTH_LONG).show();
                } else if (major.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.majorEmptyError, Toast.LENGTH_LONG).show();
                } else {
                    editor = sharedPreferences.edit();
                    editor.putString(FNAME, firstName.getText().toString());
                    editor.putString(LNAME, lastName.getText().toString());
                    editor.putString(AGE, age.getText().toString());
                    editor.putString(EMAIL, email.getText().toString());
                    editor.putString(PHONE, phone.getText().toString());
                    editor.putString(MAJOR, major.getText().toString());
                    editor.apply();
                    Toast.makeText(getApplicationContext(), R.string.submitSuccess, Toast.LENGTH_LONG).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.setText("");
                lastName.setText("");
                age.setText("");
                email.setText("");
                phone.setText("");
                major.setText("");
            }
        });
    }

    public void showDegreeActivity(View v){
        Intent intent = new Intent(this,DegreeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("ps","onPause");
        editor = sharedPreferences.edit();
        editor.putString(FNAME, firstName.getText().toString());
        editor.putString(LNAME, lastName.getText().toString());
        editor.putString(AGE, age.getText().toString());
        editor.putString(EMAIL, email.getText().toString());
        editor.putString(PHONE, phone.getText().toString());
        editor.commit();
    }

    public void onResume() {
        super.onResume();
        Log.i("ps","onResume");
        Log.i("ps","onResume"+MAJOR);
        firstName.setText(sharedPreferences.getString(FNAME,""));
        lastName.setText(sharedPreferences.getString(LNAME,""));
        age.setText(sharedPreferences.getString(AGE,""));
        email.setText(sharedPreferences.getString(EMAIL,""));
        phone.setText(sharedPreferences.getString(PHONE,""));
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("ps","onDestroy");
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i("ps", "stateSaved");
        storedFirstName = firstName.getText().toString();
        storedLastName = lastName.getText().toString();
        storedAge = age.getText().toString();
        storedEmail = email.getText().toString();
        storedPhone = phone.getText().toString();
        storedMajor = major.getText().toString();
        outState.putString(getFirstName,storedFirstName);
        outState.putString(getLastName,storedLastName);
        outState.putString(getAge,storedAge);
        outState.putString(getEmail,storedEmail);
        outState.putString(getPhone,storedPhone);
        outState.putString(getMajor,storedMajor);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("ps", "stateRestored");
        storedFirstName = savedInstanceState.getString(getFirstName);
        storedLastName = savedInstanceState.getString(getLastName);
        storedAge = savedInstanceState.getString(getAge);
        storedEmail = savedInstanceState.getString(getEmail);
        storedPhone = savedInstanceState.getString(getPhone);
        storedMajor = savedInstanceState.getString(getMajor);
    }
}
