package com.example.aser.careerbook;

public class ProfileModel {
    double matricTm;
    double matricOm;
    double interTm;
    double interOm;
    double testOm;

    public double getUetAgrigate() {
        return uetAgrigate;
    }

    public void setUetAgrigate(double uetAgrigate) {
        this.uetAgrigate = uetAgrigate;
    }

    double uetAgrigate;
    String testName,fullName,email,matricType,interType;

   public ProfileModel()
    {

    }

    public ProfileModel(double matricTm, double matricOm, double interTm, double interOm, double testOm, String testName,String FullName,String Email,String matricType,String interType,double uetAgrigate) {
        this.matricTm = matricTm;
        this.matricOm = matricOm;
        this.interTm = interTm;
        this.interOm = interOm;
        this.testOm = testOm;
        this.testName = testName;
        this.fullName= FullName;
        this.email = Email;
        this.matricType = matricType;
        this.interType = interType;
        this.uetAgrigate = uetAgrigate;

    }

    public double getMatricTm() {
        return matricTm;
    }

    public double getMatricOm() {
        return matricOm;
    }

    public double getInterTm() {
        return interTm;
    }

    public double getInterOm() {
        return interOm;
    }

    public double getTestOm() {
        return testOm;
    }

    public String getTestName() {
        return testName;
    }

    public String getFullName() { return fullName; }

    public String getEmail() { return email; }

    public String getMatricType() {
        return matricType;
    }

    public String getInterType() {
        return interType;
    }

    public void setMatricType(String matricType) {
        this.matricType = matricType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public void setFullName(String fullName) {
       this.fullName = fullName;
    }

    public void setEmail(String email) { this.email = email; }

    public void setMatricTm(double matricTm) {
        this.matricTm = matricTm;
    }

    public void setMatricOm(double matricOm) {
        this.matricOm = matricOm;
    }

    public void setInterTm(double interTm) {
        this.interTm = interTm;
    }

    public void setInterOm(double interOm) {
        this.interOm = interOm;
    }

    public void setTestOm(double testOm) {
        this.testOm = testOm;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

}
