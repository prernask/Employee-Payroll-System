package com.example.emppaysys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EmployeePortal extends AppCompatActivity {

    TextView txt_profile,txt_sal;
    ImageView profile_vw;
    ImageView sal_slip_vw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_portal);

        txt_profile = findViewById(R.id.txt_profile);
        txt_sal = findViewById(R.id.txt_salary_slip);

        profile_vw = findViewById(R.id.img_vw_profile);
        sal_slip_vw = findViewById(R.id.img_vw_sal_slip);

        getSupportActionBar().hide();

        profile_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentsCall(view,txt_profile.getText().toString());
            }
        });

        sal_slip_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentsCall(view, txt_sal.getText().toString());
            }
        });
    }

    public void fragmentsCall(View v, String flag){
        Intent i = new Intent(EmployeePortal.this, EmployeePortalFragments.class);
        i.putExtra("flag", flag);
        startActivity(i);

    }
}