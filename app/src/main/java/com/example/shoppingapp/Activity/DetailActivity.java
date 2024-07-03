package com.example.shoppingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Domain.PopularDomains;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityDetailBinding;
import com.example.shoppingapp.helper.ManagmentCart;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private PopularDomains object;
    private int numberOrder =1;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles();
        managmentCart = new ManagmentCart(this);
    }

    private void statusBarColor() {
        Window window = DetailActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailActivity.this,R.color.purple_dark));
    }


    @SuppressLint("SetTextI18n")
    private void getBundles() {
        object = (PopularDomains) getIntent().getSerializableExtra("Object");
        if (object != null) {
            Log.d("DetailActivity", "Object is not null");
            int drawableResourceID = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
            Log.d("DetailActivity", "Drawable Resource ID: " + drawableResourceID);
            Glide.with(this)
                    .load(drawableResourceID)
                    .into(binding.itemPic);

            binding.titleTxt.setText(object.getTitle());
            binding.priceTxt.setText("$" + object.getPrice());
            binding.descrpitionTxt.setText(object.getDescription());
            binding.reviewTxt.setText(object.getReview() + " ");
            binding.ratingTxt.setText(object.getScore() + " ");

            binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    object.setNumberInCart(numberOrder);
                    managmentCart.insertFood(object);
                }
            });

            binding.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        else {
            Log.e("DetailActivity", "Object is null");
        }
    }
}