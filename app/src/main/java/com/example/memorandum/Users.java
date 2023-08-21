package com.example.memorandum;

public class Users {

    String  userId, firstName, lastlame, uName, email , password ;

    public Users() {

    }
    public Users(String userId ,String s1, String s2, String s3, String s4, String s5) {
        this.userId = userId;
        this.firstName = s1;
        this.lastlame = s2;
        this.uName = s3;
        this.email = s4;
        this.password = s5;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastlame() {
        return lastlame;
    }

    public void setLastlame(String lastlame) {
        this.lastlame = lastlame;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
