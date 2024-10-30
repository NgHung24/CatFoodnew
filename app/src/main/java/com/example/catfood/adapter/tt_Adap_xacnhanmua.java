package com.example.catfood.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.Itemsp;

import java.util.ArrayList;

public class tt_Adap_xacnhanmua extends ArrayAdapter<Itemsp> {
    private Activity context;
    private int Layout;
    private ArrayList<Itemsp> Listxacnhan;

    public tt_Adap_xacnhanmua(Activity context, int layout, ArrayList<Itemsp> listxacnhan) {
        super(context, layout, listxacnhan);
        this.context = context;
        this.Layout = layout;
        this.Listxacnhan = listxacnhan;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(Layout, parent, false);
        }
        Itemsp itemsp = getItem(position);

        ImageView img_xacnhan = convertView.findViewById(R.id.img_xacnhan);
        TextView tv_tenspxacnhan = convertView.findViewById(R.id.tv_tenspxacnhan);
        TextView tv_xacnhangiamua = convertView.findViewById(R.id.tv_xacnhangiamua);
        TextView tv_xacnhanslmua = convertView.findViewById(R.id.tv_xacnhanslmua);
        TextView txt_loaihang = convertView.findViewById(R.id.txt_loaihang);
        String linkAnh = itemsp.getImage();
        int resID = convertView.getResources().getIdentifier(linkAnh, "drawable", convertView.getContext().getPackageName());
        img_xacnhan.setImageResource(resID);
        tv_tenspxacnhan.setText("Tên sản phẩm: " + itemsp.getTensp());
        tv_xacnhangiamua.setText("Giá: " + (itemsp.getGiasp() * itemsp.getSl()) + "đ");
        tv_xacnhanslmua.setText(String.valueOf(itemsp.getSl()));
        txt_loaihang.setText(String.valueOf(itemsp.getLoaihang()));

        return convertView;
    }
}
