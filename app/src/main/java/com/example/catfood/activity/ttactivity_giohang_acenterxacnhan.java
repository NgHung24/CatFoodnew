package com.example.catfood.activity;

import static com.example.catfood.model.DataBaseCuaTuan.cl_madon;
import static com.example.catfood.model.DataBaseCuaTuan.tb_name;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.tt_Adap_xacnhanmua;
import com.example.catfood.adapter.tt_Adapter_giohang_itemsp;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.Itemsp;

import java.util.ArrayList;

public class ttactivity_giohang_acenterxacnhan extends Activity {
    ImageButton id_back;
    EditText edit_namekh,edit_sdtkh,edit_emailkh,edit_diachikh;
    ListView lv_spdonhang;
    TextView tv_tongtien;
    RadioButton radio_pthuctt;
    Button bt_dathang;
    DataBaseCuaTuan databasecuatuan;
    ArrayList<Itemsp> listcuatuan;
    tt_Adap_xacnhanmua adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttactivity_giohang_acenterxacnhan);

        lv_spdonhang = findViewById(R.id.lv_spdonhang);
        id_back = findViewById(R.id.id_back);
        edit_namekh = findViewById(R.id.edit_namekh);
        edit_sdtkh = findViewById(R.id.edit_sdtkh);
        edit_emailkh = findViewById(R.id.edit_emailkh);
        edit_diachikh = findViewById(R.id.edit_diachikh);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        radio_pthuctt = findViewById(R.id.radio_pthuctt);
        bt_dathang = findViewById(R.id.bt_dathang);

        databasecuatuan = new DataBaseCuaTuan(ttactivity_giohang_acenterxacnhan.this);
        listcuatuan = databasecuatuan.hienthigiohang(); // Lấy danh sách giỏ hàng từ database

        // Đảm bảo adapter sử dụng layout của từng item
        adapter = new tt_Adap_xacnhanmua(this, R.layout.ttactivity_giohangxacnhan_aitemsp, listcuatuan);
        lv_spdonhang.setAdapter(adapter);
        id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcn = new Intent(ttactivity_giohang_acenterxacnhan.this, ttactivity_giohang_acenter.class);
                startActivity(itcn);
            }
        });
        double Tongtien = tinhTongTien();
        tv_tongtien.setText("Tổng tiền: " + Tongtien + " VND");
        bt_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radio_pthuctt.isChecked()) {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Bạn chưa chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pttt = radio_pthuctt.getText().toString();

                String tenkh = edit_namekh.getText().toString().trim();
                if (tenkh.isEmpty()) {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sdtkh = edit_sdtkh.getText().toString().trim();
                if (sdtkh.isEmpty()) {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailkh = edit_emailkh.getText().toString().trim();
                if (emailkh.isEmpty()) {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }

                String diachikh = edit_diachikh.getText().toString().trim();
                if (diachikh.isEmpty()) {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Bạn chưa nhập địa chỉ nhận hàng", Toast.LENGTH_SHORT).show();
                    return;
                }

                String madon = DataBaseCuaTuan.taoMa(cl_madon, tb_name, databasecuatuan.getWritableDatabase());
                boolean success = databasecuatuan.addDonHang(madon,tenkh, sdtkh, emailkh, diachikh, pttt, Tongtien, "Chờ xác nhận");
                if (success) {

                    for (Itemsp item : listcuatuan) {

                        databasecuatuan.addChiTiet(madon, item.getImage(), item.getTensp(), item.getGiasp(), item.getSl(),Tongtien,item.getLoaihang());
                    }
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    databasecuatuan.xoaGioHang();
                    Intent itcn = new Intent(ttactivity_giohang_acenterxacnhan.this, h_MainActivity.class);
                    startActivity(itcn);
                } else {
                    Toast.makeText(ttactivity_giohang_acenterxacnhan.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                }





            }
        });

    }
    double tinhTongTien() {
        double tongTien = 0;
        for (Itemsp item : listcuatuan) {
            tongTien += item.getGiasp() * item.getSl();
        }
        return tongTien;
    }


}
