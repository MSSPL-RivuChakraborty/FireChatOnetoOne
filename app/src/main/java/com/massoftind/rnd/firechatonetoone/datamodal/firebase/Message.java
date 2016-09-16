package com.massoftind.rnd.firechatonetoone.datamodal.firebase;

/**
 * Created by developer on 16/9/16.
 */
public class Message {
    String groupId;
    String senderId;
    String timestamp;
    String message;
    String messageType;
    String thumbUrl;
    String senderName;
    String senderImageUrl;

    public Message() {
    }

    public Message(String groupId, String senderId, String timestamp, String message, String messageType, String thumbUrl, String senderName, String senderImageUrl) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.message = message;
        this.messageType = messageType;
        this.thumbUrl = thumbUrl;
        this.senderName = senderName;
        this.senderImageUrl = senderImageUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderImageUrl() {
        return senderImageUrl;
    }

    public void setSenderImageUrl(String senderImageUrl) {
        this.senderImageUrl = senderImageUrl;
    }
}
