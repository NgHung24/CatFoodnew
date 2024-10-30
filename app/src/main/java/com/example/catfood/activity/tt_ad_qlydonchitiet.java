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

public class tt_ad_qlydonchitiet extends Activity {
    ListView lvChiTiet;
    tt_adap_ad_qlydonctcuslvitem chiTietAdapter;
    ArrayList<ChiTietDonHang> chiTietList;
    DataBaseCuaTuan databasecuatuan;
    ImageButton imgbt_quaylai;
    TextView t_tenchitiet,t_sdtchitiet,t_dcchitiet,t_emailchitiet,t_ptttchitiet,t_ttchitiet,t_trangthaichitiet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_ad_qlydonchitiet);

        // Nhận mã đơn hàng dạng String từ Intent
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
        imgbt_quaylai = findViewById(R.id.imgbt_quaylai);
        t_tenchitiet = findViewById(R.id.t_tenchitiet);
        t_sdtchitiet = findViewById(R.id.t_sdtchitiet);
        t_dcchitiet = findViewById(R.id.t_dcchitiet);
        t_emailchitiet = findViewById(R.id.t_emailchitiet);
        t_ptttchitiet = findViewById(R.id.t_ptttchitiet);
        t_ttchitiet = findViewById(R.id.t_ttchitiet);
        t_trangthaichitiet = findViewById(R.id.t_trangthaichitiet);
        // Khởi tạo cơ sở dữ liệu
        databasecuatuan = new DataBaseCuaTuan(this);

        // Lấy danh sách chi tiết đơn hàng
        chiTietList = databasecuatuan.hienthiChiTietDonHang(MADON);
        lvChiTiet = findViewById(R.id.lv_ctiet);
        chiTietAdapter = new tt_adap_ad_qlydonctcuslvitem(this, R.layout.tt_ad_qlydonctcuslvitem, chiTietList);
        lvChiTiet.setAdapter(chiTietAdapter);


        donhang dhang = databasecuatuan.getDonHangByMaDon(MADON);
        t_tenchitiet.setText("Tên: "+dhang.getTkh());
        t_sdtchitiet.setText("SĐT: "+dhang.getSodtkh());
        t_emailchitiet.setText("Email: "+dhang.getEemailkh());
        t_dcchitiet.setText("Địa chỉ: "+dhang.getDchikh());
        t_ptttchitiet.setText("Phương thức thanh toán: "+dhang.getPthucttoan());
        t_trangthaichitiet.setText("Trạng thái: "+dhang.getTthai());
        t_ttchitiet.setText("Tổng tiền: "+ dhang.getTtien());
        imgbt_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcn = new Intent(tt_ad_qlydonchitiet.this, tt_ad_qlydon.class);
                startActivity(itcn);
            }
        });


    }
}
