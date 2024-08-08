package com.example.campusexpensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensemanager.DataBase.ExpensesData;
import com.example.campusexpensemanager.Model.Expenses;

public class ExpensesActivity extends AppCompatActivity {

    private ExpensesData expensesData;
     Button btn_save, btn_cancel;

     EditText edt_ExoensesAmount, edt_ExpensesType, edt_ExpensesNote;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add_item);

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        edt_ExoensesAmount = findViewById(R.id.edt_ExpensesAmount);
        edt_ExpensesNote = findViewById(R.id.edt_ExpensesNote);
        edt_ExpensesNote = findViewById(R.id.edt_ExpensesNote);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpenses();
                Intent intent = new Intent(ExpensesActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpensesActivity.this, HomeFragment.class);
            }
        });

    }

    public void addExpenses(){
        String amount = edt_ExoensesAmount.getText().toString().trim();
        String type = edt_ExpensesType.getText().toString().trim();
        String note = edt_ExpensesNote.getText().toString().trim();

        if (TextUtils.isEmpty(amount)){
            edt_ExoensesAmount.setError("Amount can be not empty");
            return;
        }
        if (TextUtils.isEmpty(type)){
            edt_ExpensesType.setError("Type can be not empty");
            return;
        }
        long insert = expensesData.addNewExpenses(amount, type,note);

        if(insert == -1){
            Toast.makeText(ExpensesActivity.this, "Insert Failure", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            Toast.makeText(ExpensesActivity.this, "Insert Successfully", Toast.LENGTH_LONG).show();
        }
    }



}
