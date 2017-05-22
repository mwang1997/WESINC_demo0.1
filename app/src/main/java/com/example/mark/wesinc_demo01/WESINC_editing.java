package com.example.mark.wesinc_demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.*;
import android.widget.*;

import java.util.ArrayList;

public class WESINC_editing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_editing);

        final Intent data = this.getIntent();

        final ArrayList<String> arrayList = this.getIntent().getStringArrayListExtra("arraylist");

        final int position = this.getIntent().getIntExtra("position", -1);

        TextView edited = (TextView)findViewById(R.id.textView_edited);
        edited.setText(arrayList.get(position));

        TextView text_totalPayment_editing = (TextView)findViewById(R.id.textView_total_payment_editing);
        text_totalPayment_editing.setText(data.getStringExtra("total_payment"));

        final Button button_delete = (Button)findViewById(R.id.button_delete_editing);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do with the result
                data.putExtra("result", "delete");
                //The array list, returned
                data.putExtra("arraylist", arrayList);
                //Position of the item
                data.putExtra("position", position);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        final Button button_edit = (Button)findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putExtra("result", "edit");
                data.putExtra("arraylist", arrayList);
                data.putExtra("position", position);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        final Button button_cancel = (Button)findViewById(R.id.button_cancel_editing);
        button_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
