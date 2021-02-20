package com.bhuvesh.medicalbook.safeentryfeature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bhuvesh.medicalbook.MainActivity;
import com.bhuvesh.medicalbook.R;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class SafeEntry extends AppCompatActivity {
    /*
    * This activity helps the user to quickly scan any QR code
    * in order to provide their details
    * for example Safe entry in singapore
    * */

    // declare the variables
    public CodeScanner safeEntryScanner;

    // att home button on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Log.d("ID number", String.valueOf(item.getItemId()));
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_entry);

        // link up objects with correct view element
        CodeScannerView safeEntryView = findViewById(R.id.safe_entry_view);
        safeEntryScanner = new CodeScanner(this,safeEntryView);

        // call back function to read QR code and send teh result back to
        // parent activity, in this case mainactivity.
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