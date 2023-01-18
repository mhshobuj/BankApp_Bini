package com.mhs.bankapp.SQLdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mhs.bankapp.Common.Constants;

/**
 * Developed by Md Mehedi Hasan
 * Date: 18-01-23
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = Constants.DBNAME;

    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table bank_create(bank_id INTEGER primary key autoincrement, bank_name TEXT, branch_name TEXT, routing_num TEXT)");
        db.execSQL("create Table transaction_info(id INTEGER primary key autoincrement, transaction_id TEXT, from_bank_name TEXT, from_branch_name TEXT, to_bank_name TEXT, to_branch_name TEXT, t_amount TEXT, date_time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists bank_create");
        db.execSQL("drop Table if exists transaction_info");
    }

    public Boolean createNewBank(String bank_name, String branch_name, String routing_num) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_BANK_NAME, bank_name);
        contentValues.put(Constants.TABLE_BRANCH_NAME, branch_name);
        contentValues.put(Constants.TABLE_ROUTING_NUMBER, routing_num);

        long result = myDB.insert(Constants.BANK_CREATE_TABLE_NAME, null, contentValues);

        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateBankInfo(String bank_id, String bank_name, String branch_name, String routing_num) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_BANK_NAME, bank_name);
        contentValues.put(Constants.TABLE_BRANCH_NAME, branch_name);
        contentValues.put(Constants.TABLE_ROUTING_NUMBER, routing_num);

        Cursor cursor = myDB.rawQuery("Select * from bank_create where bank_id = ?", new String[]{bank_id});
        if (cursor.getCount() > 0) {
            long result = myDB.update(Constants.BANK_CREATE_TABLE_NAME, contentValues, "bank_id=?", new String[]{bank_id});

            if (result == -1) return false;
            else
                return true;
        } else {
            return false;
        }
    }

    public Boolean insertTransactionInfo(String transaction_id, String from_bank_name, String from_branch_name, String to_bank_name, String to_branch_name, String t_amount, String date_time) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TRANSACTION_TABLE_ID, transaction_id);
        contentValues.put(Constants.TRANSACTION_FROM_BANK, from_bank_name);
        contentValues.put(Constants.TRANSACTION_FROM_BRANCH, from_branch_name);
        contentValues.put(Constants.TRANSACTION_TO_BANK, to_bank_name);
        contentValues.put(Constants.TRANSACTION_TO_BRANCH, to_branch_name);
        contentValues.put(Constants.TRANSACTION_T_AMOUNT, t_amount);
        contentValues.put(Constants.TRANSACTION_DATE_TIME, date_time);

        long result = myDb.insert(Constants.TRANSACTION_TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from bank_create order by bank_id desc", null);
        return cursor;
    }

    public Cursor getTransactions() {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from transaction_info order by id desc", null);
        return cursor;
    }

    public Cursor getDataByID(String bank_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from bank_create where bank_id= ?", new String[]{bank_id});
        return cursor;
    }

    public int deleteBank(int bank_id) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        return myDb.delete(Constants.BANK_CREATE_TABLE_NAME, "bank_id=?", new String[]{String.valueOf(bank_id)});
    }
}
