package com.massoftind.rnd.firechatonetoone.datamodal;

import java.io.Serializable;

/**
 * Created by Developer on 16-02-2015.
 */
public class Publish implements Serializable {
    private String version="";
    private String developer="";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
