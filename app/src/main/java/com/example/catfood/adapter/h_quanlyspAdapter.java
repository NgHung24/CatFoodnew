package com.example.catfood.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catfood.R;
import com.example.catfood.activity.h_quanlyspsua;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.SanPham;

import java.util.List;

public class h_quanlyspAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private List<SanPham> sanPhamList;
    public h_quanlyspAdapter(Context context, List<SanPham> sanPhamList) {
        super(context, 0, sanPhamList);
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.h_ad_quanlyspcusitem, parent, false);
        }

        SanPham sanPham = getItem(position);

        TextView maSP = convertView.findViewById(R.id.textView_quanlyspcusitem_MaSP);
        TextView tenSP = convertView.findViewById(R.id.textView_quanlyspcusitem_TenSP);
        TextView giaSP = convertView.findViewById(R.id.textView_quanlyspcusitem_Gia);
        TextView soLuong = convertView.findViewById(R.id.textView_quanlyspcusitem_SoLuong);
        TextView theLoai = convertView.findViewById(R.id.textView_quanlyspcusitem_TheLoai);
        ImageView anhSP = convertView.findViewById(R.id.image_quanlyspcusitem_AnhSP);

        // Set text thông tin sản phẩm
        maSP.setText("Mã sản phẩm: " + sanPham.getMaSP());
        tenSP.setText("Tên sản phẩm: " + sanPham.getTenSP());
        giaSP.setText("Giá: " + sanPham.getGia() + "đ");
        soLuong.setText("Số lượng: " + sanPham.getSoLuong());
        theLoai.setText("Thể loại: " + sanPham.getTheLoai());

        // Lấy Resource ID từ tên của file ảnh
        String linkAnh = sanPham.getLinkAnh(); // Tên file ảnh trong thư mục res/drawable
        int resID = convertView.getResources().getIdentifier(linkAnh, "drawable", convertView.getContext().getPackageName());
        anhSP.setImageResource(resID);



        Button btnSua = convertView.findViewById(R.id.button_quanlyspcusitem_Sua);
        Button btnXoa = convertView.findViewById(R.id.button_quanlyspcusitem_Xoa);

        btnSua.setOnClickListener(v -> {
            SanPham SP = getItem(position);
            Intent intent = new Intent(context, h_quanlyspsua.class);
           intent.putExtra("MASP", SP.getMaSP()); // Truyền mã sản phẩm qua Intent
            context.startActivity(intent);
        });

        btnXoa.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?");

            builder.setPositiveButton("Xóa", (dialog, which) -> {
                // Xác định vị trí của sản phẩm trong danh sách dựa trên position
                SanPham sanPhamXoa = getItem(position);

                // Xử lý xóa sản phẩm khỏi cơ sở dữ liệu
                DataBaseCuaTuan database = new DataBaseCuaTuan(getContext());
                database.xoasp(sanPhamXoa.getMaSP());

                // Xử lý xóa sản phẩm khỏi danh sách Adapter
                remove(sanPhamXoa); // Xóa sản phẩm khỏi danh sách Adapter

                // Cập nhật giao diện ListView
                notifyDataSetChanged(); // Thông báo cho Adapter rằng dữ liệu đã thay đổi

                // Hiển thị thông báo khi xóa thành công
                Toast.makeText(getContext(), "Đã xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Hủy", (dialog, which) -> {
                // Đóng hộp thoại khi người dùng chọn Hủy
                dialog.dismiss();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
        return convertView;
    }


}
