package com.example.tra.restaurant;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tra.restaurant.adapter.RestaurantAdapter;
import com.example.tra.restaurant.helper.DatabaseHelper;
import com.example.tra.restaurant.model.Restaurant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.createDefaultRestaurant();

        ArrayList<Restaurant> listRes = databaseHelper.getAllRestaurant();
        Log.d("BacHK", "onCreate: size = " + listRes.size());

        listView = findViewById(R.id.listView);

        adapter = new RestaurantAdapter(listRes,this);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return false;
            }
        });
    }

    public void showAlertDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ThangCoder.Com");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ứ chịu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Không thoát được", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                adapter.deleteItem(position);
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
