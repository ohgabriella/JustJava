package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffes = 0;
    int price = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();

        CheckBox checkBoxCream = (CheckBox) findViewById(R.id.cream);
        boolean hasWhippedCream = checkBoxCream.isChecked();

        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = checkBoxChocolate.isChecked();

        orderSumary(price, hasWhippedCream, hasChocolate);
    }

    public void orderSumary(int price, boolean hasWhippedCream, boolean hasChocolate) {
        displaySumary("Quantity: " + numberOfCoffes  + "\n"
                + "Total: $" + price + "\n"
                + "Has whipped cream: " + hasWhippedCream + "\n"
                + "Has chocolate: " + hasChocolate + "\n"
                + "Thank you!"
        );
    }

    private int calculatePrice() {
       return numberOfCoffes * price;
    }

    public void increment(View view) {
        numberOfCoffes++;
        display(numberOfCoffes);
    }

    public void decrement(View view) {
        numberOfCoffes--;
        display(numberOfCoffes);
    }

    private void display(int value) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + value);
    }

    private void displaySumary(String value) {
        TextView priceTextView = findViewById(R.id.sumary_text_view);
        priceTextView.setText("" + value);
    }

}