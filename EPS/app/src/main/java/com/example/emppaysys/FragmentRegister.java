package com.example.emppaysys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helpers.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class FragmentRegister extends Fragment {

    EditText emp_fnm,emp_lnm,emp_bacc,emp_pan,emp_dept,emp_desig,emp_mobile,emp_password;
    String fnm,lnm,bacc,pan,dept,desig,mobile,password;
    Button submit,clear;
    int eid = 101;
    Employee employee;

    DatabaseReference dbRef;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_reg,
                container,
                false);

        emp_fnm = root.findViewById(R.id.edt_fname);
        emp_lnm = root.findViewById(R.id.edt_lname);
        emp_bacc = root.findViewById(R.id.edt_bankacc);
        emp_pan = root.findViewById(R.id.edt_pan_no);
        emp_dept = root.findViewById(R.id.edt_dep);
        emp_desig = root.findViewById(R.id.edt_designation);
        emp_mobile = root.findViewById(R.id.edt_mobile);
        emp_password = root.findViewById(R.id.edt_password);
        submit = root.findViewById(R.id.btn_sbt);
        clear = root.findViewById(R.id.btn_clear);
        

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fnm = emp_fnm.getText().toString();
                lnm = emp_lnm.getText().toString();
                bacc = emp_bacc.getText().toString();
                pan = emp_pan.getText().toString();
                dept = emp_dept.getText().toString();
                desig = emp_desig.getText().toString();
                mobile = emp_mobile.getText().toString();
                password = emp_password.getText().toString();

                boolean flag = ValidationsRegex.validations(getContext(),
                        fnm,lnm,bacc,pan,dept,desig,mobile,password);

                if(flag){
                    String s = fnm+"\n"+lnm+"\n"+bacc+"\n"+pan+"\n"+dept+"\n"+desig+"\n"+mobile+"\n"+password;

                    employee = new Employee(
                            emp_fnm.getText().toString(),
                            emp_lnm.getText().toString(),
                            emp_bacc.getText().toString(),
                            emp_pan.getText().toString(),
                            emp_dept.getText().toString(),
                            emp_desig.getText().toString(),
                            emp_mobile.getText().toString(),
                            emp_password.getText().toString());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Emplo yee");
                    dbRef.push().setValue(employee);

                    Toast.makeText(getContext(), "New Employee Added Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_fnm.setText("");
                emp_lnm.setText("");
                emp_bacc.setText("");
                emp_pan.setText("");
                emp_dept.setText("");
                emp_desig.setText("");
                emp_mobile.setText("");
                emp_password.setText("");

            }
        });

        return root;
    }

}
