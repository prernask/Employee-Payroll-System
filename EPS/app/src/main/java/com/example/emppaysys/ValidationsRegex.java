package com.example.emppaysys;

import android.content.Context;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class ValidationsRegex {

    public static String regex_nm,regex_bacc,regex_pan,regex_dept_desig,regex_mobile,regex_password,regex_sal;
    public static boolean flag = false;


    public static boolean validations(Context con,String fnm,String lnm,String bacc,String pan,String dept,String desig,String mobile,String password){
        regex_nm = "[A-Z]{1}[a-z]{4,10}";
        regex_bacc="[0-9]{11}";
        regex_pan ="[A-Z]{5}[0-9]{4}[A-Z]{1}";
        regex_dept_desig="[A-Z]{1}[A-Za-z]{6,15}";
        regex_mobile="[7-9]{1}[0-9]{9}";
        regex_password="[a-z][0-9]{8,16}";
        regex_sal = "[0-9]{5,10}";

        if(validateValue(regex_nm,fnm)){
            if(validateValue(regex_nm,lnm)){
                if(validateValue(regex_bacc,bacc)){
                    if(validateValue(regex_pan,pan)){
                        if(validateValue(regex_dept_desig,dept)){
                            if(validateValue(regex_dept_desig,desig)){
                                if(validateValue(regex_mobile,mobile)){
                                    if(validateValue(regex_password,password)){

                                        flag = true;

                                    } else {
                                        Toast.makeText(con,"Enter Valid Alphanumeric Password ",Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(con,"Enter Valid Mobile Number starting with 7/8/9",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(con,"Enter Valid Designation starting with Capital letter",Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(con,"Enter Valid Department starting with Capital letter",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(con,"Enter Valid Pan No",Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(con,"Enter Valid Bank Account",Toast.LENGTH_LONG).show();
                }
            } else{
                Toast.makeText(con,"Enter Valid Last Name Should start with Capital letter", LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(con,"Enter Valid First Name Should start with Capital letter",Toast.LENGTH_LONG).show();
        }
        return flag;
    }
    public static boolean validateValue(String regex, String value) {

        if(value.matches(regex)){
            return  true;
        }
        return false;
    }

}
