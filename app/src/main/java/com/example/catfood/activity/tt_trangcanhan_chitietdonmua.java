package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.tt_adap_ad_qlydonctcuslvitem;
import com.example.catfood.model.ChiTietDonHang;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.donhang;

import java.util.ArrayList;

public class tt_trangcanhan_chitietdonmua extends Activity {
    ListView lv_ctiettcn;
    tt_adap_ad_qlydonctcuslvitem chiTiettcnAdapter;
    ArrayList<ChiTietDonHang> chiTiettcnList;
    DataBaseCuaTuan databasecuatuan;
    ImageButton imgbt_ql;
    TextView t_tenchitiettcn, t_sdtchitiettcn, t_dcchitiettcn,t_emailchitiettcn,t_ptttchitiettcn,t_ttchitiettcn, t_trangthaichitiettcn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_trangcanhan_chitietdonmua);
        String MADON = getIntent().getStringExtra("MADON");
        if (MADON != null) {
            Log.d("tt_ad_qlydonchitiet", "Received MADON: " + MADON);
        } else {
            Log.e("tt_ad_qlydonchitiet", "Received invalid MADON");
            Toast.makeText(this, "Không tìm thấy mã đơn hàng!", Toast.LENGTH_SHORT).show();
            finish(); // Kết thúc activity nếu không có mã đơn
            return;
        }

        // Khởi tạo các view
        imgbt_ql = findViewById(R.id.imgbt_ql);
        t_tenchitiettcn = findViewById(R.id.t_tenchitiettcn);
        t_sdtchitiettcn = findViewById(R.id.t_sdtchitiettcn);
        t_dcchitiettcn = findViewById(R.id.t_dcchitiettcn);
        t_emailchitiettcn = findViewById(R.id.t_emailchitiettcn);
        t_ptttchitiettcn = findViewById(R.id.t_ptttchitiettcn);
        t_ttchitiettcn = findViewById(R.id.t_ttchitiettcn);
        t_trangthaichitiettcn = findViewById(R.id.t_trangthaichitiettcn);
        // Khởi tạo cơ sở dữ liệu
        databasecuatuan = new DataBaseCuaTuan(this);

        // Lấy danh sách chi tiết đơn hàng
        chiTiettcnList = databasecuatuan.hienthiChiTietDonHang(MADON);
        lv_ctiettcn = findViewById(R.id.lv_ctiettcn);
        chiTiettcnAdapter = new tt_adap_ad_qlydonctcuslvitem(this, R.layout.tt_ad_qlydonctcuslvitem, chiTiettcnList);
        lv_ctiettcn.setAdapter(chiTiettcnAdapter);


        donhang dhang = databasecuatuan.getDonHangByMaDon(MADON);
        t_tenchitiettcn.setText("Tên: "+dhang.getTkh());
        t_sdtchitiettcn.setText("SĐT: "+dhang.getSodtkh());
        t_emailchitiettcn.setText("Email: "+dhang.getEemailkh());
        t_dcchitiettcn.setText("Địa chỉ: "+dhang.getDchikh());
        t_ptttchitiettcn.setText("Phương thức thanh toán: "+dhang.getPthucttoan());
        t_trangthaichitiettcn.setText("Trạng thái: "+dhang.getTthai());
        t_ttchitiettcn.setText("Tổng tiền: "+ dhang.getTtien());


        imgbt_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

}
