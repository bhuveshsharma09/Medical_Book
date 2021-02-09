package com.bhuvesh.medicalbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class DialyMedicalRecordActivity extends AppCompatActivity {


    ArrayList<Record> records;
    RecyclerView recyclerView;
    ImageButton imageButton;
    RecordAdapter recordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialy_medical_record);

        imageButton = findViewById(R.id.image_add_record);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch_record_input(v);
            }
        });


        recyclerView = findViewById(R.id.record_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(DialyMedicalRecordActivity.this));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new RecordHandler(DialyMedicalRecordActivity.this).remove(records.get(viewHolder.getAdapterPosition()).getId());
                records.remove(viewHolder.getAdapterPosition());
                recordAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());





            }
        };



        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        addRecords();




        addRecords();
    }

    public ArrayList<Record> readRecords(){

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
    {

        RecordHandler recordHandler = new RecordHandler(this);
        Record record = recordHandler.readOneRecord(id);
        Intent intent = new Intent(this,EditRecord.class);
        intent.putExtra("title",record.getTitle());
        intent.putExtra("des",record.getDescription());
        intent.putExtra("id",record.getId());

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                (DialyMedicalRecordActivity.this,
                view, ViewCompat.getTransitionName(view));

        startActivityForResult(intent,1,optionsCompat.toBundle());




    }


    public void launch_record_input(View view) {

        LayoutInflater layoutInflater = (LayoutInflater) DialyMedicalRecordActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.record_input,null,false);

        EditText record_title = (EditText) view1.findViewById(R.id.dialog_record_title);
        EditText record_des = (EditText) view1.findViewById(R.id.dialog_record_description);


        new AlertDialog.Builder(DialyMedicalRecordActivity.this)
        .setView(view1)
        .setTitle("Add today's health record")
        .setPositiveButton("STORE", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String title = record_title.getText().toString();
            String des = record_des.getText().toString();
            Record record = new Record(title,des);


            boolean result = new RecordHandler(DialyMedicalRecordActivity.this).createRecord(record);

            if(result){
                Toast.makeText(DialyMedicalRecordActivity.this, "ADDED", Toast.LENGTH_SHORT).show();
                addRecords();
            }
            else {
                Toast.makeText(DialyMedicalRecordActivity.this, "UNABLE", Toast.LENGTH_SHORT).show();
            }

            dialog.cancel();


        }
    }).show();
    }
}