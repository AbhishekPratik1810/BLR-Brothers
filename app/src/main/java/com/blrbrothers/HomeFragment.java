package com.blrbrothers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView amb,wt,vr,events,news,video;
    FragmentManager fragmentManager;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        fragmentManager=getFragmentManager();
        amb.setOnClickListener(this);
        wt.setOnClickListener(this);
        vr.setOnClickListener(this);
        events.setOnClickListener(this);
        news.setOnClickListener(this);
        video.setOnClickListener(this);
        return view;
    }

    private void initView(View view){
        amb=view.findViewById(R.id.iv_Home_Ambulance);
        wt=view.findViewById(R.id.iv_Home_WaterTank);
        vr=view.findViewById(R.id.iv_Home_VaikuntaRath);
        events=view.findViewById(R.id.iv_Home_Events);
        news=view.findViewById(R.id.iv_Home_News);
        video=view.findViewById(R.id.iv_Home_Video);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_Home_Ambulance:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new Amb_WT_VRFragment(0))
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
                break;
            case R.id.iv_Home_WaterTank:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new Amb_WT_VRFragment(1))
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
                break;
            case R.id.iv_Home_VaikuntaRath:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new Amb_WT_VRFragment(2))
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
                break;
            case R.id.iv_Home_Events:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new EventsFragment())
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
//                Toast.makeText(getActivity(),"Yet to make this feature",Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_Home_News:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new NewsFragment())
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
               //Toast.makeText(getActivity(),"Yet to make this feature",Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_Home_Video:
                fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, new VideoFragment())
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("ambulance").commit();
                break;
        }
    }
}
