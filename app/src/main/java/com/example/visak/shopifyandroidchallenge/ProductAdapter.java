package com.example.visak.shopifyandroidchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by visak on 2018-09-20.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    ArrayList<Product> productList = new ArrayList<Product>();
    LayoutInflater layoutInflater;

    public ProductAdapter(Context context, int textViewResourceId, ArrayList<Product> products) {
        super(context, textViewResourceId, products);
        this.context = context;
        this.productList = products;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArrayList<ProductVariants> variantsArrayList;
        StringBuilder variants = new StringBuilder();
        View view = convertView;
        view = layoutInflater.inflate(R.layout.product_list_items, null);
        TextView name = (TextView) view.findViewById(R.id.productName);
        TextView quantity = (TextView) view.findViewById(R.id.inventoryQuantity);
        TextView productVariants = (TextView) view.findViewById(R.id.productVariants);
        ImageView productIcon = (ImageView) view.findViewById(R.id.product_icon);
        Picasso.with(context).load(productList.get(position).getImageUrl()).fit().into(productIcon);
        name.setText(productList.get(position).getProductName());
        quantity.setText("Available Inventory: "+ Integer.toString(productList.get(position).getInventoryQuantity()));
        variantsArrayList = productList.get(position).getProductVariants();
        variants.append("Variants: ");
        for (int i = 0; i < variantsArrayList.size(); i++){
            variants.append(variantsArrayList.get(i).getTitle()).append(", ");
        }
        productVariants.setText(variants.substring(0,variants.length() - 2));
        return view;
    }
}
