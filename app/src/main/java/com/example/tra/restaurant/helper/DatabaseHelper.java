package com.example.tra.restaurant.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.tra.restaurant.constant.Constant;
import com.example.tra.restaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Phiên bản
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "RestaurantManager";


    // Tên bảng: Note.
    private static final String TABLE_NAME = Constant.TABLE_NAME;

    private static final String COLUMN_ID = Constant.COLUMN_ID;
    private static final String COLUMN_NAME = Constant.COLUMN_NAME;
    private static final String COLUMN_ADDRESS = Constant.COLUMN_ADDRESS;
    private static final String COLUMN_DANHGIA = Constant.COLUMN_DANHGIA;
    // Script tạo bảng.
    private static final String CREAT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_DANHGIA + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Chạy lệnh tạo bảng.
        db.execSQL(CREAT_TABLE);
        Log.d("BacHK", "onCreate: called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Và tạo lại.
        onCreate(db);
    }

    // Chèn vào mặc định 6 bản ghi như đề bài.
    public void createDefaultRestaurant()  {
        int count = this.getRestaurantCount();
        if(count ==0 ) {
            Restaurant restaurant1 = new Restaurant("Sen Tây Hồ","514 Lạc Long Quân",String.valueOf(8.6));
            Restaurant restaurant2 = new Restaurant("Nón Lá","Nguyễn Đình Chuyển",String.valueOf(8.8));
            Restaurant restaurant3 = new Restaurant("Quán Ngon Hà Nội","Phan Bội Châu",String.valueOf(8.9));
            Restaurant restaurant4 = new Restaurant("Lục Thủy","Lê Thái Tổ",String.valueOf(8.5));
            Restaurant restaurant5 = new Restaurant("Cham Cham","Phan Văn Chương",String.valueOf(8.2));
            Restaurant restaurant6 = new Restaurant("Ly Club","Lê Phụng Hiếu",String.valueOf(7.8));

            this.addRestaurant(restaurant1);
            this.addRestaurant(restaurant2);
            this.addRestaurant(restaurant3);
            this.addRestaurant(restaurant4);
            this.addRestaurant(restaurant5);
            this.addRestaurant(restaurant6);
        }
    }


    public ArrayList<Restaurant> getAllRestaurant() {

        ArrayList<Restaurant> resList = new ArrayList<Restaurant>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setMaNhaHang(Integer.parseInt(cursor.getString(0)));
                restaurant.setTenNhaHang(cursor.getString(1));
                restaurant.setDiaChi(cursor.getString(2));
                restaurant.setDiemDanhGia(cursor.getString(3));

                // Thêm vào danh sách.
                resList.add(restaurant);
            } while (cursor.moveToNext());
        }

        // return note list
        return resList;
    }

    public void addRestaurant(Restaurant restaurant) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, restaurant.getTenNhaHang());
        values.put(COLUMN_ADDRESS, restaurant.getDiaChi());
        values.put(COLUMN_DANHGIA, restaurant.getDiemDanhGia());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_NAME, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public int updateRes(Restaurant restaurant) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, restaurant.getTenNhaHang());
        values.put(COLUMN_ADDRESS, restaurant.getDiaChi());
        values.put(COLUMN_DANHGIA, restaurant.getDiemDanhGia());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(restaurant.getMaNhaHang())});
    }

    public void deleteRes(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[] { String.valueOf(restaurant.getMaNhaHang()) });
        db.close();
    }

    public int getRestaurantCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
}
