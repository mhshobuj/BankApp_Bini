package com.mhs.bankapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhs.bankapp.Model.TransactionListModel;
import com.mhs.bankapp.R;

import java.util.ArrayList;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.MyViewHolder> {

    private ArrayList<TransactionListModel> transactionListModels;
    private Context context;

    public TransactionListAdapter(ArrayList<TransactionListModel> transactionListModels, Context context) {
        this.transactionListModels = transactionListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_list_card_item, parent, false);
        return new TransactionListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionListAdapter.MyViewHolder holder, int position) {
        holder.txtFromBankName.setText(new StringBuilder(" ").append(transactionListModels.get(position).getFrom_bank_name()));
        holder.txtToBankName.setText(new StringBuilder(" ").append(transactionListModels.get(position).getTo_bank_name()));
        holder.txtDateTime.setText(new StringBuilder(" ").append(transactionListModels.get(position).getDate_time()));
        holder.txtTransactionID.setText(new StringBuilder(" ").append(transactionListModels.get(position).getTransaction_id()));
        holder.txtTransactionAmount.setText(new StringBuilder(" ").append(transactionListModels.get(position).getTransaction_amount()));
    }

    @Override
    public int getItemCount() {
        return transactionListModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtFromBankName, txtToBankName, txtDateTime, txtTransactionID, txtTransactionAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFromBankName = (TextView) itemView.findViewById(R.id.txtFromBankName);
            txtToBankName = (TextView) itemView.findViewById(R.id.txtToBankName);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            txtTransactionID = (TextView) itemView.findViewById(R.id.txtTransactionID);
            txtTransactionAmount = (TextView) itemView.findViewById(R.id.txtTransactionAmount);
        }
    }
}
