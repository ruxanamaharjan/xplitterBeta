package com.example.x_splitter;

public class EventInfo {
    String ID;
    String EventName;

    public EventInfo(String groupID) {
        GroupID = groupID;
    }

    String GroupID;

    EventInfo(){
    }

    public EventInfo(String ID, String eventName, String groupID) {
        this.ID = ID;
        EventName = eventName;
        GroupID = groupID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }
}
