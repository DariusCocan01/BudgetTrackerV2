package com.example.budgettrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String category;
    int expense;
    private Button addButton;
    private Button viewDetails;
    private EditText addExpense;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        addButton = findViewById(R.id.add_button);
        viewDetails = findViewById(R.id.viewDetailsButton);

        addExpense = findViewById(R.id.addexpense);


        DataBase dataBase = new DataBase(MainActivity.this);
        //

        String [] vCat = {"Food","Utilities","Rent","Car","Going out","House","Others"};
        int [] vExpenses = {0,0,0,0,0,0,0};
        Calendar calendar1 = Calendar.getInstance();
        int month1 = calendar1.get(Calendar.MONTH)+1;
        List<MyData> dataFromCurrentMonth = dataBase.getAll();

        for(int i = 0;i<dataFromCurrentMonth.size();i++){
            MyData e = dataFromCurrentMonth.get(i);
            if (e.getMonth() == month1) {
                if (e.getCategory().equals("Food")) vExpenses[0] += e.getExpense();
                if (e.getCategory().equals("Utilities")) vExpenses[1] += e.getExpense();
                if (e.getCategory().equals("Rent")) vExpenses[2] += e.getExpense();
                if (e.getCategory().equals("Car")) vExpenses[3] += e.getExpense();
                if (e.getCategory().equals("Going out")) vExpenses[4] += e.getExpense();
                if (e.getCategory().equals("House")) vExpenses[5] += e.getExpense();
                if (e.getCategory().equals("Others")) vExpenses[6] += e.getExpense();
            }
        }



        //

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyData myData;
                String input = addExpense.getText().toString();
                if (!input.isEmpty()) {
                    expense = Integer.parseInt(input);
                    addExpense.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                //Toast.makeText(MainActivity.this, category,Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based, so add 1
                int year = calendar.get(Calendar.YEAR);

                // Display the current date in a Toast message
                //String message = "Current Date: " + day + "/" + month + "/" + year + "Expense"+ expense;
                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                myData = new MyData(day,month,year,expense,category);
                //Toast.makeText(MainActivity.this, myData.toString(), Toast.LENGTH_SHORT).show();



                boolean succes = dataBase.addOne(myData);
                Toast.makeText(getApplicationContext(),"Succes= "+ succes, Toast.LENGTH_SHORT).show();

            }
        });
        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                //view.getContext().startActivity(intent);
                DataBase dataBase = new DataBase(MainActivity.this);
                List<MyData> allData = dataBase.getAll();
                Toast.makeText(MainActivity.this,allData.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.category = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), category,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
//    public void vectorsExpenses(List<MyData> data){
//        Calendar calendar1 = Calendar.getInstance();
//        int month1 = calendar1.get(Calendar.MONTH)+1;
//
//        for(int i = 0;i<data.size();i++){
//            MyData e = data.get(i);
//            if(e.getMonth()== month1 ) {
//                if (e.getCategory() == "Food") vExpenses[0] =  1;
//                if (e.getCategory() == "Utilities") vExpenses[1] += e.getExpense();
//                if (e.getCategory() == "Rent") vExpenses[2] += e.getExpense();
//                if (e.getCategory() == "Car") vExpenses[3] += e.getExpense();
//                if (e.getCategory() == "Going out") vExpenses[4] += e.getExpense();
//                if (e.getCategory() == "House") vExpenses[5] += e.getExpense();
//                if (e.getCategory() == "Others") vExpenses[6] += e.getExpense();
//            }
//        }
//
//    }

}