package com.example.emppaysys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentEmpSalarySlip extends Fragment {

    TextView ename,eid,ebacc,epan,esalary,etax,enetsalary;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_emp_sal_slip,container,false);
        ename = root.findViewById(R.id.txt_emp_name);
        ebacc = root.findViewById(R.id.txt_bank_acc);
        epan = root.findViewById(R.id.txt_pan);
        esalary = root.findViewById(R.id.txt_emp_sal);
        etax = root.findViewById(R.id.txt_sal_tax);
        enetsalary = root.findViewById(R.id.txt_net_sal);


        SharedPreferences sharedPref = getActivity().getSharedPreferences("loginName", Context.MODE_PRIVATE);

        String fnmData = sharedPref.getString("LoginName",null);

        if (fnmData.equals(null)){
            Toast.makeText(getContext(), "Something went Wrong!", Toast.LENGTH_SHORT).show();
        }
        else{

            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("SalarySlip");

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override

                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    boolean flag = false;

                    for (DataSnapshot ds : snapshot.getChildren()){

                        if(fnmData.equals(ds.child("emp_fnm").getValue().toString())){

                            flag = true;

                            ename.setText("First Name : "+ds.child("emp_fnm").getValue().toString());
                            ebacc.setText("Bank Acc : "+ds.child("emp_bacc").getValue().toString() );
                            epan.setText("Pan No : "+ ds.child("emp_pan").getValue().toString());
                            esalary.setText("Salary : "+ ds.child("salary").getValue().toString());
                            etax.setText("Tax : "+ ds.child("tax").getValue().toString());
                            enetsalary.setText("Net Salary : "+ ds.child("netsal").getValue().toString());

                        }


                    }

                    if (!flag){
                        Toast.makeText(getContext(), "Your salary has not generated yet!", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


        return root;
    }

}
