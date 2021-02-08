package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FragmentYogaList extends ListFragment {


    static interface YogaListListener {
        void itemClicked(long id);
    }

    ;

    private YogaListListener yogaListListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        String[] names = new String[Yoga.yogas.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Yoga.yogas[i].getYogaName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        this.yogaListListener = (YogaListListener) activity;

    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (yogaListListener != null) {
            yogaListListener.itemClicked(id);
        }


    }
}