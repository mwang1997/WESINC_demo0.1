package com.example.mark.wesinc_demo01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.ArrayList;
import android.content.*;

public class WESINC_slider extends AppCompatActivity {

    final int EDITING_REQUEST = 1;
    String result = "";
    final Intent data = this.getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_slider);
        final Context context = this;

        final String total_payment = this.getIntent().getStringExtra("total_payment");

        //Enumerated List of transactions
        final ArrayList<String> paidArrayList = this.getIntent().getStringArrayListExtra("paidArrayList");

        //List of transactions
        final ListView transactionSummary = (ListView)findViewById(R.id.listView_transactionSummary);

        //Adapter of Strings
        final ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.list_items, R.id.list_item_layout, paidArrayList);
        transactionSummary.setAdapter(adapter);

        transactionSummary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,  View view, int position, long id) {
                Intent editing = new Intent(context, WESINC_editing.class);
                editing.putExtra("order_item", paidArrayList.get(position));
                editing.putExtra("total_payment", total_payment);
                startActivityForResult(editing, EDITING_REQUEST);
                if(result.equals("delete")){
                    paidArrayList.remove(position);
                    adapter.notifyDataSetChanged();
                    setResult(RESULT_OK, data);
                }
                else if(result.equals("edit")){
                }
            }
        });

        final Button button_additem = (Button)findViewById(R.id.button_additem);
        button_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == EDITING_REQUEST && resultCode == RESULT_OK){
            result = data.getStringExtra("result");
            if(result.equals("edit")){
                this.data.putExtra("result", 0);
                setResult(RESULT_OK, this.data);
                finish();
            }
        }
    }
}
