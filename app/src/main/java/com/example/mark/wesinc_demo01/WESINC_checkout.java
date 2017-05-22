package com.example.mark.wesinc_demo01;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.nfc.tech.*;
import android.nfc.*;
import android.widget.Toast;
import android.content.*;
import android.app.*;
import android.util.*;
import java.util.*;
import android.net.*;
import android.widget.*;

public class WESINC_checkout extends AppCompatActivity {

    ArrayList<String> paidArrayList;
    NfcAdapter nfcAdapter;
    Intent data;
    String total_payment;
    FileUriCallback fileUriCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_checkout);

        data = this.getIntent();

        paidArrayList = data.getStringArrayListExtra("arraylist");

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        String total_payment = data.getStringExtra("total_payment");

        TextView text_totalPayment_checkout = (TextView)findViewById(R.id.textView_totalPayment_checkout);
        text_totalPayment_checkout.setText(total_payment);

        fileUriCallback = new FileUriCallback();

        if(nfcAdapter == null || !nfcAdapter.isEnabled()){
            Toast.makeText(this, "NFC is currently unavaliable", Toast.LENGTH_LONG);
            finish();
            return;
        }

        nfcAdapter.setBeamPushUrisCallback(fileUriCallback, this);

    }

    private class FileUriCallback implements NfcAdapter.CreateBeamUrisCallback{
        @Override
        public Uri[] createBeamUris(NfcEvent nfcEvent){
            return new Uri[]{data.getData()};
        }
    }


}
