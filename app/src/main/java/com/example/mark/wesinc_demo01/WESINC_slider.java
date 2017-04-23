package com.example.mark.wesinc_demo01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.ArrayList;
import android.content.*;

public class WESINC_slider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_slider);
        final Context context = this;

        //Enumerated List of transactions
       ArrayList<String> paidArrayList = this.getIntent().getStringArrayListExtra("paidArrayList");

        //List of transactions
        ListView transactionSummary = (ListView)findViewById(R.id.listView_transactionSummary);

        //Adapter of Strings
        ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.list_items, R.id.list_item_layout, paidArrayList);
        transactionSummary.setAdapter(adapter);

        /*
        transactionSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(context, WESINC_edit.class);
                edit.putExtra()
            }
        });
        */
    }
}
