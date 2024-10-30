package com.example.catfood.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.aad_qltaikhoancuslvitem;
import com.example.catfood.model.DataBaseCuaTuan;

import java.util.ArrayList;

public class aad_qltaikhoan extends Activity {
    ImageButton btnqltk, imgtimtk;
    Button btnttk, btnxuatdata;
    EditText ettimtk;
    ListView lvtk;
    ArrayList<DataBaseCuaTuan> altk;
    aad_qltaikhoancuslvitem adap;
    DataBaseCuaTuan taikhoan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoan);

        ettimtk = findViewById(R.id.ettimtk);
        btnqltk = findViewById(R.id.btnqltk);
        imgtimtk = findViewById(R.id.imgtimtk);
        btnttk = findViewById(R.id.btnttk);
        btnxuatdata = findViewById(R.id.btnxuatdata);
        lvtk = findViewById(R.id.lvtk);

        // Khởi tạo đối tượng asqltk
        taikhoan = new DataBaseCuaTuan(aad_qltaikhoan.this); // Giả sử bạn cần context để khởi tạo

        altk = new ArrayList<>();
        adap = new aad_qltaikhoancuslvitem(this, R.layout.aad_quanlytaikhoan, altk);
        lvtk.setAdapter(adap);

        xuat();
        btnttk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ithem = new Intent(aad_qltaikhoan.this, aad_qltaikhoanthem.class);
                startActivity(ithem);
            }
        });

        btnxuatdata.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
            }
        });

        btnqltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Nhận thông điệp từ Intent
        Intent intent = getIntent();
        String mes = intent.getStringExtra("tbao");

        // Hiển thị Toast nếu có thông điệp
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
        }
    }
    @SuppressLint("Range")
    public void xuat(){
        String tbname = taikhoan.getTbname(); // Đảm bảo taikhoan không phải là null
        Cursor c = taikhoan.getReadableDatabase().query(tbname, null, null, null, null, null, null);
        altk.clear(); // Xóa dữ liệu cũ trong danh sách

        if (!c.isAfterLast()) {
            while (c.moveToNext()) {
                DataBaseCuaTuan taiKhoan = new DataBaseCuaTuan(aad_qltaikhoan.this);
                taiKhoan.setCol_id(c.getString(c.getColumnIndex("id")));
                taiKhoan.setCol_pic(c.getString(c.getColumnIndex("picture")));
                taiKhoan.setCol_name(c.getString(c.getColumnIndex("name")));
                taiKhoan.setCol_pas(c.getString(c.getColumnIndex("pass")));
                taiKhoan.setCol_chucvu(c.getString(c.getColumnIndex("cv")));
                altk.add(taiKhoan);
            }
        }
        c.close();
        adap.notifyDataSetChanged();
    }
}