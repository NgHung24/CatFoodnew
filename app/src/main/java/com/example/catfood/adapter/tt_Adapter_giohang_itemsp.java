package com.example.catfood.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.Itemsp;

import java.util.ArrayList;

public class tt_Adapter_giohang_itemsp extends ArrayAdapter<Itemsp> {
    private Activity context;
    private int LayoutItemGioHang;
    private ArrayList<Itemsp> ListGHang;

    public tt_Adapter_giohang_itemsp(Activity context, int layoutitemgiohang, ArrayList<Itemsp> listghang) {
        super(context, layoutitemgiohang, listghang);
        this.context = context;
        this.LayoutItemGioHang = layoutitemgiohang;
        this.ListGHang = listghang;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(LayoutItemGioHang, parent, false); // Sử dụng LayoutItemGioHang
        }
        Itemsp itemsp = getItem(position);

        ImageView imgsp = convertView.findViewById(R.id.img_sp);
        TextView tv_tensp = convertView.findViewById(R.id.tv_tensp);
        TextView tv_giasp = convertView.findViewById(R.id.tv_giasp);
        EditText ed_slg = convertView.findViewById(R.id.ed_slg);
        TextView tv_loaihang = convertView.findViewById(R.id.tv_loaihang);
        Button bt_sua,bt_xoa;
        bt_sua = convertView.findViewById(R.id.bt_sua);
        bt_xoa = convertView.findViewById(R.id.bt_xoa);

        // Trong lớp tt_Adapter_giohang_itemsp
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSoLuong;
                try {
                    newSoLuong = Integer.parseInt(ed_slg.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Số lượng không hợp lệ.", Toast.LENGTH_SHORT).show();
                    return;
                }

                DataBaseCuaTuan db = new DataBaseCuaTuan(context);
                boolean success = db.updateGioHang(itemsp.getTensp(), itemsp.getGiasp(), newSoLuong);

                if (success) {
                    itemsp.setSl(newSoLuong);  // Cập nhật số lượng trong danh sách
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa sản phẩm trong database
                DataBaseCuaTuan db = new DataBaseCuaTuan(context);
                db.xoasp(itemsp.getImage());

                // Xóa sản phẩm khỏi danh sách và cập nhật giao diện
                ListGHang.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }
        });



        String linkAnh = itemsp.getImage();
        int resID = convertView.getResources().getIdentifier(linkAnh, "drawable", convertView.getContext().getPackageName());
        imgsp.setImageResource(resID);
        tv_tensp.setText("Tên sản phẩm: " + itemsp.getTensp());
        tv_giasp.setText("Giá: " + (itemsp.getGiasp() * itemsp.getSl()) + "đ");
        ed_slg.setText(String.valueOf(itemsp.getSl()));
        tv_loaihang.setText(String.valueOf(itemsp.getLoaihang()));


        return convertView;
    }

}
