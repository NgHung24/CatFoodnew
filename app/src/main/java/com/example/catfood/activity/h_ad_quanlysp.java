package com.example.catfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.catfood.R;
import com.example.catfood.adapter.h_quanlyspAdapter;
import com.example.catfood.model.DataBaseCuaTuan;
import com.example.catfood.model.SanPham;

import java.util.ArrayList;

public class h_ad_quanlysp extends Activity {
    private ListView listView;
    private EditText TimKiem;
    private ArrayList<SanPham> originalProductList; // Danh sách gốc chứa tất cả sản phẩm
    private h_quanlyspAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_ad_quanlysp);

        ImageButton btQL = findViewById(R.id.imageButton_quanlysp_QuayLai);
        ImageButton btTimKiem = findViewById(R.id.imageButton_quanlysp_TimKiem);
        Button btThemSP = findViewById(R.id.button_quanlysp_ThemSP);
        Button btLamMoi = findViewById(R.id.button_quanlysp_LamMoiDL);
        TimKiem = findViewById(R.id.editText_quanlysp_TimKiem);

        listView = findViewById(R.id.listView_quanlysp_LV);

        // Lấy dữ liệu từ cơ sở dữ liệu và hiển thị trong ListView
        updateListView();

        btThemSP.setOnClickListener(view -> {
            Intent intent = new Intent(h_ad_quanlysp.this, h_qualyspthem.class);
            startActivity(intent);
        });

        btLamMoi.setOnClickListener(view -> updateListView());

        btTimKiem.setOnClickListener(view -> {
            String keyword = TimKiem.getText().toString().trim();
            if (!keyword.isEmpty()) {
                timkiemSP(keyword);
            } else {
                Toast.makeText(this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức cập nhật ListView với dữ liệu từ cơ sở dữ liệu
    private void updateListView() {
        DataBaseCuaTuan db = new DataBaseCuaTuan(this);
        originalProductList = db.hienthisp(); // Lấy danh sách gốc từ cơ sở dữ liệu
        adapter = new h_quanlyspAdapter(this, originalProductList);
        listView.setAdapter(adapter);
    }

    // Phương thức tìm kiếm sản phẩm dựa trên danh sách gốc và cập nhật ListView
    private void timkiemSP(String keyword) {
        ArrayList<SanPham> filteredList = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Để kiểm tra không phân biệt hoa thường

        for (SanPham sp : originalProductList) {
            // Kiểm tra từ khóa trong các thuộc tính khác nhau
            if (sp.getTenSP().toLowerCase().contains(lowerCaseKeyword) ||
                    String.valueOf(sp.getMaSP()).toLowerCase().contains(lowerCaseKeyword) ||
                    String.valueOf(sp.getGia()).contains(lowerCaseKeyword) ||
                    String.valueOf(sp.getSoLuong()).contains(lowerCaseKeyword) ||
                    sp.getTheLoai().toLowerCase().contains(lowerCaseKeyword)) {

                filteredList.add(sp); // Thêm sản phẩm vào danh sách nếu có thuộc tính nào chứa từ khóa
            }
        }

        // Cập nhật lại adapter với danh sách lọc
        adapter = new h_quanlyspAdapter(this, filteredList);
        listView.setAdapter(adapter);
    }

}
