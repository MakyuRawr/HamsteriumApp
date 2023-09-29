package com.makyu.hamsterium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.widget.ListAdapter;


public class HamsterAdapter extends RecyclerView.Adapter<HamsterAdapter.ViewHolder> {

    private List<Hamster> hamsterList;

    public HamsterAdapter(List<Hamster> hamsterList) {
        this.hamsterList = hamsterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hamster_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hamster hamster = hamsterList.get(position);
        holder.hamsterNameTextView.setText(hamster.getName());
        holder.hamsterImageView.setImageResource(hamster.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return hamsterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView hamsterImageView;
        public TextView hamsterNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            hamsterImageView = itemView.findViewById(R.id.hamster_image_view);
            hamsterNameTextView = itemView.findViewById(R.id.hamster_name_text_view);
        }
    }
}