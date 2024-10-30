package com.example.catfood.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class adangNhap extends Activity {
    EditText ettk, etmk;
    Button btndn, btndk, btnfg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adangnhap);

        ettk = findViewById(R.id.ettk);
        etmk = findViewById(R.id.etmk);
        btndn = findViewById(R.id.btndn);
        btndk = findViewById(R.id.btndk);
        btnfg = findViewById(R.id.btnfg);

        btndn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String name = ettk.getText().toString();
                String pa = etmk.getText().toString();
                DataBaseCuaTuan check = new DataBaseCuaTuan(adangNhap.this);
                String sel = "name = ? AND pass = ?";
                String[] seldk = {name, pa};
                Cursor c = check.getWritableDatabase().query(check.getTbname(), null, sel, seldk, null, null, null);

                if (c != null && c.moveToFirst()) {
                    String ab = c.getString(c.getColumnIndex("name"));
                    String ad = c.getString(c.getColumnIndex("cv"));
                    Intent intent;
                    if (ad.equals("Admin")) {
                        intent = new Intent(adangNhap.this, aadminCenter.class);
                        Bundle bd = new Bundle();
                        bd.putString("name", ab);
                        intent.putExtra("pac", bd);
                    } else {
                        intent = new Intent(adangNhap.this, h_MainActivity.class);
                        Bundle dl = new Bundle();
                        dl.putString("name", ab); // Đổi tên khóa ở đây
                        intent.putExtra("pac", dl); // Sử dụng cùng một khóa "pac"
                    }
                    startActivity(intent);
                } else {
                    if (c != null) c.close();
                    Toast.makeText(adangNhap.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                }
                ettk.setText("");
                etmk.setText("");
            }
        });

        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dk = new Intent(adangNhap.this, adangkykhach.class);
                startActivity(dk);
            }
        });
        btnfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fg = new Intent(adangNhap.this, aquenmatKhau.class);
                startActivity(fg);
            }
        });
    }
}