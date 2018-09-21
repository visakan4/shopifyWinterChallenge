package com.example.visak.shopifyandroidchallenge;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listOfTags;
    TagAdapter tagAdapter;
    Button retry;
    TextView errorText;
    OkHttpClient client = new OkHttpClient();
    public static ArrayList<String> tagArrayList = new ArrayList<>();
    public static JSONArray productList;

    private boolean checkInternetConnectivity(){
        boolean wifiConnected = false;
        boolean mobileConnected = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null){
            int network = networkInfo.getType();
            if (network == ConnectivityManager.TYPE_WIFI){
                wifiConnected = true;
            }
            else if (network == ConnectivityManager.TYPE_MOBILE){
                mobileConnected = true;
            }
        }
        return wifiConnected || mobileConnected;
    }

    private int getInventoryAmount(JSONArray inventory) throws JSONException {
        int length = inventory.length();
        int totalCount = 0;
        for (int i = 0; i < length; i++){
            JSONObject productVariant = inventory.getJSONObject(i);
            totalCount+=(int) productVariant.get("inventory_quantity");
        }
        return totalCount;
    }

    private ArrayList<ProductVariants> getProductVariants(JSONArray variants) throws JSONException{
        int variantsLength = variants.length();
        String title, weightUnit;
        float price, weight;
        int inventoryQuantity;
        ArrayList<ProductVariants> variantsArrayList = new ArrayList<>();
        for (int i = 0; i < variantsLength; i++){
            JSONObject variant = variants.getJSONObject(i);
            title = variant.getString("title").trim();
            price = variant.getLong("price");
            inventoryQuantity = variant.getInt("inventory_quantity");
            weight = variant.getLong("weight");
            weightUnit = variant.getString("weight_unit").trim();
            variantsArrayList.add(new ProductVariants(title,price,inventoryQuantity,weight,weightUnit));
        }
        return variantsArrayList;
    }


    private ArrayList<Product> getTagSpecificProducts(String tag) throws JSONException {
        ArrayList<Product> productDetails = new ArrayList<>();
        int length = productList.length();
        for (int i = 0; i < length; i++){
            JSONObject product = productList.getJSONObject(i);
            if (product.getString("tags").contains(tag)){
                ArrayList<ProductVariants> variants = getProductVariants(product.getJSONArray("variants"));
                productDetails.add(new Product(product.getString("title").trim(),getInventoryAmount(product.getJSONArray("variants")),product.getJSONObject("image").getString("src"),variants));
            }
        }
        return productDetails;
    }


    private void getInput() throws IOException {
        Request request= new Request.Builder().url("https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!(response.isSuccessful())){
                    throw new IOException("Unexpected Response" + response);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        productList = jsonObject.getJSONArray("products");
                        int length = productList.length();
                        for (int i = 0; i < length; i++){
                            JSONObject product = productList.getJSONObject(i);
                            String tag = product.getString("tags");
                            String[] strings = tag.split(",");
                            for (String tagValue:strings) {
                                String trimmedValue = tagValue.trim();
                                if (!(tagArrayList.contains(trimmedValue))){
                                    tagArrayList.add(trimmedValue);
                                }
                            }
                        }
                        Collections.sort(tagArrayList, new Comparator<String>() {
                            @Override
                            public int compare(String s, String t1) {
                                return  s.compareToIgnoreCase(t1);
                            }
                        });
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listOfTags.setVisibility(View.VISIBLE);
                                errorText.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                tagAdapter = new TagAdapter(MainActivity.this,R.layout.list_view_items,tagArrayList);
                                listOfTags.setAdapter(tagAdapter);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retry = (Button) findViewById(R.id.retryButton);
        errorText = (TextView) findViewById(R.id.errorText);
        listOfTags = (ListView) findViewById(R.id.listView);
        retry.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);
        if (checkInternetConnectivity()){
            try {
                getInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            listOfTags.setVisibility(View.INVISIBLE);
            errorText.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInternetConnectivity()) {
                    try {
                        getInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        listOfTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ARRAYLIST",getTagSpecificProducts(tagArrayList.get(i)));
                    intent.putExtra("Products",bundle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tagAdapter.filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
