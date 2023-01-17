package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mhs.bankapp.R;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout llCreateNewBank, llViewBankList, llTransfer, llBankLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews() {
        //initialization
        llCreateNewBank = findViewById(R.id.llCreateNewBank);
        llViewBankList = findViewById(R.id.llViewBankList);
        llTransfer = findViewById(R.id.llTransfer);
        llBankLocation = findViewById(R.id.llBankLocation);

        llCreateNewBank.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, CreateBank.class));
        });

        llViewBankList.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, ViewBankList.class));
        });

        llTransfer.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, TransactionsMoney.class));
        });

        llBankLocation.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, ShowNearestBank.class));
        });
    }
}