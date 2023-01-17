package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.bankapp.Adapters.BankListAdapter;
import com.mhs.bankapp.Adapters.TransactionListAdapter;
import com.mhs.bankapp.Model.BankListModel;
import com.mhs.bankapp.Model.TransactionListModel;
import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;

import java.util.ArrayList;

public class TransactionsMoney extends AppCompatActivity {
    private TextView toolbarText;
    private ImageView imgBack;
    private RecyclerView transaction_recycler_view;
    private ArrayList<TransactionListModel> transactionListModelArrayList;
    private AppCompatButton btnMakeTransactions;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_money);

        initViews();

    }

    private void initViews() {
        toolbarText = findViewById(R.id.toolbarText);
        imgBack = findViewById(R.id.imgBack);
        transaction_recycler_view = findViewById(R.id.transaction_recycler_view);
        btnMakeTransactions = findViewById(R.id.btnMakeTransactions);

        transactionListModelArrayList = new ArrayList<>();

        //SQL db initialization
        dbHelper = new DataBaseHelper(this);

        //recycler view initialization
        transaction_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        toolbarText.setText("Transactions");

        //get item from bd and pass value in adapter
        Cursor cursor = dbHelper.getTransactions();
        if (cursor.getCount() == 0){
            Toast.makeText(TransactionsMoney.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()){
            TransactionListModel model = new TransactionListModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2),
                    cursor.getString(4), cursor.getString(6), cursor.getString(7));
            transactionListModelArrayList.add(model);
        }

        TransactionListAdapter bankListAdapter = new TransactionListAdapter(transactionListModelArrayList,this);
        transaction_recycler_view.setAdapter(bankListAdapter);

        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnMakeTransactions.setOnClickListener(view -> {
            startActivity(new Intent(TransactionsMoney.this, MakeTransactions.class));
        });
    }

}