package com.example.sqlitedatabase_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText etTile, etAuthor, etPages;
    Button btnAdd;
    MyDataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTile = findViewById(R.id.etTile);
        etAuthor = findViewById(R.id.etAuthor);
        etPages = findViewById(R.id.etPages);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTile.getText().toString();
                String author = etAuthor.getText().toString();
                int pages = Integer.parseInt(etPages.getText().toString());
                db = new MyDataBaseHelper(getApplicationContext());
                db.addBook(getApplicationContext(), title, author, pages);
            }
        });
    }
}