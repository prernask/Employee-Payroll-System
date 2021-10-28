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

public class FragmentProfile extends Fragment {

    TextView fnm,lnm,bacc,pan,dept,desig,mobile,password;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_profile,container,false);
        fnm = root.findViewById(R.id.fname);
        lnm = root.findViewById(R.id.lname);
        bacc = root.findViewById(R.id.bankacc);
        pan = root.findViewById(R.id.pan_no);
        dept = root.findViewById(R.id.dept);
        desig = root.findViewById(R.id.designation);
        mobile = root.findViewById(R.id.mobile);
        password = root.findViewById(R.id.password);

        SharedPreferences sharedRef = getActivity().getSharedPreferences("loginName", Context.MODE_PRIVATE);
        String fnmData = sharedRef.getString("LoginName",null);

        if(fnmData.equals(null)){
            Toast.makeText(getContext(), "Something went Wrong!", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {

                    for (DataSnapshot ds: snapshot.getChildren()) {

                        if(fnmData.equals(ds.child("emp_fnm").getValue().toString())){

                            fnm.setText("First Name : "+ds.child("emp_fnm").getValue().toString());
                            lnm.setText("Last Name : "+ds.child("emp_lnm").getValue().toString());
                            bacc.setText("Bank Acc : "+ds.child("emp_bacc").getValue().toString());
                            pan.setText("Pan No : "+ds.child("emp_pan").getValue().toString());
                            dept.setText("Department : "+ds.child("emp_dept").getValue().toString());
                            desig.setText("Designation :"+ds.child("emp_design").getValue().toString());
                            mobile.setText("Mobile NO : "+ds.child("emp_mobile").getValue().toString());
                            password.setText("Password : "+ds.child("emp_password").getValue().toString());

                        }

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
