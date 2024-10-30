package com.example.catfood.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.SanPham;

public class h_quanlyspsua extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_ad_quanlyspsua);

        TextView MaSP = findViewById(R.id.textView_suasp_MaSP);
        EditText TenSP = findViewById(R.id.editText_suasp_TenSP);
        EditText Gia = findViewById(R.id.editText_suasp_Gia);
        EditText SoLuong = findViewById(R.id.editText_suasp_SoLuong);
        EditText MoTa = findViewById(R.id.editText_suasp_MoTa);
        EditText LinkAnh = findViewById(R.id.editText_suasp_LinkAnh);

        RadioButton rdHat = findViewById(R.id.radioButton_quanlyspsua_ThucAnHat);
        RadioButton rdUot = findViewById(R.id.radioButton_quanlyspsua_ThucAnUot);

        Button btCapNhat = findViewById(R.id.Button_suasp_CapNhat);
        Button btQuayLai = findViewById(R.id.Button_suasp_QuayLai);

        String loaiSP = "";

        // Nhận mã sản phẩm từ Intent
        int maSP = getIntent().getIntExtra("MASP", -1);
        if (maSP != -1) {
            Log.d("h_quanlyspsua", "Received MASP: " + maSP);
        } else {
            Log.e("h_quanlyspsua", "Received invalid MASP");
        }

        // Khởi tạo database helper
        DataBaseCuaTuan databaseHelper = new DataBaseCuaTuan(this);

        // Kiểm tra và lấy thông tin sản phẩm nếu mã sản phẩm hợp lệ
        if (maSP != -1) {
            SanPham sanPham = databaseHelper.laySanPhamTheoMa(maSP);
            if (sanPham != null) {
                // Chuyển đổi int thành String trước khi đặt vào setText()
                MaSP.setText(String.valueOf(sanPham.getMaSP()));
                TenSP.setText(sanPham.getTenSP());
                Gia.setText(String.valueOf(sanPham.getGia()));
                SoLuong.setText(String.valueOf(sanPham.getSoLuong()));
                MoTa.setText(sanPham.getMota());
                LinkAnh.setText(sanPham.getLinkAnh());
                loaiSP = sanPham.getTheLoai();
            } else {
                Log.e("h_quanlyspsua", "SanPham not found for MASP: " + maSP);
            }
        }

        if ("ThucAnHat".equals(loaiSP)) {
            rdHat.setChecked(true);
        } else if ("ThucAnUot".equals(loaiSP)) {
            rdUot.setChecked(true);
        }

        btCapNhat.setOnClickListener(v -> {
            String newTenSP = TenSP.getText().toString().trim();
            String newLoaiSP = rdHat.isChecked() ? "ThucAnHat" : "ThucAnUot";
            double newGia;
            int newSoLuong;
            String newMoTa = MoTa.getText().toString().trim();
            String newLinkAnh = LinkAnh.getText().toString().trim();

            try {
                newGia = Double.parseDouble(Gia.getText().toString().trim());
                newSoLuong = Integer.parseInt(SoLuong.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập đúng định dạng cho giá và số lượng!", Toast.LENGTH_SHORT).show();
                return;
            }

            DataBaseCuaTuan db = new DataBaseCuaTuan(this);
            boolean success = db.capnhatsp(maSP, newTenSP, newLoaiSP, newGia, newSoLuong, newMoTa, newLinkAnh);

            if (success) {
                Toast.makeText(this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
            }
        });

        btQuayLai.setOnClickListener(view -> finish());
    }
}
