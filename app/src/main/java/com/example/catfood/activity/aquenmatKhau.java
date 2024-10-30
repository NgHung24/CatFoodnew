package com.example.catfood.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class aquenmatKhau extends Activity {
    EditText etsdt;
    TextView etkqsdt;
    Button btlaymk, btndn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquenmatkhau);

        etsdt = findViewById(R.id.etsdt);
        etkqsdt = findViewById(R.id.etkqsdt);
        btlaymk = findViewById(R.id.btlaymk);
        btndn = findViewById(R.id.btndn);

        btlaymk.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String getdt = etsdt.getText().toString();
                DataBaseCuaTuan layp = new DataBaseCuaTuan(aquenmatKhau.this);
                String sel = "sdt = ?";
                String[] seldk = { getdt };

                Cursor c = layp.getReadableDatabase().query(layp.getTbname(), null, sel, seldk, null, null, null);
                if (c != null && c.moveToFirst()){
                    String abp = c.getString(c.getColumnIndex("pass"));
                    etkqsdt.setText(abp);
                } else{
                    c.close();
                    etkqsdt.setText("KHÔNG TỒN TẠI!!!!");
                }
                layp.close();
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