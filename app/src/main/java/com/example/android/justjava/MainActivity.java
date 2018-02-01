package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream;
    boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String name = getName();
        addWhippedCream();
        addChocolate();
        displayMessage(createOrderSummary(price, name));
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order
     * @return text summary
     */
    public String createOrderSummary(int price, String name) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal $: " + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method get the name from user
     */
    private String getName() {
        EditText editText = findViewById(R.id.name);
        String name = editText.getText().toString();
        return name;

    }

    /**
     * This method check if chocolate added
     */
    private void addChocolate() {
        CheckBox state = findViewById(R.id.notify_me_checkbox_choc);
        hasChocolate = state.isChecked();
    }

    /**
     * This method check if whipped cream added
     */
    private void addWhippedCream() {
        CheckBox state = findViewById(R.id.notify_me_checkbox);
        hasWhippedCream = state.isChecked();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}