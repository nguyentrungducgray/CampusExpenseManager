package com.example.campusexpensemanager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.campusexpensemanager.DataBase.ExpensesData;
import com.example.campusexpensemanager.Model.Expenses;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private DrawerLayout drawerLayout;

    private  NavigationView navigationView;
    FloatingActionButton fab;


    private ExpensesData expensesData;

    @SuppressLint({"NonConstantResourceId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fab = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Home);
        }
        replaceFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.menu_Home){
                replaceFragment(new HomeFragment());
            }
            else  if(item.getItemId() == R.id.menu_Expense){
                replaceFragment(new ExpenseFragment());
            }
            else  if(item.getItemId() == R.id.menu_Budget){
                replaceFragment(new BudgetFragment());
            }
            else  if(item.getItemId() == R.id.menu_Profile){
                replaceFragment(new ProfileFragment());
            }

            return true;
        });

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showBottomDialog();
           }
       });


    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout ExpenseLayout = dialog.findViewById(R.id.layoutExpense);
        LinearLayout BudgetLayout = dialog.findViewById(R.id.layoutBudget);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        ExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showExpenseDialog();
               dialog.dismiss();
            }
        });

        BudgetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.nav_Home){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Home);
        }
        else  if(menuItem.getItemId() == R.id.nav_Expense){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ExpenseFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Expense);
        }
        else  if(menuItem.getItemId() == R.id.nav_Budget){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new BudgetFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Budget);
        }
        else  if(menuItem.getItemId() == R.id.nav_Profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Profile);
        }
        return true;
    }

    private void showExpenseDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.expense_add_item);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);

        final View customLayout = getLayoutInflater().inflate(R.layout.expense_add_item, null);
        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);
        EditText edt_ExpensesAmount = customLayout.findViewById(R.id.edt_ExpensesAmount);
        EditText edt_ExpensesType = customLayout.findViewById(R.id.edt_ExpensesType);
        EditText edt_ExpensesNote = customLayout.findViewById(R.id.edt_ExpensesNote);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = edt_ExpensesAmount.getText().toString().trim();
                String type = edt_ExpensesType.getText().toString().trim();
                String note = edt_ExpensesNote.getText().toString().trim();

                if (TextUtils.isEmpty(amount)){
                    edt_ExpensesAmount.setError("Amount can be not empty");
                    return;
                }
                if (TextUtils.isEmpty(type)){
                    edt_ExpensesType.setError("Type can be not empty");
                    return;
                }
                else {
                    long insert = expensesData.addNewExpenses(amount, type,note);

                    if(insert == -1){
                        Toast.makeText(MenuActivity.this, "Insert Failure", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        Toast.makeText(MenuActivity.this, "Insert Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                dialog.dismiss();
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }



}
