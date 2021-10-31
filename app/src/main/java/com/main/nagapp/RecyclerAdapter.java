package com.main.nagapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Card> cardList;

    public RecyclerAdapter(ArrayList<Card> cardList){
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView question_text;
        private TextView yes_text;
        private TextView no_text;
        private TextView time_text;

        public MyViewHolder(final View view){
            super(view);
            this.question_text = view.findViewById(R.id.question_c);
            this.yes_text = view.findViewById(R.id.yes_response_c);
            this.no_text = view.findViewById(R.id.no_response_c);
            this.time_text = view.findViewById(R.id.time_response_c);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String question = "q: " + this.cardList.get(position).getQuestion();
        String yes = "y: " + this.cardList.get(position).getYes_response();
        String no = "n: " + this.cardList.get(position).getNo_response();
        String time = "t: " + this.cardList.get(position).getTime();

        holder.question_text.setText(question);
        holder.yes_text.setText(yes);
        holder.no_text.setText(no);
        holder.time_text.setText(time);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
