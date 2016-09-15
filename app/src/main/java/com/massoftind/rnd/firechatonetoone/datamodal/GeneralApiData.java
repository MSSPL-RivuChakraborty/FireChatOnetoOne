package com.massoftind.rnd.firechatonetoone.datamodal;

/**
 * Created by Developer on 16-02-2015.
 */
public class GeneralApiData {

    private Status status=new Status();
    private Publish publish=new Publish();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Publish getPublish() {
        return publish;
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }
}
