package com.example.mark.wesinc_demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.nfc.NfcAdapter;
import android.content.*;
import android.widget.*;

public class WESINC_checkout extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesinc_checkout);
    }
}
