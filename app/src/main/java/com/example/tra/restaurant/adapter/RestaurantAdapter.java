package com.example.tra.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.tra.restaurant.R;
import com.example.tra.restaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestaurantAdapter extends BaseAdapter implements Filterable{

    private ArrayList<Restaurant> listRestaurant;
    private Context context;
    private ArrayList<Restaurant> arraylist;

    public RestaurantAdapter(ArrayList<Restaurant> listRestaurant, Context context) {
        this.listRestaurant = listRestaurant;
        this.context = context;
        arraylist = new ArrayList<>();
        arraylist.addAll(listRestaurant);
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

    // Lọc dữ liệu khi Search
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listRestaurant = (ArrayList<Restaurant>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Restaurant> FilteredArrayNames = new ArrayList<Restaurant>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < arraylist.size(); i++) {
                    Restaurant restaurant = arraylist.get(i);
                    if (restaurant.getTenNhaHang().toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(restaurant);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }
        };

        return filter;
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
