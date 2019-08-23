package com.example.x_splitter;

public class TransactionInfo2 {
    String amount;
    String date;
    String category;

    public TransactionInfo2(String amount, String date, String category) {
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
