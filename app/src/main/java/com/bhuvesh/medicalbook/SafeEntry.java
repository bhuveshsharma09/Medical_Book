package com.bhuvesh.medicalbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class SafeEntry extends AppCompatActivity {
    public CodeScanner safeEntryScanner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_entry);

        CodeScannerView safeEntryView = findViewById(R.id.safe_entry_view);

        safeEntryScanner = new CodeScanner(this,safeEntryView);

        safeEntryScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                Intent intent = new Intent();
                intent.putExtra("scanning_result",result.getText());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        safeEntryScanner.startPreview();




    }





}