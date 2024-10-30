package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.catfood.R;

public class aadminCenter extends Activity {
    Button btnqltk, btnaex;
    TextView tvtk;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aadmincenter);

        btnqltk = findViewById(R.id.btnqltk);
        btnaex = findViewById(R.id.btnaex);
        tvtk = findViewById(R.id.tvtk);

        Intent gettk = getIntent();
        Bundle get = gettk.getBundleExtra("pac");
        String ab = get.getString("name");
        tvtk.setText(ab+"");

        btnqltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qltk = new Intent(aadminCenter.this, aad_qltaikhoan.class);
                startActivity(qltk);
            }
        });
        btnaex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}