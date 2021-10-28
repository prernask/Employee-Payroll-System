package com.example.helpers;

public class Employee {
    String emp_fnm,emp_lnm,emp_bacc,emp_pan,emp_dept,emp_design,emp_mobile,emp_password;
    
    public Employee(){
        
    }
    
    public Employee(String emp_fnm,String emp_lnm,String emp_bacc,String emp_pan,String emp_dept,String emp_design,String emp_mobile,String emp_password){
        this.emp_fnm=emp_fnm;
        this.emp_lnm=emp_lnm;
        this.emp_bacc=emp_bacc;
        this.emp_pan=emp_pan;
        this.emp_dept=emp_dept;
        this.emp_design=emp_design;
        this.emp_mobile=emp_mobile;
        this.emp_password=emp_password;
    }

    public String getEmp_fnm() {
        return emp_fnm;
    }

    public void setEmp_fnm(String emp_fnm) {
        this.emp_fnm = emp_fnm;
    }

    public String getEmp_lnm() {
        return emp_lnm;
    }

    public void setEmp_lnm(String emp_lnm) {
        this.emp_lnm = emp_lnm;
    }

    public String getEmp_bacc() {
        return emp_bacc;
    }

    public void setEmp_bacc(String emp_bacc) {
        this.emp_bacc = emp_bacc;
    }

    public String getEmp_pan() {
        return emp_pan;
    }

    public void setEmp_pan(String emp_pan) {
        this.emp_pan = emp_pan;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public String getEmp_design() {
        return emp_design;
    }

    public void setEmp_design(String emp_design) {
        this.emp_design = emp_design;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }
}
