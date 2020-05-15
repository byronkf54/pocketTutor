package com.example.pockettutor;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class STRAdapter extends RecyclerView.Adapter<STRAdapter.STRHolder> {
    private List<STRDB> data = new ArrayList<>();

    @NonNull
    @Override
    public STRHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_sub_top, parent, false);
        return new STRHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull STRHolder holder, int position) {
        STRDB currentSTR = data.get(position);
        holder.textViewSubject.setText(currentSTR.getSUBJECT());
        holder.textViewTopic.setText(currentSTR.getTOPIC());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<STRDB> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class STRHolder extends RecyclerView.ViewHolder {
        private TextView textViewSubject;
        private TextView textViewTopic;

        public STRHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.text_view_subject);
            textViewTopic = itemView.findViewById(R.id.text_view_topic);
        }
    }
}
