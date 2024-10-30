package com.example.catfood.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.tt_Adapter_giohang_itemsp;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.Itemsp;

import java.util.ArrayList;

public class ttactivity_giohang_acenter extends Activity {
    ListView lv_spthem;
    Button btn_thanhtoan, btn_muatiep;
    DataBaseCuaTuan databasecuatuan;
    ArrayList<Itemsp> listcuatuan;
    tt_Adapter_giohang_itemsp adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttactivity_giohang_acenter);

        // Khởi tạo các thành phần
        lv_spthem = findViewById(R.id.lv_spthem);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        btn_muatiep = findViewById(R.id.btn_muatiep);
        databasecuatuan = new DataBaseCuaTuan(ttactivity_giohang_acenter.this);
        listcuatuan = databasecuatuan.hienthigiohang(); // Lấy danh sách giỏ hàng từ database

        // Đảm bảo adapter sử dụng layout của từng item
        adapter = new tt_Adapter_giohang_itemsp(this, R.layout.ttactivity_giohang_aitemsp, listcuatuan);
        lv_spthem.setAdapter(adapter);


        btn_muatiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcn = new Intent(ttactivity_giohang_acenter.this, h_MainActivity.class);
                startActivity(itcn);
            }
        });

        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcn = new Intent(ttactivity_giohang_acenter.this, ttactivity_giohang_acenterxacnhan.class);
                startActivity(itcn);
            }
        });
    }



}
