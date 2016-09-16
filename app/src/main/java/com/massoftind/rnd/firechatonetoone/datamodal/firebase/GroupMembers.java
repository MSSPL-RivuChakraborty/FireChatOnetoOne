package com.massoftind.rnd.firechatonetoone.datamodal.firebase;

/**
 * Created by developer on 16/9/16.
 */
public class GroupMembers {
    String id;
    String groupId;
    String memberId;
    boolean isOwner;

    public GroupMembers() {
    }

    public GroupMembers(String id, String groupId, String memberId, boolean isOwner) {
        this.id = id;
        this.groupId = groupId;
        this.memberId = memberId;
        this.isOwner = isOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }
}
