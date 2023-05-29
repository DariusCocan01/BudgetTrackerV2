package com.example.budgettrackerv2;

public class MyData {
    private int day;
    private int month;
    private int year;
    private int expense;
    private String category;

    public MyData(int day, int month, int year, int expense, String category) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.expense = expense;
        this.category = category;
    }

    @Override
    public String toString() {
        return expense + "RON for " + category + " at " + day + "."+ month+"."+year;
                /*"day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", expense=" + expense +
                ", category='" + category + '\'' +
                '}';*/
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
