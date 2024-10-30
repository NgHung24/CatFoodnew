package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;

public class aad_qltaikhoanthem extends Activity {
    EditText etname, etnum, etmail, pa1, etpic;
    RadioGroup rgcv;
    RadioButton rba, rbk;
    Button btnxn, btnql;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aad_quanlytaikhoanthem);

        etname = findViewById(R.id.etname); // edittext
        etnum = findViewById(R.id.etnum);
        etmail = findViewById(R.id.etmail);
        etpic = findViewById(R.id.etpic);
        rgcv = findViewById(R.id.rgcv);
        pa1 = findViewById(R.id.pa1);
        btnxn = findViewById(R.id.btnxn);
        btnql = findViewById(R.id.btnql);
        rba = findViewById(R.id.rba);
        rbk = findViewById(R.id.rbk);

        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString().trim();
                String num = etnum.getText().toString().trim();
                String mail = etmail.getText().toString().trim();
                String pic = etpic.getText().toString().trim();
                int rgchon = rgcv.getCheckedRadioButtonId();
                String ck = "";
                if(rgchon == R.id.rba){
                    ck = "Admin";
                }else{
                    ck = "Khách";
                }
                String p1 = pa1.getText().toString().trim();

                DataBaseCuaTuan mydb = new DataBaseCuaTuan(aad_qltaikhoanthem.this);

                mydb.addtaikhoan(aad_qltaikhoanthem.this ,name, num, mail, pic, ck, p1);

                // Xóa nội dung sau khi thêm
                etname.setText("");
                etnum.setText("");
                etmail.setText("");
                etpic.setText("");
                rba.setChecked(false);
                rbk.setChecked(true);
                pa1.setText("");
                etname.requestFocus();
            }
        });

        btnql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ql = new Intent(aad_qltaikhoanthem.this, aad_qltaikhoan.class);
                startActivity(ql);
            }
        });
    }
}