package com.example.mark.wesinc_demo01;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class WESINC_numpad extends AppCompatActivity {
    boolean tempMessage = true;
    double totalPayment = 0.0;
    String totalPaymentString = "";
    Intent slider;
    ArrayList<String> paidArrayList = new ArrayList<String>();
    //Results
    final int RESULT_ADDITEM = 5;
    final int RESULT_EDIT = 6;
    final int SLIDER_REQUEST = 2;
    final int CHECKOUT_REQUEST = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_numpad);
        final Context context = this;

        //Initializing current purchase
        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView text_totalPayment = (TextView)findViewById(R.id.text_totalPayment);

        //Clears the input
        final Button button_clear = (Button)findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPayment = 0.0;
                paidArrayList.clear();
                text_totalPayment.setText("$0.00");
                textView.setText("Number of Items x Price of Item");
                tempMessage = true;
                button_clear.setVisibility(View.GONE);
            }
        });

        //Appends a decimal
        final Button button_decimal = (Button)findViewById(R.id.button_decimal);
        button_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                }
                if(!textView.getText().toString().contains(".")) {
                    textView.append(".");
                }
            }
        });

        //Adds the multiplication sign
        final Button button_x = (Button)findViewById(R.id.button_x);
        button_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                }
                if(!textView.getText().toString().equals("") && !textView.getText().toString().contains("x") && !textView.getText().toString().contains(".")) {
                    textView.append("x");
                }
            }
        });

        //Goes back and deletes one from the input
        final Button button_delete = (Button)findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                }
                if (textView.length() > 1){
                    textView.setText(textView.getText().subSequence(0, textView.length() - 1));
                }
                else if(textView.length() == 1){
                    textView.setText("Number of Items x Price of Item");
                    tempMessage = true;
                }
            }
        });

        //Adds a payment or Confirms a purchase
        final Button button_payment = (Button)findViewById(R.id.button_payment);
        button_payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int amount = 0;
                double price = 0;
                double additionalPayment = 0;
                boolean tempAmountBool = true;
                boolean decimalBool = false;

                if(tempMessage && paidArrayList.isEmpty()){
                    textView.setText("");
                    return;
                }
                else if(tempMessage && !paidArrayList.isEmpty()){
                    slider = new Intent(context, WESINC_slider.class);
                    slider.putStringArrayListExtra("paidArrayList", paidArrayList);
                    slider.putExtra("total_payment", text_totalPayment.getText());
                    startActivityForResult(slider, SLIDER_REQUEST);
                    return;
                }

                if(textView.getText().toString().charAt(textView.length() - 1) == 120){
                    textView.setText("Invalid Input");
                    tempMessage = true;
                    return;
                }
                //Incrementing to numbers into the linkedlist
                for(int i = 0, x = 0; i < textView.length(); i++){
                    //if there is no amount
                    if(!textView.getText().toString().contains("x")){
                        tempAmountBool = false;
                        amount = 1;
                    }
                    //Else wait for the x to happen before starting to append
                    if(textView.getText().toString().charAt(i) == 120){
                        tempAmountBool = false;
                    }
                    //When there is a decimal
                    if(textView.getText().toString().charAt(i) == 46){
                        decimalBool = true;
                    }
                    //If the tempAmountBool is true
                    if(tempAmountBool && textView.getText().toString().charAt(i) != 46 && textView.getText().toString().charAt(i) != 120){
                        amount *= 10;
                        amount += Character.getNumericValue(textView.getText().toString().charAt(i));
                    }
                    if(!tempAmountBool && textView.getText().toString().charAt(i) != 46 && textView.getText().toString().charAt(i) != 120){
                        if (!decimalBool) {
                            price *= 10;
                            price += Character.getNumericValue(textView.getText().toString().charAt(i));
                        }
                        else{
                            x++;
                            price += Character.getNumericValue(textView.getText().toString().charAt(i)) / (Math.pow(10, x));
                        }
                    }
                }
                //How much to add on
                additionalPayment = amount * price;
                if(totalPayment + additionalPayment > 10000){
                    textView.setText("Amount is too large");
                    tempMessage = true;
                    return;
                }
                else{
                    totalPayment += additionalPayment;
                    //Correcting for rounding errors
                    if(totalPayment * 100 - (int)(totalPayment * 100) >= .5){
                        totalPayment = ((int)(totalPayment * 100) + 1) / 100.0;
                    }
                    else{
                        totalPayment = ((int)(totalPayment * 100)) / 100.0;
                    }

                    if(totalPayment * 10 - (int)(totalPayment * 10) == 0) {
                        totalPaymentString = "$" + totalPayment + "0";
                    }
                    else{
                       totalPaymentString = "$" + totalPayment;
                    }
                    text_totalPayment.setText(totalPaymentString);
                    button_payment.setText("Checkout");
                    textView.setText("Number of Items x Price of Item");
                    tempMessage = true;
                }

                //Rounding the price
                if(price * 100 - (int)(price * 100) >= .5){
                    price = ((int)(price * 100) + 1) / 100.0;
                }
                else{
                    price = ((int)(price * 100)) / 100.0;
                }

                paidArrayList.add(amount + " x " + price);

                button_payment.setText("Checkout");
                textView.setText("Number of Items x Price of Item");
                tempMessage = true;

                //The array of information needed to insert
                button_clear.setVisibility(View.VISIBLE);
            }
        });

        //Initializing Button integers
        final Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("9");
            }
        });
        final Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("8");
            }
        });
        final Button button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("7");
            }
        });

        final Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("6");
            }
        });

        final Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("5");
            }
        });

        final Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("4");
            }
        });

        final Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("3");
            }
        });

        final Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("2");
            }
        });

        final Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                textView.append("1");
            }
        });

        final Button button0 = (Button)findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
                    button_payment.setText("Add to Total");
                    tempMessage = false;
                }
                for(int i = 0; i < textView.length(); i++){
                    if(textView.getText().toString().charAt(i) == 46 & textView.length() == i + 3) {
                        return;
                    }
                }
                if(!textView.getText().toString().equals("")) {
                    textView.append("0");
                }
            }
        });

        final Button button_checkout = (Button)findViewById(R.id.button_checkout);
        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(context, WESINC_checkout.class);
                checkout.putExtra("arraylist", paidArrayList);
                checkout.putExtra("total_payment", totalPaymentString);
                startActivityForResult(checkout, CHECKOUT_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == SLIDER_REQUEST && resultCode == RESULT_EDIT){
            setArrayList(data.getStringArrayListExtra("arraylist"));
            ((TextView)findViewById(R.id.textView)).setText(data.getStringExtra("arrayitem"));
            totalPaymentString = data.getStringExtra("total_payment");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < totalPaymentString.length(); i++){
                if((totalPaymentString.charAt(i) <= 57 && totalPaymentString.charAt(i) >= 48) || totalPaymentString.charAt(i) == 46){
                    sb.append(totalPaymentString.charAt(i));
                }
            }
            totalPayment = Double.parseDouble(sb.toString());
            ((TextView)findViewById(R.id.text_totalPayment)).setText(data.getStringExtra("total_payment"));
            tempMessage = false;
        }
        else if(requestCode == SLIDER_REQUEST && resultCode == RESULT_ADDITEM){
            setArrayList(data.getStringArrayListExtra("arraylist"));
            ((TextView)findViewById(R.id.text_totalPayment)).setText(data.getStringExtra("total_payment"));
            totalPaymentString = data.getStringExtra("total_payment");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < totalPaymentString.length(); i++){
                if((totalPaymentString.charAt(i) <= 57 && totalPaymentString.charAt(i) >= 48) || totalPaymentString.charAt(i) == 46){
                    sb.append(totalPaymentString.charAt(i));
                }
            }
            totalPayment = Double.parseDouble(sb.toString());
            ((TextView)findViewById(R.id.textView)).setText("Number of Items x Price of Item");
            tempMessage = true;
        }
    }

    private void setArrayList(ArrayList<String> arrayList){
        paidArrayList = arrayList;
    }
}
