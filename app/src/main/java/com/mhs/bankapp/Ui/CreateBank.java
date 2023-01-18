package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;

public class CreateBank extends AppCompatActivity {
    private TextView toolbarText;
    private ImageView imgBack;
    private AppCompatEditText edtBankName, edtBranchName, edtRoutingNum;
    private AppCompatButton btnCreateBank;
    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bank);

        initViews();
    }

    private void initViews() {
        //Id initialization
        toolbarText = findViewById(R.id.toolbarText);
        imgBack = findViewById(R.id.imgBack);

        edtBankName = findViewById(R.id.edtBankName);
        edtBranchName = findViewById(R.id.edtBranchName);
        edtRoutingNum = findViewById(R.id.edtRoutingNum);
        btnCreateBank = findViewById(R.id.btnCreateBank);

        toolbarText.setText("Create Bank");

        //SQL db initialization
        db = new DataBaseHelper(this);

        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnCreateBank.setOnClickListener(view -> {
            CreateNewBank();
        });
    }

    private void CreateNewBank() {
        hideSoftKeyboard(this);
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
        } else {
            //insert data in create bank table
            Boolean insert = db.createNewBank(bankName, branchName, routingNumber);
            if (insert) {
                Toast.makeText(CreateBank.this, "Bank create Successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreateBank.this, HomeActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                Toast.makeText(CreateBank.this, "Failed try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    //keyboardHide method
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}