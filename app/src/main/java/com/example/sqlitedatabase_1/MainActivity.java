package com.example.sqlitedatabase_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    MyDataBaseHelper db;
    ArrayList<String> id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        db = new MyDataBaseHelper(getApplicationContext());
        id = new ArrayList<>();
        title = new ArrayList<>();
        author = new ArrayList<>();
        pages = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(getApplicationContext(), id, title, author, pages, new CardClickListener() {
            @Override
            public void onCardClick(int position) { //Here we are listening ..control will come to here
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("author", String.valueOf(author.get(position)));
                intent.putExtra("pages", String.valueOf(pages.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        readAllData();
    }

    void readAllData() {
        Cursor cursor = db.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                author.add(cursor.getString(2));
                pages.add(cursor.getString(3));
            }
        }
    }
}