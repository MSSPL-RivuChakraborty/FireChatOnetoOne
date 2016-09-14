package com.massoftind.rnd.firechatonetoone.interfaces;

import org.json.JSONObject;

public interface ServerResponse {

    void getJsonResponse(JSONObject json);
    void isNetWorkAvailable(boolean status);
}
