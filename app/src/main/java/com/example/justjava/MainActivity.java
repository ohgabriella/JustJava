package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        EditText text = findViewById(R.id.editPersonName);
        String name = text.getText().toString();

        Log.v("MainActivity", "Name: " + name);

        CheckBox checkBoxCream = (CheckBox) findViewById(R.id.cream);
        boolean hasWhippedCream = checkBoxCream.isChecked();

        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = checkBoxChocolate.isChecked();

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        orderSumary(name, totalPrice, hasWhippedCream, hasChocolate);
    }

    public void orderSumary(String name, int totalPrice, boolean hasWhippedCream, boolean hasChocolate) {
        displaySumary(""
                + "Name: " + name + "\n"
                + "Quantity: " + numberOfCoffes + "\n"
                + "Total: $" + totalPrice + "\n"
                + "Has whipped cream: " + hasWhippedCream + "\n"
                + "Has chocolate: " + hasChocolate + "\n"
                + "Thank you!"
        );
    }

    private int calculatePrice(boolean hasWhippedCream, boolean chocolate) {
        int basePrice = 4;

        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (chocolate) {
            basePrice = basePrice + 2;
        }
        return basePrice * numberOfCoffes;
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