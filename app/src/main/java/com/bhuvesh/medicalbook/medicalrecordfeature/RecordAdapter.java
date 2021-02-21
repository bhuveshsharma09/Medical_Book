package com.bhuvesh.medicalbook.medicalrecordfeature;

import android.content.Context;
import android.graphics.Color;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhuvesh.medicalbook.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    // create an array of records
    ArrayList<Record> records;
    Context context;
    static ItemClicked itemClicked;

    ViewGroup parent;
    private int lastSelectedPosition = -1;



    // create adapter to set data into Recycler view
    public RecordAdapter(ArrayList<Record> arrayList, Context context, ItemClicked itemClicked) {
        records = arrayList;
        this.context = context;
        this.itemClicked = itemClicked;
    }




    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.record_holder,parent,false);
        this.parent = parent;
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordHolder holder, int position) {
        if (position == lastSelectedPosition){
            holder.recordTitle.setTextColor(Color.parseColor("#000000"));
        }else {
            holder.recordTitle.setTextColor(Color.parseColor("#4472C4"));
        }
        holder.recordTitle.setText(records.get(position).getRecordTitle());
        holder.recordDescription.setText(records.get(position).getRecordDescription());

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class RecordHolder extends RecyclerView.ViewHolder {
        TextView recordTitle, recordDescription;
        ImageView editImage;
        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            recordTitle = itemView.findViewById(R.id.record_title_name);
            recordDescription = itemView.findViewById(R.id.record_description);
            editImage = itemView.findViewById(R.id.edit_clipart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recordTitle.setTextColor(Color.parseColor("#000000"));
                    lastSelectedPosition = getAdapterPosition();

                    if (recordDescription.getMaxLines() == 1){
                        recordDescription.setMaxLines(Integer.MAX_VALUE);
                    }else {
                        recordDescription.setMaxLines(1);
                    }
                    notifyDataSetChanged();
                    TransitionManager.beginDelayedTransition(parent);
                }
            });



            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClicked.onClick(getAdapterPosition(),itemView);
                }
            });


        }
    }




    interface ItemClicked{
        void onClick(int position, View view);
    }
}


