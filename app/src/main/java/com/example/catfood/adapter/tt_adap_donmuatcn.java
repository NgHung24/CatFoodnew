package com.example.catfood.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.activity.tt_ad_qlydonchitiet;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.donhang;

import java.util.ArrayList;

public class tt_adap_donmuatcn extends ArrayAdapter<donhang> {
    private Activity context;
    private int LayoutDonhang;
    private ArrayList<donhang> listdonhang;
    private DataBaseCuaTuan db;

    public tt_adap_donmuatcn(Activity context, int LayoutDonhang, ArrayList<donhang> listdonhang) {
        super(context, LayoutDonhang, listdonhang);
        this.context = context;
        this.LayoutDonhang = LayoutDonhang;
        this.listdonhang = listdonhang;
        this.db = new DataBaseCuaTuan(context); // Khởi tạo database
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(LayoutDonhang, parent, false);
        }

        donhang dh = getItem(position);
        TextView txt_madon_qlydon = convertView.findViewById(R.id.txt_madon_qlydon);
        TextView t_tenkh = convertView.findViewById(R.id.t_tenkh);
        TextView t_sdtkh = convertView.findViewById(R.id.t_sdtkh);
        TextView t_emailkh = convertView.findViewById(R.id.t_emailkh);
        TextView t_diachikh = convertView.findViewById(R.id.t_diachikh);
        TextView t_pttt = convertView.findViewById(R.id.t_pttt);
        TextView t_tongtien = convertView.findViewById(R.id.t_tongtien);
        TextView t_trangthai = convertView.findViewById(R.id.t_trangthai);

        // Hiển thị thông tin đơn hàng
        txt_madon_qlydon.setText(dh.getMdon());
        t_tenkh.setText("Tên: " + dh.getTkh());
        t_sdtkh.setText("SĐT: " + dh.getSodtkh());
        t_emailkh.setText("Email: " + dh.getEemailkh());
        t_diachikh.setText("Địa chỉ: " + dh.getDchikh());
        t_pttt.setText("PTTT: " + dh.getPthucttoan());
        t_tongtien.setText("Tổng tiền: " + dh.getTtien());
        t_trangthai.setText("Trạng thái: "+ dh.getTthai());

        convertView.setOnClickListener(v -> {
            donhang DH = getItem(position);
            Intent intent = new Intent(context, tt_ad_qlydonchitiet.class);
            intent.putExtra("MADON", DH.getMdon());
            context.startActivity(intent);
        });

        return convertView;
    }
}
