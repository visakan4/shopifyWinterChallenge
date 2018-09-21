package com.example.visak.shopifyandroidchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    ListView productListView;

    /**
     * Product Activity
     *
     * List view which contains the products
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productListView = (ListView) findViewById(R.id.productListView);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Products");
        ArrayList<Product> products = (ArrayList<Product>) bundle.getSerializable("ARRAYLIST");
        ProductAdapter productAdapter = new ProductAdapter(this,R.layout.product_list_items,products);
        productListView.setAdapter(productAdapter);
    }
}
