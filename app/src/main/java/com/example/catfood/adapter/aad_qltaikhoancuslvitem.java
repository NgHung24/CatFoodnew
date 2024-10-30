package com.example.catfood.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catfood.R;
import com.example.catfood.activity.aad_quanlytaikhoanSua;
import com.example.catfood.model.DataBaseCuaTuan;

import java.io.InputStream;
import java.util.ArrayList;

public class aad_qltaikhoancuslvitem extends ArrayAdapter<DataBaseCuaTuan> {
    private Activity content;
    private ArrayList<DataBaseCuaTuan> ar;
    int layd;

    public aad_qltaikhoancuslvitem(Activity content, int resource, ArrayList<DataBaseCuaTuan> ar) {
        super(content, resource, ar);
        this.content = content;
        this.ar = ar;
        layd = resource;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        LayoutInflater layi = content.getLayoutInflater();
        view = layi.inflate(R.layout.aad_quanlytaikhoancusitem, viewGroup,false);
        ImageView imgtk = view.findViewById(R.id.imgtk);
        TextView etid = view.findViewById(R.id.etid);
        TextView etten = view.findViewById(R.id.etten);
        TextView etpa = view.findViewById(R.id.etpa);
        TextView etchucvu = view.findViewById(R.id.etchucvu);
        Button btnsuatk = view.findViewById(R.id.btnsuatk);
        Button btnxoatk = view.findViewById(R.id.btnxoatk);

        String aa = ar.get(i).getCol_pic();
        Bitmap bitmap = null;
        try {
            // Chuyển String URI thành Uri
            Uri imageUri = Uri.parse(aa);
            // Mở InputStream từ ContentResolver để đọc ảnh từ URI
            InputStream inputStream = content.getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap != null) {
            imgtk.setImageBitmap(bitmap);
        } else {
            imgtk.setImageResource(R.drawable.vd); // Đặt hình ảnh mặc định nếu không tìm thấy ảnh
        }

        String gid = ar.get(i).getCol_id();
        etid.setText("Mã_"+gid);
        etten.setText(ar.get(i).getCol_name());
        etpa.setText(ar.get(i).getCol_pas());
        String chucvu = ar.get(i).getCol_chucvu().equals("Admin")? "Chức vụ: Admin" : "Chức vụ: Khách";
        etchucvu.setText(chucvu);


        btnsuatk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trangsua = new Intent(content, aad_quanlytaikhoanSua.class);
                Bundle cl = new Bundle();
                cl.putString("id", gid);
                trangsua.putExtra("pac",cl);
                content.startActivity(trangsua);
            }
        });

        btnxoatk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseCuaTuan xoatk = new DataBaseCuaTuan(content);
                xoatk.xoatk(content, gid);
            }
        });
        return view;
    }

}