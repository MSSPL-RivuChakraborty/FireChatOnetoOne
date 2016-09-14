package com.massoftind.rnd.firechatonetoone.datamodal;

import java.io.Serializable;

/**
 * Created by Developer on 16-02-2015.
 */
public class Status implements Serializable {


    public String getSuccess_message() {
        return success_message;
    }

    public void setSuccess_message(String success_message) {
        this.success_message = success_message;
    }

    /* account_status
            'P' -> Pending
            'I' -> Inactive
            'A'- Active*/
    private String success_message="";
    private String error_message="";
    private String account_status="";
    private boolean login_status=false;
    private boolean action_status=false;

    private boolean offline = false;

    public boolean isLogout_status() {
        return logout_status;
    }

    public void setLogout_status(boolean logout_status) {
        this.logout_status = logout_status;
    }

    private boolean logout_status=false;

    public String getFirst_time_login() {
        return first_time_login;
    }

    public void setFirst_time_login(String first_time_login) {
        this.first_time_login = first_time_login;
    }

    private String first_time_login="";

    private boolean connection_request_status = true;//only work for sendConnection.json api
    private String msg="";
    private String is_emai_verifiedforl;

    public String getIs_emai_verifiedforl() {
        return is_emai_verifiedforl;
    }

    public void setIs_emai_verifiedforl(String is_emai_verifiedforl) {
        this.is_emai_verifiedforl = is_emai_verifiedforl;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
    public boolean isLogin_status() {
        return login_status;
    }

    public void setLogin_status(boolean login_status) {
        this.login_status = login_status;
    }

    public boolean isAction_status() {
        return action_status;
    }

    public void setAction_status(boolean action_status) {
        this.action_status = action_status;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isConnection_request_status() {
        return connection_request_status;
    }

    public void setConnection_request_status(boolean connection_request_status) {
        this.connection_request_status = connection_request_status;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}
