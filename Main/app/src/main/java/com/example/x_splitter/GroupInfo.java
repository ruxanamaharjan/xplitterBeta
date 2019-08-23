package com.example.x_splitter;

public class GroupInfo {
    String ID;
    String GroupName;

    GroupInfo(){

    }

    public GroupInfo(String ID, String GroupName) {
        this.ID = ID;
        this.GroupName = GroupName;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
