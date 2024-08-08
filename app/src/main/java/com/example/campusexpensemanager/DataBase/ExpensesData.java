package com.example.campusexpensemanager.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.campusexpensemanager.Model.Expenses;

public class ExpensesData extends SQLiteOpenHelper {

    public static final String DB_Name = "Expenses";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "expenses";
    public static final String ID_COL = "id";
    public static final String EXPENSESNAME_COL = "ExpensesName";
    public static final String AMOUNT_COL = "ExpensesAmount";
    public static final String NOTE_COL = "ExpensesNote";

    public static final String DATE_COL = "Date";


    public ExpensesData(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "( "
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EXPENSESNAME_COL + " VARCHAR(60), "
                + AMOUNT_COL + " VARCHAR(200), "
                + NOTE_COL + " VARCHAR(100), "
                + DATE_COL + "  VARCHAR(100))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNewExpenses(String expensesname, String amount, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXPENSESNAME_COL, expensesname);
        values.put(AMOUNT_COL, amount);
        values.put(NOTE_COL, note);

        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert; // tra ve la -1: insert that bai
    }


    @SuppressLint("Range")
    public Expenses getInfoExpenses(String ExpensesName, String ExpensesAmount) {
        Cursor cursor = null;
        Expenses expenses = new Expenses();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            // lay cac du lieu tu cot nao
            String[] columns = {ID_COL, EXPENSESNAME_COL, AMOUNT_COL, NOTE_COL};
            String condition = EXPENSESNAME_COL + " = ? " + " AND " + AMOUNT_COL + " = ? ";
            String[] params = {ExpensesName, ExpensesAmount};
            cursor = db.query(
                    TABLE_NAME,
                    columns,
                    condition,
                    params,
                    null,
                    null,
                    null
            );
            // select id, username,email,phone where username =? AND password =?;
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();//lay ra 1 dong
                // do du lieu vaof model
                expenses.setExpensesID(cursor.getInt(cursor.getColumnIndex(ID_COL)));
                expenses.setExpensesName(cursor.getString(cursor.getColumnIndex(EXPENSESNAME_COL)));
                expenses.setExpensesAmount(cursor.getString(cursor.getColumnIndex(AMOUNT_COL)));
               expenses.setExpensesNote(cursor.getString(cursor.getColumnIndex(NOTE_COL)));
               expenses.setDate(cursor.getString(cursor.getColumnIndex(DATE_COL)));
            }
            db.close();
        } finally {
            assert cursor != null;
            cursor.close();
        }
        return expenses;
    }
}
