package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class SafeEntry extends AppCompatActivity {
    public CodeScanner safeEntryScanner;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("ID number", String.valueOf(item.getItemId()));

        int itemId = 0;
        itemId = item.getItemId();

        if (itemId == R.id.dashboard)
        {
            Log.d("ID number", String.valueOf("at home"));

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;




        }
        else
        {
            Log.d("ID number", String.valueOf("at set"));
            return true;
        }





        //return super.onOptionsItemSelected(item);
    }
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