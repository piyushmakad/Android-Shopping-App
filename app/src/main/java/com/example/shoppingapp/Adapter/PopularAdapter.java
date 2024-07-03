package com.example.shoppingapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shoppingapp.Activity.DetailActivity;
import com.example.shoppingapp.Domain.PopularDomains;
import com.example.shoppingapp.*;
import com.example.shoppingapp.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<PopularDomains> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularAdapter(ArrayList<PopularDomains> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
          binding.titletxt.setText(items.get(position).getTitle());
          binding.feeTxt.setText("$" + items.get(position).getPrice());
          binding.scoretxt.setText("" + items.get(position).getScore());
          binding.reviewtxt.setText(""+items.get(position).getReview());


          @SuppressLint("DiscouragedApi") int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
                  ,"drawable",holder.itemView.getContext().getPackageName());

          Glide.with(context)
                  .load(drawableResourced)
                  .transform(new GranularRoundedCorners(30,30,0,0))
                  .into(binding.pic);

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, DetailActivity.class);
                  intent.putExtra("Object",items.get(position));
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull  ViewholderPupListBinding viewholderPupListBinding) {
            super(viewholderPupListBinding.getRoot());
        }
    }
}
