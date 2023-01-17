package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.bankapp.Adapters.BankListAdapter;
import com.mhs.bankapp.Model.BankListModel;
import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;

import java.util.ArrayList;

public class ViewBankList extends AppCompatActivity {
    private RecyclerView bank_list_recycler_view;
    private ArrayList<BankListModel> bankListModels;
    private TextView toolbarText;
    private ImageView imgBack;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bank_list);

        initViews();
    }
    private void initViews() {
        toolbarText = findViewById(R.id.toolbarText);
        imgBack = findViewById(R.id.imgBack);
        bank_list_recycler_view = findViewById(R.id.bank_list_recycler_view);

        toolbarText.setText("Bank List");
        bankListModels = new ArrayList<>();

        //SQL db initialization
        dbHelper = new DataBaseHelper(this);

        //recycler view initialization
        bank_list_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        //get item from bd and pass value in adapter
        Cursor cursor = dbHelper.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(ViewBankList.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()){
            BankListModel model = new BankListModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            bankListModels.add(model);
        }

        BankListAdapter bankListAdapter = new BankListAdapter(bankListModels,this);
        bank_list_recycler_view.setAdapter(bankListAdapter);

        imgBack.setOnClickListener(view -> {
            finish();
        });
    }

}