package com.example.shoppingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.shoppingapp.Adapter.CartAdapter;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityCartBinding;
import com.example.shoppingapp.helper.ChangeNumberItemsListener;
import com.example.shoppingapp.helper.ManagmentCart;

public class CartActivity extends AppCompatActivity {
    private ManagmentCart managmentCart;
    double tax;
    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        initList();
        calculatorCart();
        statusBarColor();
    }
    private void statusBarColor() {
        Window window =CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this,R.color.purple_dark));
    }
    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
                  binding.emptyTxt.setVisibility(View.VISIBLE);
                  binding.scrollV.setVisibility(View.GONE);
        }
        else {
                  binding.emptyTxt.setVisibility(View.GONE);
                  binding.scrollV.setVisibility(View.VISIBLE);
        }

        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), new ChangeNumberItemsListener() {
            @Override
            public void change() {
                 calculatorCart();
            }
        }));
    }

    private void calculatorCart(){
        double percentTax = 0.02;
        double delivery = 10;
        tax= (double) Math.round(managmentCart.getTotalFee() * percentTax * 100) / 100;
        double total = (double) Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) /100;
        double itemTotal = (double) Math.round(managmentCart.getTotalFee() *100)/100;
        binding.totalfeeTxt.setText("$" + itemTotal);
        binding.TaxTxt.setText("$" + tax);
        binding.DeliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
    private void setVariable() {

        binding.backBtn.setOnClickListener(v -> {
            //navigate back from current screen to previous screen
            finish();
        });
    }
}