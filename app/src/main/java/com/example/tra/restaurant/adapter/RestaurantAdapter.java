package com.example.tra.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tra.restaurant.R;
import com.example.tra.restaurant.model.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends BaseAdapter {

    private ArrayList<Restaurant> listRestaurant;
    private Context context;

    public RestaurantAdapter(ArrayList<Restaurant> listRestaurant, Context context) {
        this.listRestaurant = listRestaurant;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listRestaurant.size();
    }

    @Override
    public Object getItem(int position) {
        return listRestaurant.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.restaurant_item_adapter,null);
            viewHolder.txtTen = convertView.findViewById(R.id.txtTen);
            viewHolder.txtDiaChi = convertView.findViewById(R.id.txtDiaChi);
            viewHolder.txtDanhGia = convertView.findViewById(R.id.txtDanhGia);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Restaurant restaurant = listRestaurant.get(position);
        viewHolder.txtTen.setText(restaurant.getTenNhaHang());
        viewHolder.txtDiaChi.setText(restaurant.getDiaChi());
        viewHolder.txtDanhGia.setText(restaurant.getDiemDanhGia());
        return convertView;
    }

    class ViewHolder {
        TextView txtTen;
        TextView txtDiaChi;
        TextView txtDanhGia;
    }

    public void deleteItem(int position) {
        listRestaurant.remove(position);
        notifyDataSetChanged();
    }
}
