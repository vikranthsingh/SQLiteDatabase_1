package com.example.sqlitedatabase_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText etTileUpdate, etAuthorUpdate, etPagesUpdate;
    Button btnUpdate;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etTileUpdate = findViewById(R.id.etTileUpdate);
        etAuthorUpdate = findViewById(R.id.etAuthorUpdate);
        etPagesUpdate = findViewById(R.id.etPagesUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        getDataFromIntent();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAllData();
            }
        });
    }

    void getDataFromIntent() {
        Intent intent = getIntent();
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
            author = intent.getStringExtra("author");
            pages = intent.getStringExtra("pages");
            etTileUpdate.setText(title);
            etAuthorUpdate.setText(author);
            etPagesUpdate.setText(pages);
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void updateAllData() {
        MyDataBaseHelper db = new MyDataBaseHelper(getApplicationContext());
        db.updateData(getApplicationContext(), id, title, author, pages);
    }
}