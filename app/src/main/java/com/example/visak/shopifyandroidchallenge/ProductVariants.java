package com.example.visak.shopifyandroidchallenge;

import java.io.Serializable;

/**
 * Created by visak on 2018-09-20.
 *
 * Product Variants class
 *
 * Member Variables
 *      1) title - Title of the variant
 *      2) price - Price of the variant
 *      3) inventoryQuantity - Available inventory quantity
 *      4) weight - Weight of the variant
 *      5) weightUnit - Measuring variant of the variants
 *
 */

public class ProductVariants implements Serializable{

    String title;
    float price;
    int inventoryQuantity;
    float weight;
    String weightUnit;

    public ProductVariants(String title, float price, int inventoryQuantity, float weight, String weightUnit){
        this.title = title;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }
}
