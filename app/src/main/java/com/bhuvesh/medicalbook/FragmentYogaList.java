package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;


public class FragmentYogaList extends ListFragment {
    /**
     *this fragment activity will have a list view
     * which has names of all teh yogas.
     * as user clicks on any yoga name, the yoga detail will appear
     */



    static interface YogaListListener {
        // this interface lets the other activit (the one implements it)
        //to get the id of item clicked in the list
        void itemClicked(long id);
    };

    // declare the variables
    private YogaListListener yogaListListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // make a array of all the yoga names from Yoga class.
        String[] names = new String[Yoga.yogas.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Yoga.yogas[i].getYogaName();
        }

        // set the array to list adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (inflater.getContext(), android.R.layout.simple_list_item_1, names);
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