package com.example.budgettrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private Button backButton;
    private ListView listView;
    private Button buttonNext;
    private Button buttonPrevious;
    private TextView textMonth;
    private int month;
    private DataBase dataBase;
    private List<MyData> data;
    private TextView textTotalExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);
        textMonth = findViewById(R.id.textMonth);
        textTotalExpenses = findViewById(R.id.textViewTotalExpenses);

        backButton = findViewById(R.id.backButton);

        listView = findViewById(R.id.listView);

        Calendar calendar = Calendar.getInstance();



        month = calendar.get(Calendar.MONTH) + 1;
        dataBase = new DataBase(DetailsActivity.this);
        data = dataBase.getSpendingsFromMonth(month);
        Collections.reverse(data);
        ArrayAdapter myDataAdapter = new ArrayAdapter<MyData>(DetailsActivity.this, android.R.layout.simple_list_item_1, data);
        textMonth.setText(getTheMonth(month-1));
        textTotalExpenses.setText("Total for month "+getTheMonth(month-1)+": "+totalExpenses(data));
        listView.setAdapter(myDataAdapter);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month = month -1;
                if (month == 0) month =12;
                data = dataBase.getSpendingsFromMonth(month);
                Collections.reverse(data);
                ArrayAdapter myDataAdapter = new ArrayAdapter<MyData>(DetailsActivity.this, android.R.layout.simple_list_item_1, data);
                textMonth.setText(getTheMonth(month-1));
                listView.setAdapter(myDataAdapter);
                textTotalExpenses.setText("Total for month "+getTheMonth(month-1)+": "+totalExpenses(data));
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month = month +1;
                if(month==13) month =1;
                data = dataBase.getSpendingsFromMonth(month);
                Collections.reverse(data);
                ArrayAdapter myDataAdapter = new ArrayAdapter<MyData>(DetailsActivity.this, android.R.layout.simple_list_item_1, data);
                textMonth.setText(getTheMonth(month-1));
                listView.setAdapter(myDataAdapter);
                textTotalExpenses.setText("Total for month "+getTheMonth(month-1)+": "+totalExpenses(data));

            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);}
        });
    }
    public String getTheMonth(int i){
        String month = null;
        if(i==0) month = "January";
        if(i==1) month = "February";
        if(i==2) month = "March";
        if(i==3) month = "April";
        if(i==4) month = "May";
        if(i==5) month = "June";
        if(i==6) month = "July";
        if(i==7) month = "August";
        if(i==8) month = "September";
        if(i==9) month = "October";
        if(i==10) month = "November";
        if(i==11) month = "December";
        return month;
    }
    public int totalExpenses(List<MyData> data){
        int total=0;
        for (int i =0;i<data.size();i++){
            MyData element = data.get(i);
            total += element.getExpense();
        }
        return total;
    }
}