package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //Stores an information of quantity of cups of coffee
    int quantity = 0;
    //Stores an information is there whipped cream added or not
    boolean hasWhippedCream;
    //Stores an information is there chocolate added or not
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
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.too_many_coffees), Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1 || quantity == 0) {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.too_less_coffees), Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        addWhippedCream();
        addChocolate();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String name = getName();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.just_java_order_name, name));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order
     * @param name  of the user
     * @return text summary
     */
    public String createOrderSummary(int price, String name) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.whipped_cream_name, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.chocolate_name, hasChocolate);
        priceMessage += "\n" + getString(R.string.quantity_name, quantity);
        priceMessage += "\n" + getString(R.string.total_name, price);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream is whether or not user choose whipped cream
     * @param hasChocolate    is whether or not user choose chocolate
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        //Price of one cup of coffee
        int basePrice = 5;

        //Adds 1$ if user choose whipped cream
        if (hasWhippedCream) {
            basePrice += 1;
        }

        //Adds 2$ if user choose chcolate
        if (hasChocolate) {
            basePrice += 2;
        }

        //Calculates total price for the order
        return quantity * basePrice;
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
}