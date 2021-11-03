package com.example.sqlitedatabase_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, title, author, pages;
    private CardClickListener cardClickListener;
    public MyAdapter(Context context, ArrayList id, ArrayList title, ArrayList author, ArrayList pages,CardClickListener cardClickListener) {
        this.context = context;
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.cardClickListener = cardClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtId.setText(String.valueOf(id.get(position)));
        holder.txtTitle.setText(String.valueOf(title.get(position)));
        holder.txtAuthor.setText(String.valueOf(author.get(position)));
        holder.txtPages.setText(String.valueOf(pages.get(position)));
        holder.updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //when you click on card then control will go from
                cardClickListener.onCardClick(position);    //here to where we are listening
                //so moved that block to activity because some lines of code needs to be written inside activity only
                //To fix this issue we ued Interface concept which is very important..
               //writing this block inside activity is better practice..you have to use interface here..and write code in Activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtId, txtTitle, txtAuthor, txtPages;
        ConstraintLayout updateLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPages = itemView.findViewById(R.id.txtPages);
            updateLayout = itemView.findViewById(R.id.updateLayout);
        }
    }
}
