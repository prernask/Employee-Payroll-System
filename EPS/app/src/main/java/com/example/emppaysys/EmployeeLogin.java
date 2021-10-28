package com.example.emppaysys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class EmployeeLogin extends AppCompatActivity {

    EditText edt_nm,edt_ps;
    Button btn_log;

    String unm,password,regexUnm,regexPassword;

    DatabaseReference dbRef;

    //String url= config.url;
    //RequestQueue req;
    //public static String userNm;
    //public static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        edt_nm=findViewById(R.id.edt_unm);
        edt_ps=findViewById(R.id.edt_pass);

        btn_log=findViewById(R.id.btn_log);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unm=edt_nm.getText().toString();
                password=edt_ps.getText().toString();

                regexUnm="[A-Za-z.]{5,10}";
                regexPassword="[A-Za-z0-9.]{8,16}";

                if(unm.equals("") || password.equals("") ){

                    Toast.makeText(getApplicationContext(),"Enter Username and Password !! ",Toast.LENGTH_LONG).show();
                }
                else {
                    if(isUserValid(unm)) {

                        if(isPasswordValid(regexPassword,password)) {

                            dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
                            dbRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if(unm.equals("admin") && password.equals("admin1234") ){
                                        Intent i = new Intent(getApplicationContext(), AdminPortal.class);
                                        i.putExtra("flag","startOrExit");
                                        startActivity(i);
                                        edt_nm.setText("");
                                        edt_ps.setText("");
                                    }
                                    else{

                                        boolean flag = false;

                                        for (DataSnapshot ds : snapshot.getChildren()){

                                            Log.i("mytag","name : "+ds.child("emp_fnm").getValue().toString());
                                            Log.i("mytag","password : "+ds.child("emp_password").getValue().toString());

                                            if(ds.child("emp_fnm").getValue().toString().equals(unm) && ds.child("emp_password").getValue().toString().equals(password)){
                                                Toast.makeText(EmployeeLogin.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(), EmployeePortal.class);
                                                flag=true;

                                                SharedPreferences sharedPref = getApplication().getSharedPreferences("loginName",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPref.edit();

                                                if(sharedPref.contains("LoginName")){
                                                    editor.clear();
                                                }

                                                editor.putString("LoginName",ds.child("emp_fnm").getValue().toString());
                                                editor.apply();

                                                i.putExtra("flag", "startOrExit");
                                                startActivity(i);

                                                edt_nm.setText("");
                                                edt_ps.setText("");
                                            }

                                        }

                                        if(!flag){
                                            Toast.makeText(EmployeeLogin.this, "Please check your login detail", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });




                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password should be 8 characters no symbols allowed -> abcde007 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Plz enter valid Username ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*txt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registration=new Intent(getApplicationContext(),EmployeePortal.class);
                registration.putExtra("flag","success");
                finish();
                startActivity(registration);

            }
        });*/
    }

    public boolean isUserValid(String text){

        /*String regexEmail,regexMobile;
        regexMobile="[0-9]{10}";
        regexEmail="[a-z0-9]{10,30}@[a-z]{3,6}.[a-z]{3}";
        Pattern checkRegex = Pattern.compile(regexEmail);
        Pattern checkRegex1 = Pattern.compile(regexMobile);*/
        //Matcher regexMatcher = checkRegex.matcher(text);

        if(text.matches(regexUnm)){
            return  true;
        }
        return false;
    }
    public boolean isPasswordValid(String regexPassword, String password) {

        //Pattern checkRegex = Pattern.compile(regexPassword);

        if(password.matches(regexPassword)){
            return  true;
        }
        else {
            return false;
        }
    }
}