package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffes = 1;

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

        String orderMessage = orderSumary(name, totalPrice, hasWhippedCream, hasChocolate);

        //ao incluir essa parte, a ideia é e apagar os codigos desnecessarios
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displaySumary(orderMessage);
    }

    public String orderSumary(String name, int totalPrice, boolean hasWhippedCream, boolean hasChocolate) {
        return "Name: " + name + "\n"
                + "Quantity: " + numberOfCoffes + "\n"
                + "Total: $" + totalPrice + "\n"
                + "Has whipped cream: " + hasWhippedCream + "\n"
                + "Has chocolate: " + hasChocolate + "\n"
                + getString(R.string.thank_you);
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
        if (numberOfCoffes == 50) {
            Toast.makeText(this, "You cannot have more than 50 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffes++;
        display(numberOfCoffes);
    }

    public void decrement(View view) {
        if (numberOfCoffes == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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