package com.example.catfood.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.activity.tt_ad_qlydonchitiet;
import com.example.catfood.activity.tt_h_activity_subitemcenter;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.donhang;

import java.util.ArrayList;

public class tt_adap_ad_qlydoncuslvitem extends ArrayAdapter<donhang> {
    private Activity context;
    private int LayoutDonhang;
    private ArrayList<donhang> listdonhang;
    private DataBaseCuaTuan db;

    public tt_adap_ad_qlydoncuslvitem(Activity context, int LayoutDonhang, ArrayList<donhang> listdonhang) {
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
        Spinner sp_trangthai = convertView.findViewById(R.id.sp_trangthai);

        // Hiển thị thông tin đơn hàng
        txt_madon_qlydon.setText(dh.getMdon());
        t_tenkh.setText("Tên: " + dh.getTkh());
        t_sdtkh.setText("SĐT: " + dh.getSodtkh());
        t_emailkh.setText("Email: " + dh.getEemailkh());
        t_diachikh.setText("Địa chỉ: " + dh.getDchikh());
        t_pttt.setText("PTTT: " + dh.getPthucttoan());
        t_tongtien.setText("Tổng tiền: " + dh.getTtien());

        // Thiết lập danh sách trạng thái và chọn trạng thái hiện tại của đơn hàng
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.trangthai_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trangthai.setAdapter(adapter);

        // Cài đặt trạng thái hiện tại trong Spinner
        if (dh.getTthai() != null) {
            int positionTrangthai = adapter.getPosition(dh.getTthai());
            sp_trangthai.setSelection(positionTrangthai);
        }

        // Thiết lập listener để cập nhật trạng thái trong bảng donhang
        sp_trangthai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int selectedPosition, long id) {
                String trangthai = parent.getItemAtPosition(selectedPosition).toString();

                // Cập nhật trạng thái trong cơ sở dữ liệu
                boolean updateStatus = db.updateDonHang(dh.getMdon(), trangthai);
                if (updateStatus) {
                    Toast.makeText(context, "Cập nhật trạng thái thành công!", Toast.LENGTH_SHORT).show();
                    dh.setTthai(trangthai);
                } else {
                    Toast.makeText(context, "Cập nhật trạng thái thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không có hành động khi không chọn gì
            }
        });

        convertView.setOnClickListener(v -> {
            donhang DH = getItem(position);
            Intent intent = new Intent(context, tt_ad_qlydonchitiet.class);
            intent.putExtra("MADON", DH.getMdon());
            context.startActivity(intent);
        });

        return convertView;
    }
}
