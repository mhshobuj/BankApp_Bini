package com.mhs.bankapp.Model;

public class TransactionListModel {
    private int id;
    private String transaction_id;
    private String from_bank_name;
    private String to_bank_name;
    private String transaction_amount;
    private String date_time;

    public TransactionListModel(int id, String transaction_id, String from_bank_name, String to_bank_name, String transaction_amount, String date_time) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.from_bank_name = from_bank_name;
        this.to_bank_name = to_bank_name;
        this.transaction_amount = transaction_amount;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getFrom_bank_name() {
        return from_bank_name;
    }

    public void setFrom_bank_name(String from_bank_name) {
        this.from_bank_name = from_bank_name;
    }

    public String getTo_bank_name() {
        return to_bank_name;
    }

    public void setTo_bank_name(String to_bank_name) {
        this.to_bank_name = to_bank_name;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
