package com.example.mark.wesinc_demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.*;
import android.widget.*;

public class WESINC_editing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_editing);

        final Intent data = this.getIntent();

        TextView edited = (TextView)findViewById(R.id.textView_edited);
        edited.setText(this.getIntent().getStringExtra("order_item"));

        TextView total_payment = (TextView)findViewById(R.id.textView_total_payment);
        total_payment.setText(this.getIntent().getStringExtra("total_payment"));

        final Button button_delete = (Button)findViewById(R.id.button_delete_editing);
        button_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.putExtra("result", "delete");
                    setResult(RESULT_OK, data);
                    finish();
                }
        });

        final Button button_edit = (Button)findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putExtra("result", "edit");
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
