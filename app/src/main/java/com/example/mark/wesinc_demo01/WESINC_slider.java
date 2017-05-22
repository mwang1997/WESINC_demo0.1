package com.example.mark.wesinc_demo01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.ArrayList;
import android.content.*;

public class WESINC_slider extends AppCompatActivity {

    //total cost of all transactions
    String total_payment;
    //listView items
    private ArrayList<String> paidArrayList;
    //Results
    final int EDITING_REQUEST = 1;
    final int RESULT_ADDITEM = 5;
    final int RESULT_EDIT = 6;
    final int RESULT_CHECKLIST = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_slider);
        final Context context = this;
        final Intent data = this.getIntent();

        //String to display total payment
        total_payment = this.getIntent().getStringExtra("total_payment");

        //Enumerated List of transactions
        paidArrayList = this.getIntent().getStringArrayListExtra("paidArrayList");

        //List of transactions
        final ListView transactionSummary = (ListView)findViewById(R.id.listView_transactionSummary);

        //TextView of total amount
        TextView text_totalPayment_slider = (TextView)findViewById(R.id.textView_header);
        text_totalPayment_slider.setText(total_payment);

        //Adapter of Strings
        final ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.list_items, R.id.list_item_layout, paidArrayList);
        transactionSummary.setAdapter(adapter);

        transactionSummary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,  View view, int position, long id) {
                Intent editing = new Intent(context, WESINC_editing.class);
                editing.putStringArrayListExtra("arraylist", paidArrayList);
                editing.putExtra("position", position);
                startActivityForResult(editing, EDITING_REQUEST);
            }
        });

        final Button button_additem = (Button)findViewById(R.id.button_additem);
        button_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putExtra("arraylist", paidArrayList);
                data.putExtra("total_payment", total_payment);
                setResult(RESULT_CHECKLIST, data);
                finish();
            }
        });

        final Button button_checkout = (Button)findViewById(R.id.button_checkout);
        button_checkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent checkout = new Intent(context, WESINC_checkout.class);
                checkout.putExtra("arraylist", paidArrayList);
                checkout.putExtra("total_payment", total_payment);
                startActivityForResult(checkout, EDITING_REQUEST);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == EDITING_REQUEST && resultCode == RESULT_OK){
            Intent slider = this.getIntent();
            int position = data.getIntExtra("position", -1);
            String total_payment_String = "";
            StringBuilder sb = new StringBuilder();
            //Parsing
            for(int i = 0; i < total_payment.length(); i++){
                if((total_payment.charAt(i) <= 57 && total_payment.charAt(i) >= 48) || total_payment.charAt(i) == 46){
                    sb.append(total_payment.charAt(i));
                }
            }
            double totalPaymentValue = Double.parseDouble(sb.toString());

            //amount and price
            StringBuilder amount = new StringBuilder();
            StringBuilder price = new StringBuilder();
            ArrayList<String> items = this.getItems();
            String item = items.get(position);
            boolean x = false;
            //Parsing
            for(int i = 0; i < item.length(); i++){
                if(!x && ((item.charAt(i) <= 57 && item.charAt(i) >= 48) || item.charAt(i) == 46)){
                    amount.append(item.charAt(i));
                }
                else if(x && ((item.charAt(i) <= 57 && item.charAt(i) >= 48) || item.charAt(i) == 46)){
                    price.append(item.charAt(i));
                }
                else if(item.charAt(i) == 120){
                    x = true;
                }
            }
            if(x){
                totalPaymentValue -= Double.parseDouble(amount.toString()) * Double.parseDouble(price.toString());
            }
            else{
                totalPaymentValue -= Double.parseDouble(amount.toString());
            }

            //Rounding
            if(totalPaymentValue * 100 - (int)(totalPaymentValue * 100) >= .5){
                totalPaymentValue = ((int)(totalPaymentValue * 100) + 1) / 100.0;
            }
            else{
                totalPaymentValue = ((int)(totalPaymentValue * 100)) / 100.0;
            }

            if(totalPaymentValue * 10 - (int)(totalPaymentValue * 10) == 0) {
                total_payment_String = "$" + totalPaymentValue + "0";
            }
            else{
                total_payment_String = "$" + totalPaymentValue;
            }
            total_payment = total_payment_String;
            ((TextView)findViewById(R.id.textView_header)).setText(total_payment);

            if(data.getStringExtra("result").equals("delete")){
                ((ArrayAdapter)((ListView)findViewById(R.id.listView_transactionSummary)).getAdapter()).remove(((ArrayAdapter)((ListView)findViewById(R.id.listView_transactionSummary)).getAdapter()).getItem(position));
                slider.putExtra("arraylist", this.getItems());
                slider.putExtra("total_payment", total_payment_String);
                slider.putExtra("arrayitem", item);
            }
            else if(data.getStringExtra("result").equals("edit")){
                ((ArrayAdapter)((ListView)findViewById(R.id.listView_transactionSummary)).getAdapter()).remove(((ArrayAdapter)((ListView)findViewById(R.id.listView_transactionSummary)).getAdapter()).getItem(position));
                slider.putExtra("arraylist", this.getItems());
                slider.putExtra("arrayitem", item);
                slider.putExtra("total_payment", total_payment_String);
                setResult(RESULT_EDIT, slider);
                finish();
            }
        }
    }

    private ArrayList<String> getItems(){
        return this.paidArrayList;
    }
}
