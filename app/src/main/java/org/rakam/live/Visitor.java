package org.rakam.live;

import java.io.Serializable;

/**
 * Created by buremba on 14/04/15.
 */
public class Visitor implements Serializable {

    private String member_name;
    private String profile_picture;
    private String status;

    public Visitor(String member_name, String profile_picture, String status) {

        this.member_name = member_name;
        this.profile_picture = profile_picture;
        this.status = status;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getProfile_pic_id() {
        return profile_picture;
    }

    public void setProfile_pic_id(String profile_pic_id) {
        this.profile_picture = profile_pic_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return member_name;
    }
}
