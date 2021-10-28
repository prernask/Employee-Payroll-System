package com.example.emppaysys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class EmployeePortalFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_portal_fragments);

        getSupportActionBar().hide();

        Intent i = getIntent();
        String flag = i.getStringExtra("flag");

        if(flag.equals("Profile")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.add(R.id.frame_employee,new FragmentProfile());
            ft.commit();

        }
        if(flag.equals("Salary Slip")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.add(R.id.frame_employee,new FragmentEmpSalarySlip());
            ft.commit();

        }
    }
}