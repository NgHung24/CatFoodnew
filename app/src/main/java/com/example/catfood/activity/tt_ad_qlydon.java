package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.tt_Adapter_giohang_itemsp;
import com.example.catfood.adapter.tt_adap_ad_qlydoncuslvitem;
import com.example.catfood.model.ChiTietDonHang;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.donhang;

import java.util.ArrayList;

public class tt_ad_qlydon extends Activity {
    ImageButton imgbt_back,bt_timkiemdon;
    EditText ed_timkiemdon;
    ListView lv_donhang;
    DataBaseCuaTuan databasecuatuan;
    ArrayList<donhang> donhangad;
    tt_adap_ad_qlydoncuslvitem adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_ad_qlydon);
        imgbt_back = findViewById(R.id.imgbt_back);
        bt_timkiemdon = findViewById(R.id.bt_timkiemdon);
        ed_timkiemdon = findViewById(R.id.ed_timkiemdon);
        lv_donhang = findViewById(R.id.lv_donhang);

        databasecuatuan = new DataBaseCuaTuan(tt_ad_qlydon.this);
        donhangad = databasecuatuan.hienthidonhang();

        // Đảm bảo adapter sử dụng layout của từng item
        adapter = new tt_adap_ad_qlydoncuslvitem(this, R.layout.tt_ad_qlydoncuslv, donhangad);
        lv_donhang.setAdapter(adapter);
        bt_timkiemdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = ed_timkiemdon.getText().toString().trim();

                // Kiểm tra nếu EditText không rỗng
                if (!searchQuery.isEmpty()) {
                    // Tìm kiếm đơn hàng
                    donhangad = databasecuatuan.timKiemDonHang(searchQuery);
                    // Cập nhật adapter với danh sách mới
                    adapter = new tt_adap_ad_qlydoncuslvitem(tt_ad_qlydon.this, R.layout.tt_ad_qlydoncuslv, donhangad);
                    lv_donhang.setAdapter(adapter);
                } else {
                    // Nếu EditText rỗng, hiển thị tất cả đơn hàng
                    donhangad = databasecuatuan.hienthidonhang();
                    adapter = new tt_adap_ad_qlydoncuslvitem(tt_ad_qlydon.this, R.layout.tt_ad_qlydoncuslv, donhangad);
                    lv_donhang.setAdapter(adapter);
                }
            }
        });
        imgbt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tt_ad_qlydon.this, aadminCenter.class);
                startActivity(intent);
            }
        });


    }


}
