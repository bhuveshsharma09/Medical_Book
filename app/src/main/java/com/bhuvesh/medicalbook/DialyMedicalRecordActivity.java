package com.bhuvesh.medicalbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialyMedicalRecordActivity extends AppCompatActivity {
    /*
    * This activity provides user with ioption to
    * create daily notes related to their health.
    * user can add their body temperature etc.
    * */

    // declare variables
    ArrayList<Record> records;
    RecyclerView recyclerView;
    ImageButton addNewRecord;
    RecordAdapter recordAdapter;

    // add home button on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = 0;
        itemId = item.getItemId();
        if (itemId == R.id.dashboard)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialy_medical_record);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // link the correct elements with variables
        addNewRecord = findViewById(R.id.image_add_record);
        addNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever user click on addNewRecord button
                // the following function will be called
                // which facilitate the creation of new record
                launch_record_input(v);
            }
        });


        recyclerView = findViewById(R.id.record_recycler);
        // add the touch swipe capability on record fragment.
        recyclerView.setLayoutManager(new LinearLayoutManager(DialyMedicalRecordActivity.this));
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new RecordHandler(DialyMedicalRecordActivity.this).remove(records.get(viewHolder.getAdapterPosition()).getId());
                // remove record when swiped left
                records.remove(viewHolder.getAdapterPosition());
                recordAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // populate recycler view with records
        addRecords();
    }

    public ArrayList<Record> readRecords(){
        // help read all records from database and return an array of records
        ArrayList<Record> records = new RecordHandler(this).readFromRecords();
        return records;
    }

    public void addRecords(){
        records = readRecords();
        recordAdapter = new RecordAdapter(records, this, new RecordAdapter.ItemClicked() {
            @Override
            public void onClick(int position, View view) {
                editRecord(records.get(position).getId(), view);
            }
        });
        recyclerView.setAdapter(recordAdapter);
    }

    private void editRecord(int id,View view)
    {   // call the activity to edit the record
        // put all the data in intent for user to check and edit.
        RecordHandler recordHandler = new RecordHandler(this);
        Record record = recordHandler.readOneRecord(id);

       // Log.d("the record", String.valueOf(record));

        Intent intent = new Intent(this,EditRecord.class);
        intent.putExtra("title",record.getRecordTitle());
        intent.putExtra("des",record.getRecordDescription());
        intent.putExtra("id",record.getId());

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                (DialyMedicalRecordActivity.this,
                view, ViewCompat.getTransitionName(view));

        startActivityForResult(intent,1,optionsCompat.toBundle());
    }


    public void launch_record_input(View view) {
        // store record values like title and description
        LayoutInflater layoutInflater = (LayoutInflater) DialyMedicalRecordActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recordInputDialogBox = layoutInflater.inflate(R.layout.record_input,null,false);

        EditText record_title = (EditText) recordInputDialogBox.findViewById(R.id.dialog_record_title);
        EditText record_des = (EditText) recordInputDialogBox.findViewById(R.id.dialog_record_description);

        // date object to provide it in record title for record managment
        String date = new SimpleDateFormat("dd-MM-yyy").format(new Date());
        // Date currentTime = Calendar.getInstance().getTime();
       // Log.d("date", String.valueOf(date));
        record_title.setText("Date:"+date);

        // launch an alert box to get user's inputs
        new AlertDialog.Builder(DialyMedicalRecordActivity.this)
        .setView(recordInputDialogBox)
        .setTitle("New health record")
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // when save button is clicked the following code will work
            String title = record_title.getText().toString();
            String des = record_des.getText().toString();
            // create a new record
            Record record = new Record(title,des);
            // create the record in database
            boolean result = new RecordHandler(DialyMedicalRecordActivity.this).createRecord(record);
            // check record has been added or not
            if(result){
                Toast.makeText(DialyMedicalRecordActivity.this, "Record has been added", Toast.LENGTH_SHORT).show();
                addRecords();
            }
            else {
                Toast.makeText(DialyMedicalRecordActivity.this, "Unable to create new record", Toast.LENGTH_SHORT).show();
            }
            // close teh dialog box
            dialog.cancel();
        }
    }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            addRecords();
        }
    }
}