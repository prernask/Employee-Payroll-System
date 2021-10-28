package com.example.emppaysys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentEmpInfo extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    DatabaseReference dbRef;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_emp_info,
                container,
                false);

        listView = (ListView) root.findViewById(R.id.lst_emp_info);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>( getActivity().getApplicationContext() , android.R.layout.simple_list_item_1, arrayList);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot ds : snapshot.getChildren()){

                    arrayList.add("Emp fname : "+ds.child("emp_fnm").getValue().toString());
                    arrayList.add("Emp lname : "+ds.child("emp_lnm").getValue().toString());
                    arrayList.add("Emp bank acc : "+ds.child("emp_bacc").getValue().toString());
                    arrayList.add("Emp pan no : "+ds.child("emp_pan").getValue().toString());
                    arrayList.add("Emp dept : "+ds.child("emp_dept").getValue().toString());
                    arrayList.add("Emp design : "+ds.child("emp_design").getValue().toString());
                    arrayList.add("Emp Mobile no : "+ds.child("emp_mobile").getValue().toString());
                    arrayList.add("Emp password : "+ds.child("emp_password").getValue().toString());

                    arrayList.add("   ");
                }

                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return root;
    }
}
