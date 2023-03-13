package com.gamzeberberoglu.renewsapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamzeberberoglu.renewsapp.R;
import com.gamzeberberoglu.renewsapp.model.Model;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private String[] colors = {"#BBFFCC","#BBCCFF","#EEBBFF","#CCBBFF"};

    private ArrayList<Model> cryptoList;

    public RecyclerViewAdapter(ArrayList<Model> list) {
        this.cryptoList = list;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.items,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.bind(cryptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView symbolId;
        TextView priceId;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(Model model, String[] color, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position %4]));

            symbolId = itemView.findViewById(R.id.symbolId);
            priceId = itemView.findViewById(R.id.priceId);
            symbolId.setText(model.symbol);
            priceId.setText(model.price);

        }
    }
}
