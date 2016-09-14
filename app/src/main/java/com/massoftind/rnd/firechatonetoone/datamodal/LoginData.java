package com.massoftind.rnd.firechatonetoone.datamodal;

/**
 * Created by sanjeev on 16-02-2015.
 */
public class LoginData {

    private String pass_key="";
    private String user_id="";
    private String username="";
    private String is_active="";
    private String email_id="";
    private String is_emai_verified="";
    private String first_name="";
    private String last_name="";
    private String latitude="";
    private String longitude="";
    private String password="";


    public String getLatitude() { return latitude;}

    public void setLatitude(String latitude) { this.latitude = latitude;}

    public String getLongitude() {return longitude;}

    public void setLongitude(String longitude) {this.longitude = longitude;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    public String getIs_emai_verified() {
        return is_emai_verified;
    }
    public void setIs_emai_verified(String is_emai_verified) {
        this.is_emai_verified = is_emai_verified;
    }
    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_key() {
        return pass_key;
    }

    public void setPass_key(String pass_key) {
        this.pass_key = pass_key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
