package com.example.emppaysys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import static com.example.emppaysys.R.layout.activity_admin_portal_fragments;

public class AdminPortalFragments extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_admin_portal_fragments);

        getSupportActionBar().hide();

        Intent i = getIntent();
        String flag = i.getStringExtra("flag");

        if(flag.equals("Register Employee")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.add(R.id.frame_admin,new FragmentRegister());
            ft.commit();

        }

        if(flag.equals("Emp Info")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frame_admin,new FragmentEmpInfo());
            ft.commit();

        }

        if(flag.equals("Salary Slip")) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.frame_admin, new FragmentSalarySlip());
            ft.commit();
        }
        if(flag.equals("Admin Details")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frame_admin,new FragmentAdminDetails());
            ft.commit();

        }

    }
}