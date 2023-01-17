package com.mhs.bankapp.Model;

public class BankListModel {
    private int bank_id;
    private String bank_name;
    private String branch_name;
    private String routing_name;

    public BankListModel(int bank_id, String bank_name, String branch_name, String routing_name) {
        this.bank_id = bank_id;
        this.bank_name = bank_name;
        this.branch_name = branch_name;
        this.routing_name = routing_name;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getRouting_name() {
        return routing_name;
    }

    public void setRouting_name(String routing_name) {
        this.routing_name = routing_name;
    }
}
