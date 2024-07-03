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
import com.example.shoppingapp.databinding.ViewholderCartBinding;
import com.example.shoppingapp.databinding.ViewholderPupListBinding;
import com.example.shoppingapp.helper.ChangeNumberItemsListener;
import com.example.shoppingapp.helper.ManagmentCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<PopularDomains> items;
    Context context;
    ViewholderCartBinding binding;
    ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<PopularDomains> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        managmentCart = new ManagmentCart(context);
        return new Viewholder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
          binding.titleTxt.setText(items.get(position).getTitle());
          binding.feeEachItem.setText("$" + items.get(position).getPrice());
          binding.totalEachItem.setText("$" + Math.round(items.get(position).getNumberInCart()*items.get(position).getPrice()));
          binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));


          @SuppressLint("DiscouragedApi") int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
                  ,"drawable",holder.itemView.getContext().getPackageName());

          Glide.with(context)
                  .load(drawableResourced)
                  .transform(new GranularRoundedCorners(30,30,0,0))
                  .into(binding.pic);

          binding.plusCartBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  managmentCart.plusNumberItem(items, position, new ChangeNumberItemsListener() {
                      @Override
                      public void change() {
                             notifyDataSetChanged();
                             changeNumberItemsListener.change();
                      }
                  });
              }
          });

          binding.minusCartBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  managmentCart.minusNumberItem(items, position, new ChangeNumberItemsListener() {
                      @Override
                      public void change() {
                          notifyDataSetChanged();
                          changeNumberItemsListener.change();
                      }
                  });
              }
          });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull  ViewholderCartBinding viewholderCartBinding) {
            super(viewholderCartBinding.getRoot());
        }
    }
}
