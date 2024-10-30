package com.example.catfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.activity.tt_h_activity_subitemcenter;
import com.example.catfood.model.SanPham;

import java.util.List;

public class h_mainAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private List<SanPham> sanPhamList;
    public h_mainAdapter(Context context, List<SanPham> sanPhamList) {
        super(context, 0, sanPhamList);
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.h_activity_mainitema, parent, false);
        }

        SanPham sanPham = getItem(position);


        TextView tenSP = convertView.findViewById(R.id.textView_mainitem_TenSP);
        TextView giaSP = convertView.findViewById(R.id.textView_mainitem_Gia);
        TextView theLoai = convertView.findViewById(R.id.textView_mainitem_TheLoai);
        ImageView anhSP = convertView.findViewById(R.id.img_mainitem_Anh);

        // Set text thông tin sản phẩm

        tenSP.setText("Tên sản phẩm: " + sanPham.getTenSP());
        giaSP.setText("Giá: " + sanPham.getGia() + "đ");
        theLoai.setText("Thể loại: " + sanPham.getTheLoai());

        // Lấy Resource ID từ tên của file ảnh
        String linkAnh = sanPham.getLinkAnh(); // Tên file ảnh trong thư mục res/drawable
        int resID = convertView.getResources().getIdentifier(linkAnh, "drawable", convertView.getContext().getPackageName());
        anhSP.setImageResource(resID);

        // Thêm sự kiện click để chuyển sang h_activity_subitemcenter với mã sản phẩm
        convertView.setOnClickListener(v -> {
            SanPham SP = getItem(position);
            Intent intent = new Intent(context, tt_h_activity_subitemcenter.class);
            intent.putExtra("MASP", SP.getMaSP()); // Truyền mã sản phẩm qua Intent
            context.startActivity(intent);
        });

        return convertView;
    }

}
