package com.massoftind.rnd.firechatonetoone.datamodal.firebase;

/**
 * Created by developer on 16/9/16.
 */
public class GroupChatUser {

    String id;
    String firstName;
    String email;
    String profilePicUrl;

    public GroupChatUser(String id, String email, String firstName, String profilePicUrl) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.profilePicUrl = profilePicUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String toString(){

        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}
