package com.example.tra.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tra.restaurant.adapter.RestaurantAdapter;
import com.example.tra.restaurant.helper.DatabaseHelper;
import com.example.tra.restaurant.model.Restaurant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.createDefaultRestaurant();

        ArrayList<Restaurant> listRes = databaseHelper.getAllRestaurant();
        Log.d("BacHK", "onCreate: size = " + listRes.size());

        listView = findViewById(R.id.listView);

        RestaurantAdapter adapter = new RestaurantAdapter(listRes,this);
        listView.setAdapter(adapter);


    }
}
