package com.massoftind.rnd.firechatonetoone.datamodal.firebase;

import java.util.ArrayList;

/**
 * Created by developer on 16/9/16.
 */
public class Group {
    String id;
    boolean isGroup;
    String groupName;
    String createdtimeStamp;
    String ownerId;
    String picUrl;

    public Group() {
    }

    public Group(String id, boolean isGroup, String groupName, String createdtimeStamp, String ownerId, String picUrl) {
        this.id = id;
        this.isGroup = isGroup;
        this.groupName = groupName;
        this.createdtimeStamp = createdtimeStamp;
        this.ownerId = ownerId;
        this.picUrl = picUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatedtimeStamp() {
        return createdtimeStamp;
    }

    public void setCreatedtimeStamp(String createdtimeStamp) {
        this.createdtimeStamp = createdtimeStamp;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
