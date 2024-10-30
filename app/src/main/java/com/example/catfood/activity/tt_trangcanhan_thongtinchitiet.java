package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.taikhoan;

public class tt_trangcanhan_thongtinchitiet extends Activity {
    EditText ed_tencanhan, ed_sdtcanhan, ed_emailcanhan, ed_mkcanhan;
    Button bt_xacnhan, bt_quaylai;
    DataBaseCuaTuan databasecuatuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_trangcanhan_thongtinchitiet);

        Intent intent = getIntent();
        String tentk = intent.getStringExtra("tentk");

        databasecuatuan = new DataBaseCuaTuan(this);

        ed_sdtcanhan = findViewById(R.id.ed_sdtcanhan);
        ed_tencanhan = findViewById(R.id.ed_tencanhan);
        ed_emailcanhan = findViewById(R.id.ed_emailcanhan);
        ed_mkcanhan = findViewById(R.id.ed_mkcanhan);
        bt_xacnhan = findViewById(R.id.bt_xacnhan);
        bt_quaylai = findViewById(R.id.bt_quaylai);

        // Lấy thông tin tài khoản dựa trên số điện thoại
        taikhoan account = databasecuatuan.layThongTinTaiKhoan(tentk);

        if (account != null) {
            // Hiển thị thông tin tài khoản lên các EditText
            ed_tencanhan.setText(account.getName());
            ed_sdtcanhan.setText(String.valueOf(account.getNum()));
            ed_emailcanhan.setText(account.getMail());
            ed_mkcanhan.setText(account.getPass());
        } else {
            // Không tìm thấy tài khoản
            Toast.makeText(this, "Không tìm thấy tài khoản!", Toast.LENGTH_SHORT).show();
        }
    }
}
