package com.example.mark.wesinc_demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.LinkedList;

public class WESINC_numpad extends AppCompatActivity {
    boolean tempMessage = true;
    Double totalPayment = 0.0;
    LinkedList<Double[]> paidLinkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_numpad);

        //Initializing current purchase
        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView text_totalPayment = (TextView)findViewById(R.id.text_totalPayment);
        //Initializing Button integers
        final Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempMessage){
                    textView.setText("");
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
                    textView.setText("");
                }
            }
        });

        final Button button_payment = (Button)findViewById(R.id.button_payment);
        button_payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double amount = 0;
                double price = 0;
                double additionalPayment = 0;
                boolean tempAmountBool = true;
                boolean decimalBool = false;

                if(tempMessage){
                    textView.setText("");
                    return;
                }
                //Incrementing to numbers into the linkedlist
                for(int i = 0, x = 0; i < textView.length(); i++){
                    //if there is no amount
                    if(!textView.getText().toString().contains("x")){
                        tempAmountBool = false;
                        amount = 1;
                    }
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

                additionalPayment = amount * price;
                if(totalPayment + additionalPayment > 10000){
                    textView.setText("Amount is too large");
                    tempMessage = true;
                    return;
                }
                else{
                    totalPayment += additionalPayment;
                    text_totalPayment.setText("$" + totalPayment);
                    textView.setText("Number of Items x Price of Item");
                    tempMessage = true;
                }

                //The array of information needed to insert
                Double[] information= {new Double(amount), new Double(price)};
                paidLinkedList.add(information);
            }
        });
    }
}
