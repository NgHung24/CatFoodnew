package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.Itemsp;
import com.example.catfood.model.SanPham;

public class tt_h_activity_subitemcenter extends Activity {
    private SanPham sanPham; // Khai báo biến sanPham ở cấp lớp

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_h_activity_subitemcenter);

        TextView TenSP = findViewById(R.id.textView_subitemcenter_TenSP);
        TextView Gia = findViewById(R.id.textView_subitemcenter_GIA);
        EditText SoLuong = findViewById(R.id.ed_soluong);
        TextView LoaiSP = findViewById(R.id.textView_subitemcenter_LoaiSP);
        TextView ThongTinSP = findViewById(R.id.textView_subitemcenter_ThongTinSP);
        EditText SoMua = findViewById(R.id.editText_subitemcenter_SoMua);
        Button ThemGioHang = findViewById(R.id.Button_subitemcenter_ThemGioHang);
        ImageButton QuayLai = findViewById(R.id.imageButton_subitemcenter_QuayLai);
        ImageView AnhSp = findViewById(R.id.imageView_subitemcenter_AnhSP);
        TextView lhang = findViewById(R.id.textView_subitemcenter_LoaiSP);

        // Nhận mã sản phẩm từ Intent
        int maSP = getIntent().getIntExtra("MASP", -1);
        if (maSP != -1) {
            Log.d("h_activity_subitemcenter", "Received MASP: " + maSP);
        } else {
            Log.e("h_activity_subitemcenter", "Received invalid MASP");
        }

        // Khởi tạo database helper và lấy thông tin sản phẩm
        DataBaseCuaTuan databaseHelper = new DataBaseCuaTuan(this);
        if (maSP != -1) {
            sanPham = databaseHelper.laySanPhamTheoMa(maSP);
            if (sanPham != null) {
                TenSP.setText(sanPham.getTenSP());
                int soLuongMua = SoMua.getText().toString().isEmpty() ? 1 : Integer.parseInt(SoMua.getText().toString());
                double tongGia = sanPham.getGia() * soLuongMua;
                Gia.setText(String.valueOf(tongGia));
                SoLuong.setText(String.valueOf(sanPham.getSoLuong()));

                LoaiSP.setText("Loại sản phẩm: " + sanPham.getTheLoai());
                ThongTinSP.setText("Thông tin: " + sanPham.getMota());

                // Thiết lập ảnh sản phẩm từ link ảnh trong thư mục drawable
                int resID = getResources().getIdentifier(sanPham.getLinkAnh(), "drawable", getPackageName());
                if (resID != 0) {
                    AnhSp.setImageResource(resID);
                } else {
                    Log.e("tt_h_activity_subitemcenter", "Image resource not found for linkAnh: " + sanPham.getLinkAnh());
                    AnhSp.setImageResource(R.drawable.ic_launcher_background); // đặt ảnh mặc định nếu không tìm thấy ảnh
                }
            }
        }

        // Xử lý sự kiện Thêm vào giỏ hàng
        ThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sanPham != null) {
                    String anhsp = sanPham.getLinkAnh();
                    String tensp = TenSP.getText().toString().trim();
                    String giasp = Gia.getText().toString().trim();
                    String slsp = SoMua.getText().toString().trim();
                    String loaihang = lhang.getText().toString().trim();

                    // Chuyển đổi giá và số lượng từ chuỗi sang số
                    double gia;
                    try {
                        gia = Double.parseDouble(giasp);
                    } catch (NumberFormatException e) {
                        Toast.makeText(tt_h_activity_subitemcenter.this, "Giá không hợp lệ.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int soluong;
                    try {
                        soluong = Integer.parseInt(slsp);
                    } catch (NumberFormatException e) {
                        Toast.makeText(tt_h_activity_subitemcenter.this, "Số lượng không hợp lệ.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Kiểm tra số mua không vượt quá số lượng sản phẩm có sẵn
                    if (soluong > sanPham.getSoLuong()) {
                        Toast.makeText(tt_h_activity_subitemcenter.this, "Số lượng mua vượt quá số lượng sản phẩm có sẵn.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Tạo đối tượng cơ sở dữ liệu
                    DataBaseCuaTuan db = new DataBaseCuaTuan(tt_h_activity_subitemcenter.this);

                    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
                    Itemsp existingItem = db.getSanPhamByTen(tensp);
                    if (existingItem != null) {
                        // Nếu sản phẩm đã tồn tại, cộng dồn số lượng
                        int newSoLuong = existingItem.getSl() + soluong;

                        // Kiểm tra nếu tổng số lượng mới không vượt quá số lượng có sẵn
                        if (newSoLuong > sanPham.getSoLuong()) {
                            Toast.makeText(tt_h_activity_subitemcenter.this, "Số lượng cộng dồn vượt quá số lượng sản phẩm có sẵn.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean success = db.updateGioHang(tensp, gia, newSoLuong);

                        if (success) {
                            Toast.makeText(tt_h_activity_subitemcenter.this, "Cập nhật số lượng thành công.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(tt_h_activity_subitemcenter.this, "Cập nhật số lượng thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Nếu sản phẩm không tồn tại, thêm sản phẩm mới
                        boolean success = db.addGioHang(anhsp, tensp, gia, soluong,loaihang);

                        if (success) {
                            Toast.makeText(tt_h_activity_subitemcenter.this, "Thêm sản phẩm thành công.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(tt_h_activity_subitemcenter.this, "Thêm sản phẩm thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // Xóa dữ liệu và chuyển về màn hình chính
                    TenSP.setText("");
                    Gia.setText("");
                    SoLuong.setText("");
                    AnhSp.setImageResource(R.drawable.thumbnailcat1);
                    TenSP.requestFocus();
                    Intent intent = new Intent(tt_h_activity_subitemcenter.this, h_MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(tt_h_activity_subitemcenter.this, "Sản phẩm không tồn tại.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Xử lý sự kiện Quay lại
        QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tt_h_activity_subitemcenter.this, h_MainActivity.class);
                startActivity(intent);
            }
        });
    }
}