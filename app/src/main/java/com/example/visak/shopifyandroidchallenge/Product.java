package com.example.visak.shopifyandroidchallenge;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by visak on 2018-09-20.
 */

public class Product implements Serializable{

    String productName;
    int inventoryQuantity;
    String imageUrl;
    ArrayList<ProductVariants> productVariants;


    public Product(String productName, int inventryQuantity, String imageUrl, ArrayList<ProductVariants> productVariants){
        this.productName = productName;
        this.inventoryQuantity = inventryQuantity;
        this.imageUrl = imageUrl;
        this.productVariants = productVariants;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductVariants(ArrayList<ProductVariants> productVariants) {
        this.productVariants = productVariants;
    }

    public ArrayList<ProductVariants> getProductVariants() {
        return productVariants;
    }

    public String getProductName(){
        return this.productName;
    }

    public int getInventoryQuantity(){
        return this.inventoryQuantity;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}
