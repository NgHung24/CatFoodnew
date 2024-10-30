package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class tt_trangcanhan_a extends Activity {
    TextView tvname;
    Button btnmua, btncanhan, btnxuat;
    ImageButton btnthoat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_trangcanhan_a);

        tvname = findViewById(R.id.tvname);
        btnthoat = findViewById(R.id.btnthoat);
        btnmua = findViewById(R.id.btnmua);
        btncanhan = findViewById(R.id.btncanhan);
        btnxuat = findViewById(R.id.btnxuat);

        Intent intent = getIntent();
        String gb = intent.getStringExtra("name");
        tvname.setText(gb);

        DataBaseCuaTuan db = new DataBaseCuaTuan(this);
        String sdttkkh = db.laySDTtuTentk(gb);



        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itdm = new Intent(tt_trangcanhan_a.this, tt_trangcanhan_Tongdonmua.class);
                itdm.putExtra("sdt", sdttkkh);
                startActivity(itdm);
            }
        });
        btnxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itxuat = new Intent(tt_trangcanhan_a.this, adangNhap.class);
                startActivity(itxuat);
            }
        });
        btncanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itdm = new Intent(tt_trangcanhan_a.this, tt_trangcanhan_thongtinchitiet.class);
                itdm.putExtra("tentk", gb);
                startActivity(itdm);
            }
        });
    }
}