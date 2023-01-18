package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;

/**
 * Developed by Md Mehedi Hasan
 * Date: 18-01-23
 */
public class BankDetailsAndUpdate extends AppCompatActivity {
    private int bank_id;
    private TextView toolbarText;
    private ImageView imgBack;
    private AppCompatEditText edtBankName, edtBranchName, edtRoutingNum;
    private AppCompatButton btnUpdateInfo;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details_and_update);

        initViews();

        if (getIntent() != null){
            bank_id = getIntent().getIntExtra("BANK_ID", 0);
            setBankValue();
        }
        else {
            Toast.makeText(BankDetailsAndUpdate.this, "Bank ID not found", Toast.LENGTH_LONG).show();
        }
    }

    private void initViews() {
        //Id initialization
        toolbarText = findViewById(R.id.toolbarText);
        imgBack = findViewById(R.id.imgBack);

        edtBankName = findViewById(R.id.edtBankName);
        edtBranchName = findViewById(R.id.edtBranchName);
        edtRoutingNum = findViewById(R.id.edtRoutingNum);
        btnUpdateInfo = findViewById(R.id.btnUpdateInfo);

        //set toolbar name
        toolbarText.setText("Bank Details & Update");

        //SQL db initialization
        dbHelper = new DataBaseHelper(this);

        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnUpdateInfo.setOnClickListener(view -> {
            updateBankInfo();
        });
    }
    private void updateBankInfo() {
        //get data from edit text filed
        String bankName = edtBankName.getText().toString();
        String branchName = edtBranchName.getText().toString();
        String routingNumber = edtRoutingNum.getText().toString();

        String error_txt = "Required!!";
        if (bankName.isEmpty()) {
            edtBankName.requestFocus();
            edtBankName.setError(error_txt);
        } else if (branchName.isEmpty()) {
            edtBranchName.requestFocus();
            edtBranchName.setError(error_txt);
        } else if (routingNumber.isEmpty()) {
            edtRoutingNum.requestFocus();
            edtRoutingNum.setError(error_txt);
        }else {
            //update date
            Boolean updateDate = dbHelper.updateBankInfo(String.valueOf(bank_id),bankName, branchName, routingNumber);
            if (updateDate){
                startActivity(new Intent(BankDetailsAndUpdate.this, ViewBankList.class));
                finish();
                Toast.makeText(BankDetailsAndUpdate.this, "Bank info updated", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(BankDetailsAndUpdate.this, "Not updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    //set value from db
    private void setBankValue() {
        Cursor cursor = dbHelper.getDataByID(String.valueOf(bank_id));
        if (cursor.getCount() == 0){
            Toast.makeText(BankDetailsAndUpdate.this, "Bank Data Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (cursor.moveToNext()){
                edtBankName.setText(new StringBuilder().append(cursor.getString(1)));
                edtBranchName.setText(new StringBuilder().append(cursor.getString(2)));
                edtRoutingNum.setText(new StringBuilder().append(cursor.getString(3)));
            }
        }
    }
}