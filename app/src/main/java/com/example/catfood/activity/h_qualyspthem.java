package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class h_qualyspthem extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_ad_quanlyspthem);

        EditText TenSP = findViewById(R.id.editText_themsp_TenSP);
        EditText Gia = findViewById(R.id.editText_themsp_Gia);
        EditText SoLuong = findViewById(R.id.editText_themsp_SoLuong);
        EditText MoTa = findViewById(R.id.editText_themsp_MoTa);
        EditText LinkAnh = findViewById(R.id.editText_themsp_LinkAnh);

        RadioButton rdHat = findViewById(R.id.radioButton_quanlyspthem_ThucAnHat);
        RadioButton rdUot = findViewById(R.id.radioButton_quanlyspthem_ThucAnUot);

        Button btXacNhan = findViewById(R.id.Button_themsp_XacNhan);
        Button btQuayLai = findViewById(R.id.Button_themsp_QuayLai);

        btQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_qualyspthem.this, h_ad_quanlysp.class);
                startActivity(intent);
            }
        });

        btXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = TenSP.getText().toString().trim();
                String giaStr = Gia.getText().toString().trim();
                String slStr = SoLuong.getText().toString().trim();
                String mota = MoTa.getText().toString().trim();
                String link_anh = LinkAnh.getText().toString().trim();

                // Kiểm tra loại thức ăn dựa trên RadioButton được chọn
                String loai = "";
                if (rdHat.isChecked()) {
                    loai = "ThucAnHat";
                } else if (rdUot.isChecked()) {
                    loai = "ThucAnUot";
                }

                // Kiểm tra nếu các trường đều đã được nhập
                if (tensp.isEmpty() || loai.isEmpty() || giaStr.isEmpty() || slStr.isEmpty() || mota.isEmpty() || link_anh.isEmpty()) {
                    Toast.makeText(h_qualyspthem.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double gia;
                try {
                    gia = Double.parseDouble(giaStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(h_qualyspthem.this, "Giá không hợp lệ.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int soluong;
                try {
                    soluong = Integer.parseInt(slStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(h_qualyspthem.this, "Số lượng không hợp lệ.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng cơ sở dữ liệu và gọi phương thức themsp
                DataBaseCuaTuan db = new DataBaseCuaTuan(h_qualyspthem.this);
                boolean success = db.themsp(tensp, loai, gia, soluong, mota, link_anh);

                // Hiển thị thông báo thành công hoặc thất bại
                if (success) {
                    Toast.makeText(h_qualyspthem.this, "Thêm sản phẩm thành công.", Toast.LENGTH_SHORT).show();
                    // Xóa nội dung các trường nhập sau khi thêm thành công
                    TenSP.setText("");
                    rdHat.setChecked(false);
                    rdUot.setChecked(false);
                    Gia.setText("");
                    SoLuong.setText("");
                    MoTa.setText("");
                    LinkAnh.setText("");

                    TenSP.requestFocus();
                } else {
                    Toast.makeText(h_qualyspthem.this, "Thêm sản phẩm thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
