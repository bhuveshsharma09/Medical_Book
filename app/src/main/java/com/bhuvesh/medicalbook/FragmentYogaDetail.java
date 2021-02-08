package com.bhuvesh.medicalbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentYogaDetail extends Fragment {

    private long yogaId;

    // to prevent yogaId reset when device screen rotates
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            yogaId = savedInstanceState.getLong("yogaId");
        }
        else {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            FragmentTimer fragmentTimer = new FragmentTimer();

            fragmentTransaction.replace(R.id.timer_container, fragmentTimer);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }





    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yoga_detail, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){
            TextView yogaName = (TextView) view.findViewById(R.id.yoga_name);
            TextView yogaDetail = (TextView) view.findViewById(R.id.yoga_detail);

            Yoga yoga = Yoga.yogas[(int) yogaId];

            yogaName.setText(yoga.getYogaName());
            yogaDetail.setText(yoga.getYogaDetail());
        }
    }

    public void setYogaId(long Id) {
        this.yogaId = Id;

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("yogaId", yogaId);
    }



}