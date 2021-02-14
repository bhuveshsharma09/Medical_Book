package com.bhuvesh.medicalbook.medicalrecordfeature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhuvesh.medicalbook.R;

public class EditRecord extends AppCompatActivity {
    /*
    * This activity is called when, the user clicks on
    * edit clip art on record holder.
    * here user can see the record and edit it.
    * after editing user can either save the record or cancel the chnages.*/

    // declare all the variables
    EditText editRecordTitle, editRecordDes;
    Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        // link variables with correct element
        editRecordTitle = findViewById(R.id.edit_record_title);
        editRecordDes = findViewById(R.id.edit_record_des);
        saveButton = findViewById(R.id.button_save);
        cancelButton = findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back if Cancel button is clicked
                onBackPressed();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if user clicks save button, this code will work
                Record record = new Record(editRecordDes.getText().toString(),editRecordTitle.getText().toString());
                record.setId(intent.getIntExtra("id",1));

                if (new RecordHandler(EditRecord.this).update(record)){
                    Toast.makeText(EditRecord.this, "Note updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditRecord.this, "Failed updating", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });

        editRecordTitle.setText(intent.getStringExtra("title"));
        editRecordDes.setText(intent.getStringExtra("des"));

    }
}