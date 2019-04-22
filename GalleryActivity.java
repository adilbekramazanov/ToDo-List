package com.example.adilbekramazanov.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        editText = findViewById(R.id.chosenNote);

        getIncomingIntent();

    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("note")){
            String note = getIntent().getStringExtra("note");
            setNote(note);
        }

    }
    private void setNote(String note){
        EditText editText = findViewById(R.id.chosenNote);
        editText.setText(note);

    }
    public void deleteNote(View view){
        setNote("");

    }
}
