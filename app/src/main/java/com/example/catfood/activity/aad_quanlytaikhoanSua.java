package com.example.catfood.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

import java.util.ArrayList;

public class aad_quanlytaikhoanSua extends Activity {
    EditText etname, etnum, etmail, etpic, pa1;
    RadioGroup rgcv;
    RadioButton rba, rbk;
    Button btncn, btnqq;
    ArrayList<DataBaseCuaTuan> al;
    DataBaseCuaTuan sqltk;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoansua);

        etname = findViewById(R.id.etname);
        etnum = findViewById(R.id.etnum);
        etmail = findViewById(R.id.etmail);
        etpic = findViewById(R.id.etpic);
        pa1 = findViewById(R.id.pa1);
        rgcv = findViewById(R.id.rgcv);
        rba = findViewById(R.id.rba);
        rbk = findViewById(R.id.rbk);
        btncn = findViewById(R.id.btncn);
        btnqq = findViewById(R.id.btnqq);

        al = new ArrayList<>();
        sqltk = new DataBaseCuaTuan(aad_quanlytaikhoanSua.this);

        Intent nhan = getIntent();
        Bundle nbd = nhan.getBundleExtra("pac");
        String id = nbd.getString("id");
        Cursor get = sqltk.getWritableDatabase().query(sqltk.getTbname(),null,"id = ?", new String[]{id}, null,null,null);
        get.moveToFirst();
        etname.setText(get.getString(get.getColumnIndex("name")));

        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na = etname.getText().toString();
                sqltk.suatk(aad_quanlytaikhoanSua.this, id, na);
            }
        });
    }
}