package com.example.adilbekramazanov.todolist.m_Realm;

import io.realm.RealmObject;

public class Model extends RealmObject {

    private long id;

    private String textName;

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
