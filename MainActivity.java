package com.example.adilbekramazanov.todolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.adilbekramazanov.todolist.m_Realm.Model;
import com.example.adilbekramazanov.todolist.m_Realm.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


//    vars
//
    private ArrayList<String> notes = new ArrayList<>();

    Realm realm;
    ArrayAdapter adapter;
    Spinner sp;
    EditText editText;
    Button button;
    RealmConfiguration realmConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        editText = findViewById(R.id.textInput);
        button = findViewById(R.id.saveButton);


        Realm.init(this);

        realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        RealmHelper helper = new RealmHelper(realm);

        notes = helper.retrieve();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initRecyclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(notes,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void saveText(View view){
        Model m = new Model();

        if (editText.getText().toString().isEmpty() == false){
            m.setTextName(editText.getText().toString());
            RealmHelper helper = new RealmHelper(realm);
            helper.save(m);
            editText.setText("");
            notes = helper.retrieve();
            initRecyclerView();
        }
        else{
            Toast.makeText(this, "Why would you want to add an empty note?",Toast.LENGTH_SHORT).show();
        }
    }

}

