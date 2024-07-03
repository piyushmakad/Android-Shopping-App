package com.example.shoppingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.shoppingapp.Adapter.PopularAdapter;
import com.example.shoppingapp.Domain.PopularDomains;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        statusBarColor();
        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomains> items = new ArrayList<>();
        items.add(new PopularDomains("item_1","T-Shirt black",15,4,500,"Unlock the digital realm with our cutting-edge solutions!" +
                " Elevate your online presence, captivate your audience, and drive unparalleled success with our innovative digital tools. Seamlessly blend creativity and technology to " +
                "craft unforgettable experiences for your users. From sleek mobile apps to dynamic websites, we specialize in transforming ideas into digital realities. Join the digital revolution " +
                "today and let us propel your brand to new heights of digital excellence"));

        items.add(new PopularDomains("item_2","Smart Watch",10,4.5,450," Unlock the digital realm with our cutting-edge solutions! " +
                "Elevate your online presence, captivate your audience, and drive unparalleled success with our innovative digital tools. Seamlessly blend creativity and technology to " +
                "craft unforgettable experiences for your users. From sleek mobile apps to dynamic websites, we specialize in transforming ideas into digital realities. Join the digital revolution " +
                "today and let us propel your brand to new heights of digital excellence"));

        items.add(new PopularDomains("item_3","Phone",3,4.9,850,"Unlock the digital realm with our cutting-edge solutions! " +
                "Elevate your online presence, captivate your audience, and drive unparalleled success with our innovative digital tools. Seamlessly blend creativity and technology to " +
                "craft unforgettable experiences for your users. From sleek mobile apps to dynamic websites, we specialize in transforming ideas into digital realities. Join the digital revolution " +
                "today and let us propel your brand to new heights of digital excellence"));

        binding.popularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.popularView.setAdapter(new PopularAdapter(items));

    }
}