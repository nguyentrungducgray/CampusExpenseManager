package com.example.campusexpensemanager.Model;

public class Expenses {
    public  int ExpensesID;
    public  String ExpensesName;
   public  String ExpensesAmount ;
    public  String ExpensesNote;

    public String Date;

    public void setExpensesAmount(String expensesAmount) {
        this.ExpensesAmount = expensesAmount;
    }

    public  void setExpensesID(int expensesID) {
        this.ExpensesID = expensesID;
    }

    public void setExpensesName(String expensesName) {
        this.ExpensesName = expensesName;
    }

    public void setExpensesNote(String expensesNote) {
        this.ExpensesNote = expensesNote;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public int getExpensesID() {
        return ExpensesID;
    }

    public String getExpensesName() {
        return ExpensesName;
    }

    public String getExpensesAmount() {
        return ExpensesAmount;
    }

    public String getExpensesNote() {
        return ExpensesNote;
    }
}
