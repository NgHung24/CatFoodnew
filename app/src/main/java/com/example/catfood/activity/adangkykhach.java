package com.example.catfood.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class adangkykhach extends Activity {
    EditText etac, etsdt, etemail, etpic,etp;
    Button btndk, btndn;
    DataBaseCuaTuan taikhoan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adangky);

        etac = findViewById(R.id.etac);
        etsdt = findViewById(R.id.etsdt);
        etemail = findViewById(R.id.etemail);
        etp = findViewById(R.id.etp);
        etpic = findViewById(R.id.etpic);
        btndk = findViewById(R.id.btndk);
        btndn = findViewById(R.id.btndn);

        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etac.getText().toString();
                String sdt = etsdt.getText().toString();
                String email = etemail.getText().toString();
                String pic = etpic.getText().toString();
                String pa = etp.getText().toString();

                taikhoan = new DataBaseCuaTuan(adangkykhach.this);
                taikhoan.addtaikhoan(adangkykhach.this, name, sdt, email, pic, "Khách", pa);

                etac.setText("");
                etsdt.setText("");
                etemail.setText("");
                etpic.setText("");
                etp.setText("");
                etac.requestFocus();
            }
        });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}