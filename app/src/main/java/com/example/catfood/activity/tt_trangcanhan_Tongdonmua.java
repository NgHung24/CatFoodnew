package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.tt_adap_donmuatcn;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.donhang;

import java.util.ArrayList;

public class tt_trangcanhan_Tongdonmua extends Activity {
    ImageButton imgbt_b, bt_tkdon;
    EditText ed_tkdon;
    ListView lv_donhangtcn;
    DataBaseCuaTuan databasecuatuan;
    ArrayList<donhang> donhangtcn;
    tt_adap_donmuatcn adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_trangcanhan_tongdonmua);

        // Ánh xạ các thành phần giao diện
        imgbt_b = findViewById(R.id.imgbt_b);
        bt_tkdon = findViewById(R.id.bt_tkdon);
        ed_tkdon = findViewById(R.id.ed_tkdon);
        lv_donhangtcn = findViewById(R.id.lv_donhangtcn);

        Intent intent = getIntent();
        String sdt = intent.getStringExtra("sdt");

        // Khởi tạo đối tượng cơ sở dữ liệu và lấy dữ liệu đơn hàng
        databasecuatuan = new DataBaseCuaTuan(this);
        donhangtcn = databasecuatuan.hienthidonkhach(sdt);

        // Đảm bảo adapter sử dụng layout của từng item
        adapter = new tt_adap_donmuatcn(this, R.layout.tt_trangcanhan_itemcuslvdonmua, donhangtcn);
        lv_donhangtcn.setAdapter(adapter);

        // Xử lý sự kiện quay lại
        imgbt_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Chỉ đóng activity hiện tại thay vì chuyển trang
            }
        });

        // Xử lý sự kiện tìm kiếm đơn hàng
        bt_tkdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = ed_tkdon.getText().toString().trim();

                if (!searchQuery.isEmpty()) {
                    donhangtcn = databasecuatuan.timKiemDonHang(searchQuery);
                } else {
                    donhangtcn = databasecuatuan.hienthidonkhach(sdt);
                }

                // Cập nhật adapter với danh sách mới
                adapter = new tt_adap_donmuatcn(tt_trangcanhan_Tongdonmua.this, R.layout.tt_trangcanhan_itemcuslvdonmua, donhangtcn);
                lv_donhangtcn.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            String sdtReturn = data.getStringExtra("sdt_return");
            if (sdtReturn != null) {
                donhangtcn = databasecuatuan.hienthidonkhach(sdtReturn);
                adapter = new tt_adap_donmuatcn(this, R.layout.tt_trangcanhan_itemcuslvdonmua, donhangtcn);
                lv_donhangtcn.setAdapter(adapter);
            }
        }
    }
}
