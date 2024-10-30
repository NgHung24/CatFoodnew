package com.example.catfood.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.ChiTietDonHang;
import com.example.catfood.model.DataBaseCuaTuan;

import java.util.ArrayList;


public class tt_adap_ad_qlydonctcuslvitem extends ArrayAdapter<ChiTietDonHang> {
    private Activity act1;
    private int lout;
    private ArrayList<ChiTietDonHang> listdonhangchitiet;
    private DataBaseCuaTuan db;

    public tt_adap_ad_qlydonctcuslvitem (Activity act1, int lout, ArrayList<ChiTietDonHang> listdonhangchitiet) {
        super(act1, lout, listdonhangchitiet);
        this.act1 = act1;
        this.lout = lout;
        this.listdonhangchitiet = listdonhangchitiet;
        this.db = new DataBaseCuaTuan(act1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = act1.getLayoutInflater();
            convertView = inflater.inflate(lout, parent, false);
        }
        ChiTietDonHang itemchitiet = getItem(position);

        ImageView img = convertView.findViewById(R.id.img);
        TextView t_tsp = convertView.findViewById(R.id.t_tsp);
        TextView t_gsp = convertView.findViewById(R.id.t_gsp);
        TextView t_slmsp = convertView.findViewById(R.id.t_slmsp);
        TextView t_lhsp = convertView.findViewById(R.id.t_lhsp);



        String linkanh = itemchitiet.getAsp();
        int resid = convertView.getResources().getIdentifier(linkanh,"drawable",convertView.getContext().getPackageName());
        img.setImageResource(resid);
        t_tsp.setText(itemchitiet.getTsp());
        t_gsp.setText("Giá: "+itemchitiet.getGsp());
        t_slmsp.setText("Số lượng: "+itemchitiet.getSlsp());
        t_lhsp.setText(itemchitiet.getLhsp());

        return convertView;
    }
}
