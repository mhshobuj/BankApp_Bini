package com.mhs.bankapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Developed by Md Mehedi Hasan
 * Date: 18-01-23
 */
public class MakeTransactions extends AppCompatActivity {
    private TextView toolbarText;
    private ImageView imgBack;
    private AppCompatSpinner categoryOfFromBankName, categoryOfFromBranchName, categoryOfToBankName, categoryOfToBranchName;
    private AppCompatEditText edtTransactionAmount;
    private AppCompatButton btnSubmit;
    private String transaction_id, selectFromBankName = "", selectToBankName = "", selectFromBranchName = "", selectToBranchName = "", date_time = "";
    private DataBaseHelper dataBaseHelper;
    private final Random myRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_transactions);

        initViews();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, hh:mm:ss aa");
        Date date = new Date();
        date_time = dateFormat.format(date);

        transaction_id = String.valueOf(myRandom.nextInt(10000000));
    }

    private void initViews() {
        toolbarText = findViewById(R.id.toolbarText);
        imgBack = findViewById(R.id.imgBack);
        toolbarText.setText("Make Transactions");

        categoryOfFromBankName = findViewById(R.id.categoryOfFromBankName);
        categoryOfFromBranchName = findViewById(R.id.categoryOfFromBranchName);
        categoryOfToBankName = findViewById(R.id.categoryOfToBankName);
        categoryOfToBranchName = findViewById(R.id.categoryOfToBranchName);

        //db initialization
        dataBaseHelper = new DataBaseHelper(this);

        //create and add static bank name
        final List<String> bankName = new ArrayList<String>();
        bankName.add("Bangladesh Central Bank.");
        bankName.add("Islami Bank of Bangladesh.");
        bankName.add("Dutch-Bangla Bank Limited.");
        bankName.add("Standard Chartered Bank.");
        bankName.add("Brack Bank Limited.");

        setBankName(bankName);

        //create and add static branch name
        final List<String> branchName = new ArrayList<String>();
        branchName.add("Dhanmondi");
        branchName.add("Banani");
        branchName.add("Gulshan");
        branchName.add("Mohakhali");
        branchName.add("Framgate");

        setBranchName(branchName);

        categoryOfFromBankName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectFromBankName = adapterView.getSelectedItem().toString();
                Log.e("FBank", "" + selectFromBankName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        categoryOfFromBranchName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectFromBranchName = adapterView.getSelectedItem().toString();
                Log.e("FBranch", "" + selectFromBranchName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        categoryOfToBankName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectToBankName = adapterView.getSelectedItem().toString();
                Log.e("TBank", "" + selectToBankName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        categoryOfToBranchName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectToBranchName = adapterView.getSelectedItem().toString();
                Log.e("TBranch", "" + selectToBranchName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtTransactionAmount = findViewById(R.id.edtTransactionAmount);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(view -> {
            makeTransaction();
        });

        imgBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void makeTransaction() {
        String t_amount = edtTransactionAmount.getText().toString().trim();
        String error_txt = "Required!!";
        if (selectFromBankName.equals("-- Please Select --")) {
            Toast.makeText(this, "Select from bank name", Toast.LENGTH_SHORT).show();
        } else if (selectFromBranchName.equals("-- Please Select --")) {
            Toast.makeText(this, "Select from branch name", Toast.LENGTH_SHORT).show();
        } else if (selectToBankName.equals("-- Please Select --")) {
            Toast.makeText(this, "Select to bank name", Toast.LENGTH_SHORT).show();
        } else if (selectToBranchName.equals("-- Please Select --")) {
            Toast.makeText(this, "Select to branch name", Toast.LENGTH_SHORT).show();
        } else if (t_amount.isEmpty()) {
            edtTransactionAmount.requestFocus();
            edtTransactionAmount.setError(error_txt);
        } else {
            Boolean checkInsertData = dataBaseHelper.insertTransactionInfo(transaction_id,selectFromBankName, selectFromBranchName,
                    selectToBankName, selectToBranchName, t_amount, date_time);
            if (checkInsertData){
                Toast.makeText(MakeTransactions.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MakeTransactions.this, TransactionsMoney.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(MakeTransactions.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //bank name set in spinner adapter
    private void setBankName(List<String> bankName) {
        bankName.add(0, "-- Please Select --");
        //Style and populate spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item, bankName);
        //Dropdown layout style
        arrayAdapter.setDropDownViewResource(R.layout.layout_spinner_dropdown);
        //Attaching data adapter to spinner
        categoryOfFromBankName.setAdapter(arrayAdapter);
        categoryOfToBankName.setAdapter(arrayAdapter);
    }

    //branch name set in spinner adapter
    private void setBranchName(List<String> branchName) {
        branchName.add(0, "-- Please Select --");
        //Style and populate spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item, branchName);
        //Dropdown layout style
        arrayAdapter.setDropDownViewResource(R.layout.layout_spinner_dropdown);
        //Attaching data adapter to spinner
        categoryOfFromBranchName.setAdapter(arrayAdapter);
        categoryOfToBranchName.setAdapter(arrayAdapter);
    }
}