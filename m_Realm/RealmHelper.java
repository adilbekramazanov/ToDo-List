package com.example.adilbekramazanov.todolist.m_Realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper  {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Save
    public void save(final Model model){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Model m = realm.copyToRealm(model);
            }
        });
    }


    public ArrayList<String> retrieve(){
        ArrayList<String> taskNames = new ArrayList<>();
        RealmResults<Model> models = realm.where(Model.class).findAll();

        for (Model i: models)
            {taskNames.add(i.getTextName());
        }
        return taskNames;
    }
}
