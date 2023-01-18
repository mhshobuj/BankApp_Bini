package com.mhs.bankapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.mhs.bankapp.Model.BankListModel;
import com.mhs.bankapp.R;
import com.mhs.bankapp.SQLdb.DataBaseHelper;
import com.mhs.bankapp.Ui.BankDetailsAndUpdate;

import java.util.ArrayList;

/**
 * Developed by Md Mehedi Hasan
 * Date: 18-01-23
 */
public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.MyViewHolder> {
    private ArrayList<BankListModel> bankListModels;
    private Context context;

    public BankListAdapter(ArrayList<BankListModel> bankListModels, Context context) {
        this.bankListModels = bankListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public BankListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bank_list_card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankListAdapter.MyViewHolder holder, int position) {
        holder.txtBankName.setText(new StringBuilder(" ").append(bankListModels.get(position).getBank_name()));
        holder.txtBranchName.setText(new StringBuilder(" ").append(bankListModels.get(position).getBranch_name()));
        holder.txtRoutingNumber.setText(new StringBuilder(" ").append(bankListModels.get(position).getRouting_name()));

        holder.setListener((view, pos) -> {
            Intent intent = new Intent(context, BankDetailsAndUpdate.class);
            intent.putExtra("BANK_ID", bankListModels.get(pos).getBank_id());
            context.startActivity(intent);

        });

        holder.imgDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.conformation);
            builder.setMessage(R.string.conMsg);
            builder.setIcon(android.R.drawable.ic_menu_delete);
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                DataBaseHelper dbHelper = new DataBaseHelper(context);
                int result = dbHelper.deleteBank(bankListModels.get(position).getBank_id());
                if (result > 0) {
                    Toast.makeText(context, "Bank deleted", Toast.LENGTH_SHORT).show();
                    bankListModels.remove(bankListModels.get(position));
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return bankListModels.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtBankName, txtBranchName, txtRoutingNumber;
        ImageView imgDelete;

        RecyclerViewClickListener listener;

        public void setListener(RecyclerViewClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBankName = (TextView) itemView.findViewById(R.id.txtBankName);
            txtBranchName = (TextView) itemView.findViewById(R.id.txtBranchName);
            txtRoutingNumber = (TextView) itemView.findViewById(R.id.txtRoutingNumber);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
