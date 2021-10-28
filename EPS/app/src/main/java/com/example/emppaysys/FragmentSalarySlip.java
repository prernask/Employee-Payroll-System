package com.example.emppaysys;

import android.os.Bundle;
import android.util.Log;
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

import com.example.helpers.SalarySlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentSalarySlip extends Fragment {

    EditText emp_id,emp_name,emp_lname,emp_bacc,emp_pan,emp_dept,emp_design,emp_mobile,emp_salary,emp_tax,emp_net_salary;
    Button cred,cancle,show;
    String fnm,lnm,bacc,pan,dept,design,mobile,sal,taxString,netsal;

    DatabaseReference dbRef;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_salary_slip,container,false);
        //emp_id = root.findViewById(R.id.edt_emp_id);
        emp_name = root.findViewById(R.id.edt_emp_name);
        emp_lname = root.findViewById(R.id.edt_emp_lastname);
        emp_bacc = root.findViewById(R.id.edt_emp_bacc);
        emp_pan = root.findViewById(R.id.edt_emp_pan);
        emp_dept = root.findViewById(R.id.edt_emp_dept);
        emp_design = root.findViewById(R.id.edt_emp_design);
        emp_mobile = root.findViewById(R.id.edt_emp_mobile);
        emp_salary = root.findViewById(R.id.edt_emp_sal);
        emp_tax = root.findViewById(R.id.edt_emp_tax);
        emp_net_salary = root.findViewById(R.id.edt_emp_net_sal);
        show = root.findViewById(R.id.btn_show);
        cred = root.findViewById(R.id.btn_credit);
        cancle = root.findViewById(R.id.btn_cnl);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String nm = emp_salary.getText().toString();
                        //int id = Integer.parseInt(emp_id.getText().toString());

                                for (DataSnapshot ds : snapshot.getChildren()){

                                    if(ds.child("emp_fnm").getValue().toString().equals(emp_name.getText().toString())){

                                        emp_name.setText("First Name : "+ds.child("emp_fnm").getValue().toString());
                                        emp_lname.setText("Last Name : "+ds.child("emp_lnm").getValue().toString());
                                        emp_bacc.setText("Bank Acc : "+ ds.child("emp_bacc").getValue().toString());
                                        emp_pan.setText("Pan NO : "+ ds.child("emp_pan").getValue().toString());
                                        emp_dept.setText("Department : "+ds.child("emp_dept").getValue().toString());
                                        emp_design.setText("Designation : "+ ds.child("emp_design").getValue().toString());
                                        emp_mobile.setText("Mobile : "+ ds.child("emp_mobile").getValue().toString());

                                        fnm = ds.child("emp_fnm").getValue().toString();
                                        lnm = ds.child("emp_lnm").getValue().toString();
                                        bacc = ds.child("emp_bacc").getValue().toString();
                                        pan = ds.child("emp_pan").getValue().toString();
                                        dept = ds.child("emp_dept").getValue().toString();
                                        design = ds.child("emp_design").getValue().toString();
                                        mobile = ds.child("emp_mobile").getValue().toString();

                                    }

                                }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });

        cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double salary = Double.parseDouble(emp_salary.getText().toString());
                double tax,nsal;

                if (salary > 50000)
                {
                    tax =salary * 10/100;
                }
                else if (salary > 30000)
                {
                    tax = salary * 5/100;
                }
                else
                {
                    tax = 0;
                }

                emp_salary.setText("Salary : "+salary);

                emp_tax.setText("Tax : " + tax);
                nsal = salary - tax;
                emp_net_salary.setText("Net Salary : "+ nsal);

                Log.i("mytag","setuping the values done!");

                Log.i("mytag","Salary : "+emp_salary.getText().toString());
                Log.i("mytag","Tax : "+emp_tax.getText().toString());

//                String s = nm+"\n"+"\n"+pan+"\n"+bacc+"\n"+sal+"\n"+tax+"\n"+netsal;

                SalarySlip newSlip = new SalarySlip(
                        fnm,
                        lnm,
                        bacc,
                        pan,
                        dept,
                        design,
                        mobile,
                        Double.toString(salary),
                        Double.toString(tax),
                        Double.toString(nsal)
                );

                dbRef = FirebaseDatabase.getInstance().getReference().child("SalarySlip");
                dbRef.push().setValue(newSlip);

                Toast.makeText(getContext(), "Salary Credited", Toast.LENGTH_SHORT).show();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp_name.getText().clear();
                emp_lname.getText().clear();
                emp_pan.getText().clear();
                emp_bacc.getText().clear();
                emp_dept.getText().clear();
                emp_design.getText().clear();
                emp_mobile.getText().clear();
                emp_salary.getText().clear();
                emp_tax.getText().clear();
                emp_net_salary.getText().clear();
            }
        });

        return root;
    }
}
